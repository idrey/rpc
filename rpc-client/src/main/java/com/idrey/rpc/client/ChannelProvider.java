package com.idrey.rpc.client;

import com.idrey.rpc.codec.RpcDecoder;
import com.idrey.rpc.codec.RpcEncoder;
import com.idrey.rpc.serializer.RpcSerializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ChannelProvider {
    private static final Logger logger = LoggerFactory.getLogger(ChannelProvider.class);
    private static EventLoopGroup eventLoopGroup;
    private static Bootstrap bootstrap = initializeBootstrap();
    private static Map<String, Channel> channels = new ConcurrentHashMap<>();

    public static Channel get(InetSocketAddress inetSocketAddress, RpcSerializer serializer) {
        String key = inetSocketAddress.toString() + serializer.getCode();
        if(channels.containsKey(key)) {
            Channel channel = channels.get(key);
            if(channel != null && channel.isActive()) {
                return channel;
            } else {
                channels.remove(key);
            }
        }
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new RpcEncoder(serializer))
                        .addLast(new IdleStateHandler(0, 5, 0))
                        .addLast(new RpcDecoder())
                        .addLast(new NettyClientHandler());
            }
        });
        Channel channel = null;
        try {
            channel = connect(bootstrap, inetSocketAddress);
        } catch (ExecutionException | InterruptedException e) {
            logger.error("Client connect error", e);
            return null;
        }
        channels.put(key, channel);
        return channel;
    }
    public static void close() {
        eventLoopGroup.shutdownGracefully();
    }

    private static Channel connect(Bootstrap bootstrap, InetSocketAddress inetSocketAddress) throws ExecutionException, InterruptedException {
        CompletableFuture<Channel> completableFuture = new CompletableFuture<>();
        bootstrap.connect(inetSocketAddress).addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                logger.info("Client connect success");
                completableFuture.complete(future.channel());
            } else {
                throw new IllegalStateException();
            }
        });
        return completableFuture.get();
    }

    private static Bootstrap initializeBootstrap() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Shutdown channels");
            ChannelProvider.close();
        }));
        eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                //Timeout
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                //HeartBeat
                .option(ChannelOption.SO_KEEPALIVE, true)
                //Nagle
                .option(ChannelOption.TCP_NODELAY, true);
        return bootstrap;
    }

}
