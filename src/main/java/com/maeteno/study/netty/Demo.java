package com.maeteno.study.netty;

import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;


public class Demo {
    public static void main(String[] args) {
        Channel channel = new NioDatagramChannel();

        ChannelFuture future = channel.connect(new InetSocketAddress("127.0.0.1", 8080));

        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    CompositeByteBuf buffer = Unpooled.compositeBuffer();
                    ChannelFuture wf = channelFuture.channel().write(buffer);
                }
            }
        });
    }
}
