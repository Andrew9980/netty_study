package org.andrew.nio;

import io.netty.buffer.ByteBufUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author siqing.xu
 * @version 1.0
 * @desc
 * @date 2021/8/8 20:30
 * @since 1.0
 */
public class TestChannel {

    public static void main(String[] args) {
        try {
            FileInputStream in = new FileInputStream("C:\\Users\\Andrew\\Documents\\projects\\netty_study\\src\\main\\resources\\data.txt");
            FileChannel channel = in.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (channel.read(buffer) > -1) {
                buffer.flip();
                buffer.compact();
                while (buffer.hasRemaining()) {
                    byte[] bytes = new byte[10];
                    buffer.get(bytes, 0, buffer.remaining());
                    System.out.println(new String(bytes));
                }
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
