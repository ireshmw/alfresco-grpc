package com.imw.platformsample;

import org.springframework.context.ApplicationEvent;
import org.springframework.extensions.surf.util.AbstractLifecycleBean;

import java.io.IOException;

public class GrpcLifecycleBean extends AbstractLifecycleBean {

    private GrpcServer grpcServer;

    @Override
    protected void onBootstrap(ApplicationEvent event) {
        grpcServer = new GrpcServer();
        try {
            log.info("Trying to start the server--------");
            grpcServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onShutdown(ApplicationEvent event) {
        grpcServer.stop();
    }
}