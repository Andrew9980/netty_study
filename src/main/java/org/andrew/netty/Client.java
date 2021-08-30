package org.andrew.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: siqing.xu
 * @description: 客户端
 * @since: 1.0
 * @create: 2021-08-25 16:32
 */
@Slf4j
public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {

        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ClientHandler())
                .connect("localhost", 9999)
                .addListener((ChannelFutureListener) future -> {
                    if (future.isSuccess()) {
                        log.info("连接成功：");
                        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello", Charset.defaultCharset());
                        future.channel().writeAndFlush(byteBuf);
                    } else {
                        log.info("连接出错：{}", future.cause().getMessage());
                    }
                });

        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            channelFuture.channel().writeAndFlush(line);
        }).start();

        channelFuture.channel().closeFuture().sync();
    }

}
