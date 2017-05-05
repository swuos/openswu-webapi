package cn.swu.edu.opensource.openswu_webapi_jersey.quitnet;

import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by chensiding on 17-5-5.
 */
public class QuitNetTask extends TimerTask {

    private final ConcurrentSkipListMap<String, QuitNetParam> queue;

    public QuitNetTask(ConcurrentSkipListMap<String, QuitNetParam> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!queue.isEmpty()) {
            QuitNetParam quitInfo = queue.pollFirstEntry().getValue();
            long want = quitInfo.getDate();
            long now = System.currentTimeMillis();
            /*
             想要退网的时间在现在时间的未来十秒之内
             */
            if (want - now < 10000) {
                new Quit().doQuit(quitInfo.getUsername(), quitInfo.getPassword());
            } else {
                break;
            }
        }
    }
}
