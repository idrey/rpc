package com.idrey.rpc.message;

import java.io.Serializable;

public class RpcRequest implements Serializable {
    private String requestId;
    private Boolean heartBeat;
    private String interfaceName;
    private String methodName;
    private Object[] params;
    private Class<?>[] paramTypes;

    public RpcRequest(String requestId, Boolean heartBeat, String interfaceName, String methodName, Object[] params, Class<?>[] paramTypes) {
        this.requestId = requestId;
        this.heartBeat = heartBeat;
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.params = params;
        this.paramTypes = paramTypes;
    }

    public RpcRequest() {
    }

    public Boolean getHeartBeat() {
        return heartBeat;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setHeartBeat(Boolean heartBeat) {
        this.heartBeat = heartBeat;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }
}
