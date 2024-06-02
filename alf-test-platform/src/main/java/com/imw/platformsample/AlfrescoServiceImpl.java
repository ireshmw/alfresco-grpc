//package com.imw.platformsample;
//
//import com.imw.alfresco.grpc.AlfrescoProto;
//import io.grpc.stub.StreamObserver;
//
//public class AlfrescoServiceImpl extends AlfrescoProtoGrpc. {
//    @Override
//    public void getDocument(AlfrescoProto.DocumentRequest request, StreamObserver<AlfrescoProto.DocumentResponse> responseObserver) {
//        String documentId = request.getDocumentId();
//        // Fetch document content using existing Alfresco logic
//        String content = "Fetched content for document ID: " + documentId;
//
//        AlfrescoProto.DocumentResponse response = AlfrescoProto.DocumentResponse.newBuilder()
//                .setContent(content)
//                .build();
//
//        responseObserver.onNext(response);
//        responseObserver.onCompleted();
//    }
//}