package cn.swu.edu.opensource.openswu_webapi_jersey.quitnet;

import java.util.*;

/**
 * Created by 张孟尧 on 2016/5/28.
 *
 * updated by by 西南大学开源协会 陈思定  on 2016/5/29. 修改为单例模式
 * <p>
 * Email : sidingchan@gmail.com
 *
 */
public class QuitNetQueue {


    /* 单例模式实现 */
    private static QuitNetQueue ourInstance = new QuitNetQueue();

    public static QuitNetQueue getInstance() {
        return ourInstance;
    }

    private QuitNetQueue() {
        startQueue();
    }

    private Map<String, QuitNetParam> queue = new Hashtable<>();
    private Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            /*间隔时间*/
            long sleepTime = 10000 ;
            while (true) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("队列总数"+queue.size());
                /*对队列按照时间的先后进行排序*/
                List<Map.Entry<String, QuitNetParam>> list = new ArrayList<>(queue.entrySet());
                Collections.sort(list, new Comparator<Map.Entry<String, QuitNetParam>>() {
                    /*升序排序*/
                    public int compare(Map.Entry<String, QuitNetParam> o1,
                                       Map.Entry<String, QuitNetParam> o2) {
                        return o1.getValue().getDate().compareTo(o2.getValue().getDate());
                    }
                });
                /*i,计数*/
                int i = 0;
                long nowTime = System.currentTimeMillis();
                int listSize = list.size();
//                System.out.println("list数量"+listSize);
                for (int j = 0; j < listSize; j++) {
                    Map.Entry<String, QuitNetParam> item = list.get(j);
                    final QuitNetParam entry = item.getValue();
                    /*如果定时和当前时间相差小于等于sleepTime,是则退出,否则说明剩下的时间都大于sleepTime,在下一次进行判断*/
                    if (entry.getDate() - nowTime <= sleepTime) {
                        /*开新线程执行退出网络的操作*/
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                /*退出wifi*/
                                new Quit().doQuit(entry.getUsername(),entry.getPassword());
//                                System.out.printf("%s退出%n", entry.getUsername());
                            }
                        }).start();
                        /*退出队列*/
                        queue.remove(item.getKey());
                    } else {
                        break;
                    }
                    i++;
                }

            }
        }
    });


    /**
     * @param item 一个item
     * @return 添加成功返回true,失败返回false
     */
    public Boolean addItem(QuitNetParam item) {
        if (item == null) {
            return false;
        }
        /*提交的时间小于当前时间*/
        if (item.getDate() < System.currentTimeMillis()) {
//            System.out.println("lower than current time"+System.currentTimeMillis());

            return false;
        }
        String swuID = item.getUsername();
        queue.put(swuID, item);
        return true;
    }

    /**
     * @param swuID 需要移除的id,将该id的定时移除队列
     * @return 移除成功返回true,失败返回false
     */
    public Boolean removeItem(String swuID) {
        if (swuID == null)
            return false;
        if (!queue.containsKey(swuID)) {
            return false;
        }
        queue.remove(swuID);
        return true;
    }

    /*
     * 启动队列
     */
    private void startQueue() {
        thread.start();
    }

}
