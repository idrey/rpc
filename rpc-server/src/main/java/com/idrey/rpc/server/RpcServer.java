package com.idrey.rpc.server;

import com.idrey.rpc.enumeration.SerializerCode;

public interface RpcServer {

    int DEFAULT_SERIALIZER = SerializerCode.KRYO.getCode();

    void start();

    <T> void publishService(T service, String serviceName);

}
