package com.weituitu.chapter04.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 *
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        int port = 8888;
        //调用服务器的 start()方法
        new EchoServer(port).start();
    }

    private void start() throws Exception {
        final EchoServerInHandler1 serverInHandler1 = new EchoServerInHandler1();
        final EchoServerInHandler2 serverInHandler2 = new EchoServerInHandler2();
        final EchoServerOutHandler1 serverOutHandler1 = new EchoServerOutHandler1();
        final EchoServerOutHandler2 serverOutHandler2 = new EchoServerOutHandler2();
        //(1) 创建EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //(2) 创建ServerBootstrap
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    //(3) 指定所使用的 NIO 传输 Channel
                    .channel(NioServerSocketChannel.class)
                    //(4) 使用指定的端口设置套接字地址
                    .localAddress(new InetSocketAddress(port))
                    //(5) 添加一个EchoServerHandler到于Channel的 ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //EchoServerHandler 被标注为@Shareable，所以我们可以总是使用同样的实例
                            //这里对于所有的客户端连接来说，都会使用同一个 EchoServerHandler，因为其被标注为@Sharable，
                            //这将在后面的章节中讲到。
                            ch.pipeline().addLast(serverOutHandler1);
                            ch.pipeline().addLast(serverOutHandler2);

                            ch.pipeline().addLast(serverInHandler1);
                            ch.pipeline().addLast(serverInHandler2);

                        }
                    });
            //(6) 异步地绑定服务器；调用 sync()方法阻塞等待直到绑定完成
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() + " started and listening for connections on" + f.channel().localAddress());
            //(7) 获取 Channel 的CloseFuture，并且阻塞当前线程直到它完成
            f.channel().closeFuture().sync();
            System.out.println("==================7");
        } finally {
            //(8) 关闭 EventLoopGroup，释放所有的资源
            group.shutdownGracefully().sync();
            System.out.println("==================8");

        }
    }
}
