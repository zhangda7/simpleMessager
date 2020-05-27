package com.comm.client;

import com.comm.codec.SimpleMessagerDecoder;
import com.comm.codec.SimpleMessagerEncoder;
import com.comm.model.DataMessage;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleClient {

    private static Logger logger = LoggerFactory.getLogger(SimpleClient.class);

    private Bootstrap clientBootStrap;

    private EventLoopGroup group = new NioEventLoopGroup(1);

    private volatile Channel channel;

    private String host;

    private int port;

    public SimpleClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        this.clientBootStrap = new Bootstrap();
        clientBootStrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel sh) {
                        ChannelPipeline p = sh.pipeline();
                        p.addLast(new SimpleMessagerEncoder());
                        p.addLast(new SimpleMessagerDecoder());
                        p.addLast(new SimpleClientHandler());
                    }
                });

        ChannelFuture future;
        try {
            future = clientBootStrap.connect(host, port).sync();
            channel = future.channel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(DataMessage dataMessage) {
        this.channel.writeAndFlush(dataMessage);
    }

}
