package com.liangxp.gmall.mock.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@MapperScan("com.liangxp.gmall.mock.db.mapper")
public class GmallMockDbApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GmallMockDbApplication.class, args);

        MockTask mockTask = context.getBean(MockTask.class);
        while (true){
            mockTask.mainTask();
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
