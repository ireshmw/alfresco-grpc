//package com.imw.platformsample;
//
//import com.linecorp.armeria.server.ServerBuilder;
//import com.linecorp.armeria.server.docs.DocService;
//import com.linecorp.armeria.server.grpc.GrpcService;
//import com.linecorp.armeria.server.logging.LoggingService;
//import io.grpc.protobuf.services.ProtoReflectionService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import com.linecorp.armeria.server.Server;
//
//public class GrpcSubSystem implements ApplicationContextAware, InitializingBean {
//    private static final Logger log = LoggerFactory.getLogger(GrpcSubSystem.class);
//    private ApplicationContext applicationContext;
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//
////        armeriaServer = Server.builder()
////                .service(GrpcService.builder()
////                        .addService(new GreeterService())
////                        .build())
////                .http(8089)
////                .serviceUnder("/docs", new DocService())
////                .decorator(LoggingService.newDecorator())
////                // Add this for request/response logging
////                .build();
//
//        ServerBuilder sb = Server.builder();
//        sb.http(8089)
//                .service(GrpcService.builder()
//                .addService(new GreeterService())
//                .addService(ProtoReflectionService.newInstance())
//                .build());
//
//        Server server = sb.build();
//        log.info("GRPC Sub system-----{}", server.toString());
//        server.start();
//        log.info("GRPC Sub system-----{}", server.toString());
//
//    }
//}
