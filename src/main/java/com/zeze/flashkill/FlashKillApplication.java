package com.zeze.flashkill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zeze.flashkill.mapper")
public class FlashKillApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlashKillApplication.class, args);
    }

}
