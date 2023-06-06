package com.idrey.rpc.service;

import com.alibaba.nacos.api.exception.NacosException;
import com.idrey.rpc.enumeration.RpcError;
import com.idrey.rpc.exception.RpcException;
import com.idrey.rpc.util.NacosUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class NacosServiceRegistry implements ServiceRegistry{

    private static final Logger logger = LoggerFactory.getLogger(NacosServiceRegistry.class);

    @Override
    public void register(String serviceName, InetSocketAddress inetSocketAddress) {
        try {
            NacosUtil.registerService(serviceName, inetSocketAddress);
        } catch (NacosException e) {
            logger.error("Register Service Error", e);
            throw new RpcException(RpcError.REGISTER_SERVICE_FAILED);
        }
    }
}
