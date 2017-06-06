package com.weituitu.chapter01;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by liuguozhu on 2017/5/24.
 */
public class ConnectHandler extends ChannelInboundHandlerAdapter{
    @Override
    //当一个新的连接已经被建立时，channelActive(ChannelHandlerContext)将会被调用
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }
}
