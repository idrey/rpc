package com.idrey.rpc.enumeration;

public enum RpcCode {
    SUCCESS(200, "Success"),
    FAIL(500, "Failed"),
    METHOD_NOT_FOUND(500, "Method not found");
    private final int code;
    private final String message;

    RpcCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
