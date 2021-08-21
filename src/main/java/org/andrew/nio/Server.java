package org.andrew.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author siqing.xu
 * @version 1.0
 * @desc
 * @date 2021/8/18 21:59
 * @since 1.0
 */
public class Server {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.bind(new InetSocketAddress(9999));
        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("selectionKey: " + selectionKey);

        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    System.out.println("客户端接入：" + channel);
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    SelectionKey sk = sc.register(selector, SelectionKey.OP_READ);
                    System.out.println("开始读取客户端数据：" + sk);
                } else if (key.isReadable()) {
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int read = channel.read(byteBuffer);
                        if (read == -1) {
                            key.cancel(); // 接口到客户端关闭事件
                            System.out.println("客户端断开：" + key);
                        } else {
                            byteBuffer.flip();
                            System.out.println("客户端发过来的数据：" + Charset.defaultCharset().decode(byteBuffer).toString());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        key.cancel(); // 从selector管理channel中移除，selector不再监听此channel的事件
                    }
                }
                iterator.remove(); // 从channel的selectedKeys中删除事件
            }
        }


    }

}
