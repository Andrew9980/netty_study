package org.andrew.ws;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.EventExecutorGroup;

import java.time.LocalDateTime;

/**
 * @author siqing.xu
 * @version 1.0
 * @desc 处理文本协议数据
 * @date 2021/8/17 19:29
 * @since 1.0
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 读取客户端数据，并写入客户端
     * @param channelHandlerContext
     * @param textWebSocketFrame
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        System.out.println("收到客户端消息：" + textWebSocketFrame.text());

        channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame("服务时间：" + LocalDateTime.now()));
    }

    //每个channel都有一个唯一的id值
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //打印出channel唯一值，asLongText方法是channel的id的全名
        System.out.println("handlerAdded："+ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved：" + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生");
        ctx.close();
    }
}
