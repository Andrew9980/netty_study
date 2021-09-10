package org.andrew.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @author: siqing.xu
 * @description:
 * @since: 1.0
 * @create: 2021-08-31 18:42
 */
@Slf4j
public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        log.info("开始");
        Future<Integer> future = eventExecutors.submit(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        future.addListener(future1 -> log.info("{}", future1.get()));
        log.info("结束");
    }

}
