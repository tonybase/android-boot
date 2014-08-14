package io.ganguo.app.basic.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer处理工具
 *
 * @author ZhiHui_Chen
 * @Email: 6208317#qq.com
 * @Date 2013-5-23
 */
public class TimerUtil {
    private final static Timer timer = new Timer();
    ;
    private final static Map<Runnable, TimerAdapter> map = new HashMap<Runnable, TimerAdapter>();

    /**
     * 间隔多少毫秒执行一次
     *
     * @param runnable
     * @param interval
     */
    public static void setInterval(Runnable runnable, long interval) {
        TimerAdapter adapter = new TimerAdapter(runnable);
        map.put(runnable, adapter);
        timer.schedule(adapter, interval, interval);
    }

    /**
     * 多少毫秒后执行该线程 只执行一次
     *
     * @param runnable
     * @param delay
     */
    public static void setTimeout(Runnable runnable, long delay) {
        TimerAdapter adapter = new TimerAdapter(runnable);
        map.put(runnable, adapter);
        timer.schedule(adapter, delay);
    }

    public static void killTimer(Runnable runnable) {
        if (map.containsKey(runnable)) {
            TimerAdapter adapter = map.get(runnable);
            adapter.cancel();
            timer.purge();
        }
    }

    public static void killAll() {
        for (Runnable r : map.keySet()) {
            map.get(r).cancel();
            timer.purge();
        }
    }

    private static class TimerAdapter extends TimerTask {
        private Runnable runnable;

        public TimerAdapter(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override
        public void run() {
            try {
                runnable.run();
            } catch (Throwable e) {
                System.out.println("TimerAdapter run timer error!"
                        + e.getMessage());
            }
        }
    }
}
