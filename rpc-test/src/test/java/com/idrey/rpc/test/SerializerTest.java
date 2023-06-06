package com.idrey.rpc.test;

import com.idrey.rpc.api.HelloService;
import com.idrey.rpc.message.RpcRequest;
import com.idrey.rpc.serializer.HessianSerializer;
import com.idrey.rpc.serializer.KryoSerializer;
import com.idrey.rpc.serializer.RpcSerializer;

import java.util.UUID;

public class SerializerTest {

    public static void main(String[] args) {
        RpcSerializer serializer = new KryoSerializer();
        RpcRequest p = new RpcRequest();
//        p.setRequestId(UUID.randomUUID().toString());
        p.setHeartBeat(true);
        byte[] bytes = serializer.serialize(p);
        System.out.println(bytes.length);
        RpcRequest o = (RpcRequest) serializer.deserialize(bytes, RpcRequest.class);
        System.out.println(o.getHeartBeat());
//        System.out.println(HelloService.class.getName());
    }
}

