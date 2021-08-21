package org.andrew.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author siqing.xu
 * @version 1.0
 * @desc
 * @date 2021/8/18 22:29
 * @since 1.0
 */
public class Client {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9999));
        SocketAddress localAddress = socketChannel.getLocalAddress();
        System.out.println("local address" + localAddress);
    }

}
