package cn.swu.edu.opensource.openswu_webapi_jersey.quitnet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by 张孟尧 on 2016/5/28.
 *
 * updated by by 西南大学开源协会 陈思定  on 2016/5/29. 修改为单例模式
 * <p>
 * Email : sidingchan@gmail.com
 */
public class QuitNetQueue {

    private static Log LOGGER = LogFactory.getLog(QuitNetQueue.class);

    private QuitNetQueue() {
        throw new IllegalAccessError();
    }

    private static final ConcurrentSkipListMap<String, QuitNetParam> queue =
            new ConcurrentSkipListMap<String, QuitNetParam>(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return queue.get(o1).getDate().compareTo(queue.get(o2).getDate());
                }
            });

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setName(Thread.currentThread().getId() + ".quitnet");
            return t;
        }
    });

    static {
        quitNet();
    }

    public static void quitNet() {
        scheduler.scheduleAtFixedRate(new QuitNetTask(queue), 0L, 10000, TimeUnit.MILLISECONDS);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            scheduler.shutdown();
        }));
    }

    /**
     * @param item 一个item
     * @return 添加成功返回true, 失败返回false
     */
    public static Boolean addItem(QuitNetParam item) {
        if (item == null) {
            return false;
        }
        /*提交的时间小于当前时间*/
        if (item.getDate() < System.currentTimeMillis()) {
            return false;
        }
        String swuID = item.getUsername();
        queue.put(swuID, item);
        return true;
    }

    /**
     * @param swuID 需要移除的id,将该id的定时移除队列
     * @return 移除成功返回true, 失败返回false
     */
    public static Boolean removeItem(String swuID) {
        if (swuID == null) {
            return false;
        }
        return queue.remove(swuID) == null ? false : true;
    }

}
