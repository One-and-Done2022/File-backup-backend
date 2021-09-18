package com.aurora.drivesyncer.lib.file.watcher;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;


public class FileMonitor {
    Log log = LogFactory.getLog(getClass());

    private final String path;                                // 文件夹目录
    private final long interval;                        // 监听间隔
    private static final long DEFAULT_INTERVAL = 5000;  // 默认监听间隔 5s
    private final FileAlterationListenerAdaptor listener;        // 事件处理类对象

    public FileMonitor(String path) {
        this.path = path;
        this.interval = DEFAULT_INTERVAL;
        this.listener = new FileListener();
    }

    public FileMonitor(String path, FileAlterationListenerAdaptor listener) {
        this.path = path;
        this.interval = DEFAULT_INTERVAL;
        this.listener = listener;
    }

    public FileMonitor(String path, long interval, FileAlterationListenerAdaptor listener) {
        this.path = path;
        this.interval = interval;
        this.listener = listener;
    }

    public void start() {
        FileAlterationObserver observer = new FileAlterationObserver(
               new File(path));

            observer.addListener(new FileListener());
            FileAlterationMonitor monitor = new FileAlterationMonitor(interval,observer);
            try {
                log.info("Starting monitoring on " + path);
                monitor.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
}