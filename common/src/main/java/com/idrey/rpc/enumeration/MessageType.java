package com.idrey.rpc.enumeration;

public enum MessageType {
    REQUEST_PACK(0),
    RESPONSE_PACK(1);

    private final int code;

    MessageType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
