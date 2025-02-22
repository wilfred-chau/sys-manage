package com.example.sysmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.example.sysmanage.mapper")
public class SysManageApplication {

    public static void main(String[] args) {
        // 设置默认字符编码
        System.setProperty("file.encoding", "UTF-8");
        SpringApplication.run(SysManageApplication.class, args);
    }

}
