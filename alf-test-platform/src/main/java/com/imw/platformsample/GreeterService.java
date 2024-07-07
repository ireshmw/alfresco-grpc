package com.imw.platformsample;

import com.imw.proto.HelloWorldRequest;
import com.imw.proto.HelloWorldResponse;
import com.imw.proto.HelloWorldServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreeterService extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(GreeterService.class);

    @Override
    public void helloWorld(HelloWorldRequest request, StreamObserver<HelloWorldResponse> responseObserver) {
        HelloWorldResponse setResponseMessage = HelloWorldResponse.newBuilder()
                .setResponseMessage("Hello------------------fsfs " + request.getClientName() + " !!!").build();
        logger.info(String.format("%1s sent a message: %1s", request.getClientName(),request.getRequestMessage()));
        responseObserver.onNext(setResponseMessage);
        responseObserver.onCompleted();
    }

}