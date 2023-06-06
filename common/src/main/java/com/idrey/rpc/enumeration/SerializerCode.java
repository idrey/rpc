package com.idrey.rpc.enumeration;

public enum SerializerCode {
    KRYO(0),
    HESSIAN(1);
    private final int code;

    SerializerCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
