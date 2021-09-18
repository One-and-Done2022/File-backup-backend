package com.aurora.drivesyncer;

import com.aurora.drivesyncer.lib.file.watcher.FileMonitor;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.aurora.drivesyncer.mapper")
public class AuroraDriveSyncerBackendApplication {
    public static void main(String[] args) {

        SpringApplication.run(AuroraDriveSyncerBackendApplication.class, args);


        FileMonitor fileMonitor = new FileMonitor("E:\\watched\\");
        fileMonitor.start();

    }

}
