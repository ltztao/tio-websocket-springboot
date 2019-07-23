package com.lt.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tio.websocket.starter.EnableTioWebSocketServer;

/**
 * @author fyp
 * @crate 2019/6/13 21:13
 * @project tio-starters
 *
 * 需要添加  @EnableTioWebSocketServer 来启动Tio WebSocket Server
 */
@SpringBootApplication
@EnableTioWebSocketServer
public class TioWebSocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(TioWebSocketApplication.class);
    }
}
