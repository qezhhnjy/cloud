package com.cotek.boot.util.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author fuzy
 * create time 18-11-17-下午2:30
 */
public class Netty {
}

@Slf4j
class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ((ByteBuf) msg).release();

        ByteBuf in = (ByteBuf) msg;
        try {
            log.info(in.toString(CharsetUtil.UTF_8));
//            while (in.isReadable()) {
//                log.info(String.valueOf(in.readByte()));
//            }
        } finally {
//            in.release();
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = bootstrap.bind(port).sync();

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        new DiscardServer(port).run();
    }
}

class Client {
    public static void main(String[] args) throws IOException {
//        java.nio.channels.SocketChannel channel = java.nio.channels.SocketChannel.open(new InetSocketAddress("localhost", 8080));
        DatagramChannel open = DatagramChannel.open();
        open.bind(new InetSocketAddress("localhost", 8980));
        ByteBuffer b = ByteBuffer.allocate(50);
        b.put("womenshisnem".getBytes());
        b.flip();
        open.send(b, new InetSocketAddress("localhost", 8080));
    }
}
