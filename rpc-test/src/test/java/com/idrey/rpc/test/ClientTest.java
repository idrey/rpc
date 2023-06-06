package com.idrey.rpc.test;

import com.idrey.rpc.api.HelloService;
import com.idrey.rpc.client.NettyClient;
import com.idrey.rpc.client.RpcClient;
import com.idrey.rpc.client.RpcClientProxy;
import com.idrey.rpc.enumeration.SerializerCode;


public class ClientTest {
    public static void main(String[] args) {
        RpcClient client = new NettyClient(SerializerCode.KRYO.getCode());
        RpcClientProxy proxy = new RpcClientProxy(client);
        HelloService service = proxy.getProxy(HelloService.class);
        String res = service.hello("MM");
        System.out.println(res);
    }
}
