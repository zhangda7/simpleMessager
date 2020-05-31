package com.comm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.comm.service.dao")
public class ServerApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServerApp.class, args);
//        SimpleServer simpleServer = new SimpleServer();
//        simpleServer.start("0.0.0.0", 8081);
    }
}
