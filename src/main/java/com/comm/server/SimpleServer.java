package com.comm.server;

import com.comm.codec.SimpleMessagerDecoder;
import com.comm.codec.SimpleMessagerEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleServer {

    private static Logger logger = LoggerFactory.getLogger(SimpleServer.class);

    public void start(String host, int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(2, new DefaultThreadFactory("Boss"));
        EventLoopGroup workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors(), new DefaultThreadFactory("worker"));

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 10240)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel sh) {
                            ChannelPipeline p = sh.pipeline();
                            p.addLast(new SimpleMessagerEncoder());
                            p.addLast(new SimpleMessagerDecoder());
                            p.addLast(new SimpleServerHandler());
                        }
                    });
            ChannelFuture f = b.bind(host, port);
            f = f.sync();
            logger.info("Server start on {} {}", host, port);
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        SimpleServer simpleServer = new SimpleServer();
        simpleServer.start("0.0.0.0", 8081);
    }

}
