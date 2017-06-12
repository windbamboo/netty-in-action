package com.weituitu.chapter04.jvmlocal;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.local.LocalChannel;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.local.LocalServerChannel;

/**
 * Created by Sundy on 2017/6/8.
 */
public class JvmLocalServer {

    private String localAddress;
    public JvmLocalServer(String localAddress) {
        this.localAddress = localAddress;
    }

    public void start() throws InterruptedException {
        EventLoopGroup eventLoopGroup = new LocalEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(eventLoopGroup);
            b.channel(LocalServerChannel.class);
            b.childHandler(new ChannelInitializer<LocalChannel>() {
                @Override protected void initChannel(LocalChannel ch) throws Exception {
                   // ch.pipeline().addLast(new LocalServerHandler());
                }
            });
            LocalAddress address = new LocalAddress(this.localAddress);
            b.bind(address).addListener(new ChannelFutureListener() {
                @Override public void operationComplete(ChannelFuture future) throws Exception {
                    System.out.println("local server successively bind");
                }
            });
        } catch (Exception e) {
            System.out.println("error !" + e);
        }
    }
}
