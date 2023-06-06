package com.idrey.rpc.test;

import com.idrey.rpc.api.HelloService;
import com.idrey.rpc.enumeration.SerializerCode;
import com.idrey.rpc.server.NettyServer;
import com.idrey.rpc.server.RpcServer;

public class ServerTest {
    public static void main(String[] args) {
        RpcServer server = new NettyServer("127.0.0.1", 8201, SerializerCode.KRYO.getCode());
        HelloService helloService = new HelloServiceImpl();
        server.publishService(helloService, HelloService.class.getName());
        server.start();
    }
}
