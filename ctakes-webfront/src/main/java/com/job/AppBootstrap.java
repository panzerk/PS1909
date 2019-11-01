package com.job;

import com.jfinal.core.JFinal;
import com.jfinal.server.undertow.UndertowServer;
import com.job.config.Config;


public class AppBootstrap {
    public static void main(String[] args) {
//        JFinal.start("src/main/webapp", 8899, "/", 5);
        UndertowServer.create(Config.class, "undertow.properties").start();
    }
}
