package com.imw.platformsample;

import com.imw.proto.HelloWorldRequest;
import com.imw.proto.HelloWorldServiceGrpc;
import com.linecorp.armeria.common.SessionProtocol;
import com.linecorp.armeria.common.grpc.GrpcSerializationFormats;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.ServerPort;
import com.linecorp.armeria.server.docs.DocService;
import com.linecorp.armeria.server.docs.DocServiceFilter;
import com.linecorp.armeria.server.logging.LoggingService;
import io.grpc.protobuf.services.ProtoReflectionService;
import io.grpc.reflection.v1alpha.ServerReflectionGrpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.grpc.GrpcService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Slf4j

public class GrpcServer {
//    private Server server;
//
//    // to check available services list
//    // grpcurl --plaintext localhost:50051 list
//    public void start() throws IOException {
//        int port = 50051;
//        server = ServerBuilder.forPort(port)
//                .addService(new GreeterService())
//                .addService(ProtoReflectionService.newInstance())  // Add reflection service
//                .build()
//                .start();
//        log.info("Server started, listening on {}", port);
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            System.err.println("*** shutting down gRPC server since JVM is shutting down");
//            GrpcServer.this.stop();
//            System.err.println("*** server shut down");
//        }));
//    }
//
//    public void stop() {
//        if (server != null) {
//            server.shutdown();
//        }
//    }
//
//    public void blockUntilShutdown() throws InterruptedException {
//        if (server != null) {
//            server.awaitTermination();
//        }
//    }

//    @Bean
//    public void armeriaServer() {
//        final GrpcService grpcService = GrpcService.builder()
//                .addService(new GreeterService())
//                .addService(ProtoReflectionService.newInstance())
//                .enableHttpJsonTranscoding(true) // enable http json transcoding
//                .build();
//        final ServerBuilder sb = Server.builder();
//        sb.service(grpcService);
//        sb.port(new ServerPort(8089, SessionProtocol.HTTP));
//        try (Server server = sb.build()) {
//            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//                server.stop().join();
//            }));
//            server.start().join();
//            log.info("GRPC Sub system-----{}", server.toString());
//        }
//    }

//    @Bean
//    public void armeriaServer() throws Exception {
//        final Server server = newServer(8089, 8443);
//        server.closeOnJvmShutdown();
//        server.start().join();
//        log.info("Server has been started. Serving DocService at http://127.0.0.1:{}/docs",
//                server.activeLocalPort());
//    }

    private Server server;

    // to check available services list
    // grpcurl --plaintext localhost:50051 list
    public void start() throws Exception {

        server = newServer(50051, 8443);
        server.closeOnJvmShutdown();
        server.start().join();
        log.info("Server has been started. Serving DocService at http://127.0.0.1:{}/docs",
                server.activeLocalPort());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            GrpcServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    public void stop() {
        if (server != null) {
            server.stop();
        }
    }

    private static Server newServer(int httpPort, int httpsPort) throws Exception {
        final ServerBuilder sb = Server.builder();
        sb.port(httpPort, SessionProtocol.HTTP);
//        sb.http(httpPort)
//                .https(httpsPort)
//                .tlsSelfSigned();
        configureServices(sb);
        return sb.build();
    }

    static void configureServices(ServerBuilder sb) {
        final HelloWorldRequest exampleRequest = HelloWorldRequest.newBuilder().build();
        final GrpcService grpcService =
                GrpcService.builder()
                        .addService(new GreeterService())
                        // See https://github.com/grpc/grpc-java/blob/master/documentation/server-reflection-tutorial.md
                        .addService(ProtoReflectionService.newInstance())
                        .supportedSerializationFormats(GrpcSerializationFormats.values())
                        .enableHttpJsonTranscoding(true)
                        .enableUnframedRequests(true)
                        // You can set useBlockingTaskExecutor(true) in order to execute all gRPC
                        // methods in the blockingTaskExecutor thread pool.
                        // .useBlockingTaskExecutor(true)
                        .build();
        sb.service(grpcService);
//                .service("prefix:/prefix", grpcService)
//                // You can access the documentation service at http://127.0.0.1:8080/docs.
//                // See https://armeria.dev/docs/server-docservice for more information.
//                .serviceUnder("/docs",
//                        DocService.builder()
//                                .exampleRequests(HelloWorldServiceGrpc.SERVICE_NAME,
//                                        "Hello", exampleRequest)
//                                .exampleRequests(HelloWorldServiceGrpc.SERVICE_NAME,
//                                        "LazyHello", exampleRequest)
//                                .exampleRequests(HelloWorldServiceGrpc.SERVICE_NAME,
//                                        "BlockingHello", exampleRequest)
//                                .exclude(DocServiceFilter.ofServiceName(
//                                        ServerReflectionGrpc.SERVICE_NAME))
//                                .build());
    }


//    @Bean
//    public Server armeriaServer() {
//        return Server.builder()
//                .service(GrpcService.builder()
//                        .addService(new GreeterService())
//                        .build())
//                .http(8089)
//                .serviceUnder("/docs", new DocService())
//                .decorator(LoggingService.newDecorator()) // Add this for request/response logging
//                .build();
//    }

}