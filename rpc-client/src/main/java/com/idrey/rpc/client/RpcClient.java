package com.idrey.rpc.client;

import com.idrey.rpc.enumeration.SerializerCode;
import com.idrey.rpc.message.RpcRequest;
import com.idrey.rpc.serializer.RpcSerializer;

public interface RpcClient {
    int DEFAULT_SERIALIZER = SerializerCode.KRYO.getCode();

    Object sendRequest(RpcRequest rpcRequest);

}
