package com.idrey.rpc.enumeration;

import org.omg.CORBA.UNKNOWN;

public enum RpcError {
    UNKNOWN_PROTOCOL("Protocol Not Match"),
    UNKNOWN_MESSAGE_TYPE("Unknown Message Type"),
    UNKNOWN_SERIALIZER("Unknown serializer"),
    FAILED_TO_CONNECT_TO_SERVICE_REGISTRY("Connect to nacos service failed"),
    SERVICE_NOT_FOUND("Service not found"),
    SERIALIZER_NOT_FOUND("Serializer not found"),
    REGISTER_SERVICE_FAILED("Register service failed"),
    SERVICE_INVOCATION_FAILURE("Service invocation failed"),
    NOT_MATCH("Request id not match");

    private final String message;
    RpcError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
