package com.weituitu.chapter04.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 *
 */
public class EchoServerInHandler2 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        //将接收到的消息写给发送者，而不冲刷出站消息
        byte[] length = new byte[in.readableBytes()];
        in.readBytes(length);
        String resultStr = new String(length);
        System.out.println("Inbound2 handler:" + resultStr);
        in.release();
        ctx.write(in);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印异常栈跟踪
        cause.printStackTrace();
        //关闭该Channel
        ctx.close();
    }
}
