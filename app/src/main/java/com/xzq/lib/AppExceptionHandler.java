package com.xzq.lib;

/**
 * 异常捕获器
 *
 * @author Alex
 *
 */
public class AppExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mDefaultHandler;

    public AppExceptionHandler() {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        handleException(thread, ex);
        mDefaultHandler.uncaughtException(thread, ex);
    }

    /**
     * 处理异常信息
     *
     * @param thread 错误线程
     * @param ex     错误
     */
    private void handleException(Thread thread, Throwable ex) {
        // 抓取异常设备信息

    }
}
