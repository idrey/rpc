package com.idrey.rpc.client;


import com.idrey.rpc.enumeration.RpcCode;
import com.idrey.rpc.enumeration.RpcError;
import com.idrey.rpc.exception.RpcException;
import com.idrey.rpc.message.RpcRequest;
import com.idrey.rpc.message.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class RpcClientProxy implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(RpcClientProxy.class);

    private final RpcClient client;

    public RpcClientProxy(RpcClient client) {
        this.client = client;
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("调用方法: {}#{}", method.getDeclaringClass().getName(), method.getName());
        RpcRequest rpcRequest = new RpcRequest(UUID.randomUUID().toString(), false, method.getDeclaringClass().getName(),
                method.getName(), args, method.getParameterTypes());
        RpcResponse rpcResponse = null;
        if(client instanceof NettyClient) {
            try {
                CompletableFuture<RpcResponse> completableFuture = (CompletableFuture<RpcResponse>) client.sendRequest(rpcRequest);
                rpcResponse = completableFuture.get();
            } catch (Exception e) {
                logger.error("Remote call send failed", e);
                return null;
            }
        }
        if(rpcResponse == null) {
            logger.error("Service invocation failed,serviceName:{}", rpcRequest.getInterfaceName());
            throw new RpcException(RpcError.SERVICE_INVOCATION_FAILURE, rpcRequest.getInterfaceName());
        }
        if(!rpcResponse.getRequestId().equals(rpcRequest.getRequestId())) {
            logger.error("Message id not match");
            throw new RpcException(RpcError.NOT_MATCH, rpcRequest.getInterfaceName());
        }
        if (rpcResponse.getCode() == null || !rpcResponse.getCode().equals(RpcCode.SUCCESS.getCode())) {
            logger.error("Service invocation failed,serviceName:{}", rpcRequest.getInterfaceName());
            throw new RpcException(RpcError.SERVICE_INVOCATION_FAILURE, rpcRequest.getInterfaceName());
        }
        return rpcResponse.getData();
    }
}
