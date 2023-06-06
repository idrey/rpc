package com.idrey.rpc.codec;

import com.idrey.rpc.enumeration.Constants;
import com.idrey.rpc.enumeration.MessageType;
import com.idrey.rpc.message.RpcRequest;
import com.idrey.rpc.serializer.RpcSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcEncoder extends MessageToByteEncoder {

    private final RpcSerializer serializer;
    private static final Logger logger = LoggerFactory.getLogger(RpcEncoder.class);
    public RpcEncoder(RpcSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(Constants.MAGIC_NUMBER);
        if(o instanceof RpcRequest) {
            byteBuf.writeInt(MessageType.REQUEST_PACK.getCode());
        }
        else {
            byteBuf.writeInt(MessageType.RESPONSE_PACK.getCode());
        }
        byteBuf.writeInt(serializer.getCode());
        byte[] bytes = serializer.serialize(o);
        logger.info("The length is " + bytes.length);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }
}
