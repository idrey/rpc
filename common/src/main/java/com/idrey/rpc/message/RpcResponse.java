package com.idrey.rpc.message;

import com.idrey.rpc.enumeration.RpcCode;

import java.io.Serializable;

public class RpcResponse implements Serializable {
    private String requestId;
    private Integer code;
    private String message;
    private Object data;

    public String getRequestId() {
        return requestId;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static RpcResponse success(Object data, String requestId) {
        RpcResponse response = new RpcResponse();
        response.setRequestId(requestId);
        response.setCode(RpcCode.SUCCESS.getCode());
        response.setData(data);
        return response;
    }

    public static RpcResponse fail(RpcCode code, String requestId) {
        RpcResponse response = new RpcResponse();
        response.setRequestId(requestId);
        response.setCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }

}
