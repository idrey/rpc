package com.idrey.rpc.serializer;

import com.idrey.rpc.enumeration.SerializerCode;

public interface RpcSerializer {

    static RpcSerializer getByCode(int code) {
        switch (code) {
            case 0:
                return new KryoSerializer();
            case 1:
                return new HessianSerializer();
            default:
                return null;
        }
    }

    byte[] serialize(Object obj);

    Object deserialize(byte[] bytes, Class<?> clazz);

    int getCode();
}
