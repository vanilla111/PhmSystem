package cn.edu.uestc.cac.utils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author wang
 */
public class Stopper {
    private static AtomicBoolean signal = new AtomicBoolean(false);

    public static final boolean isStopped() {
        return signal.get();
    }

    public static final boolean isRunning() {
        return !signal.get();
    }

    public static final void stop() {
        signal.set(true);
    }
}
