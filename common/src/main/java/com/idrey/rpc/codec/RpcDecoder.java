package com.idrey.rpc.codec;

import com.idrey.rpc.enumeration.Constants;
import com.idrey.rpc.enumeration.MessageType;
import com.idrey.rpc.enumeration.RpcError;
import com.idrey.rpc.exception.RpcException;
import com.idrey.rpc.message.RpcRequest;
import com.idrey.rpc.message.RpcResponse;
import com.idrey.rpc.serializer.RpcSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RpcDecoder extends ReplayingDecoder {
    private static final Logger logger = LoggerFactory.getLogger(RpcDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int magicNumber = byteBuf.readInt();
        if(magicNumber != Constants.MAGIC_NUMBER) {
            logger.error("Magic number not match: {}", magicNumber);
            throw new RpcException(RpcError.UNKNOWN_PROTOCOL);
        }
        int messageType = byteBuf.readInt();
        Class<?> messageClass;
        if(messageType == MessageType.REQUEST_PACK.getCode()) {
            messageClass = RpcRequest.class;
        } else if(messageType == MessageType.RESPONSE_PACK.getCode()) {
            messageClass = RpcResponse.class;
        } else {
            logger.error("Unknown Message Type: {}", messageType);
            throw new RpcException(RpcError.UNKNOWN_MESSAGE_TYPE);
        }
        int serializerCode = byteBuf.readInt();
        RpcSerializer serializer = RpcSerializer.getByCode(serializerCode);
        if(serializer == null) {
            logger.error("Unknown serializer: {}", serializerCode);
            throw new RpcException(RpcError.UNKNOWN_SERIALIZER);
        }
        int length = byteBuf.readInt();
        logger.info("The length is " + length);
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Object obj = serializer.deserialize(bytes, messageClass);
        list.add(obj);
    }
}
