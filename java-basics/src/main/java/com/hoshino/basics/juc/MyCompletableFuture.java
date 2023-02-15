package com.hoshino.basics.juc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author huangyuehao
 * @date 2023-02-15
 */
public class MyCompletableFuture {

    private static final Integer CORE_POOL_SIZE = 6;

    private static final Integer MAXIMUM_POOL_SIZE = 10;

    private static final Long KEEP_ALIVE_TIME = 10L;

    private static final ArrayBlockingQueue<Runnable> ARRAY_BLOCKING_QUEUE = new ArrayBlockingQueue<>(20, true);

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().build();

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MINUTES,
                ARRAY_BLOCKING_QUEUE, THREAD_FACTORY);

        // 创建无返回值的异步任务，runAsync()
        for (int i = 0; i < 6; i++) {
            try {
                CompletableFuture.runAsync(() -> {
                    System.out.println(Thread.currentThread().getName() + " 正在运行 CompletableFuture.runAsync方法");
                }, threadPoolExecutor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("=================================================================");

        // 创建有返回值的异步任务，supplyAsync()
        for (int i = 0; i < 6; i++) {
            try {
                CompletableFuture<String> rFuture = CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName() + " 正在运行 CompletableFuture.supplyAsync方法");
                    return Thread.currentThread().getName() + " 已执行 CompletableFuture.supplyAsync方法";
                }, threadPoolExecutor);
                // 可以通过get或者join方法，阻塞等待并从CompletableFuture中获取对应的值,join()不抛异常的阻塞时获取执行结果
                System.out.println(rFuture.get());
            } catch (ExecutionException | InterruptedException e){
                e.printStackTrace();
            }
        }

        // 手动关闭线程池
        threadPoolExecutor.shutdown();

        // 异步回调
        // thenApply/thenApplyAsync
        // thenAccept/thenAcceptAsync
        // thenRun/thenRunAsync
        // thenCompose/thenComposeAsync

        // 组合-and
        // thenCombine/thenCombineAsync
        // thenAcceptBoth/thenAcceptBothAsync
        // runAfterBoth/runAfterBothAsync
        // allOf

        // 组合-or
        // applyToEither/applyToEitherAsync
        // acceptEither/acceptEitherAsync
        // runAfterEither/runAfterEitherAsync
        // anyOf

        // 异常
        // whenComplete/whenCompleteAsync
        // handle/handleAsync
        // exceptionally

    }


}
