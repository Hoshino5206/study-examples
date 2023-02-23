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

    private static final ThreadPoolExecutor.AbortPolicy ABORT_POLICY = new ThreadPoolExecutor.AbortPolicy();

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MINUTES,
                ARRAY_BLOCKING_QUEUE, THREAD_FACTORY, ABORT_POLICY);

        System.out.println("=================================================================");
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
                // 可以通过get或者join方法，阻塞等待并从CompletableFuture中获取对应的值，join()不抛异常的阻塞时获取执行结果
                System.out.println(rFuture.get());
            } catch (ExecutionException | InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println("=================================================================");
        // thenApply()接收supplyAsync()的执行结果，返回的是CompletableFuture<String>
        try {
            CompletableFuture<String> future = CompletableFuture
                    .supplyAsync(() -> "hello", threadPoolExecutor)
                    .thenApply(s -> s + ", thenApply");
            System.out.println("thenApply result: " +future.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=================================================================");
        // thenAccept()接收supplyAsync()的执行结果，返回的是CompletableFuture<Void>
        try {
            CompletableFuture<Void> future = CompletableFuture
                    .supplyAsync(() -> "hello", threadPoolExecutor)
                    .thenAccept(s -> System.out.println(s + ", thenAccept"));
            System.out.println("thenAccept result: " + future.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=================================================================");
        // thenRun()不接收任务的结果，继续执行任务逻辑，返回的是CompletableFuture<Void>
        try {
            CompletableFuture<Void> future = CompletableFuture
                    .supplyAsync(() -> "hello , thenRun", threadPoolExecutor)
                    .thenRun(() -> {
                        System.out.println("thenRun ..............");
                    });
            System.out.println("thenRun result: " + future.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=================================================================");
        // thenCompose()，编排两个存在依赖关系的任务，和thenApply()的核心不同之处在于它们的返回值类型
        // thenApply()：返回计算结果的原始类型，比如返回String;
        // thenCompose()：返回CompletableFuture类型，比如返回CompletableFuture.
        try {
            CompletableFuture<String> future = CompletableFuture
                    .supplyAsync(() -> "hello", threadPoolExecutor)
                    .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + ", thenCompose"));
            System.out.println("thenCompose result: " + future.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=================================================================");
        // thenCombine()组合两个相互独立的任务
        try {
            CompletableFuture<Integer> positiveNumber = CompletableFuture.supplyAsync(() -> 500, threadPoolExecutor);
            CompletableFuture<Integer> negativeNumber = CompletableFuture.supplyAsync(() -> -400, threadPoolExecutor);

            CompletableFuture<Object> future = positiveNumber
                    .thenCombine(negativeNumber, (positive, negative) -> {
                        positive++;
                        negative--;
                        return positive + negative;
                    });
            System.out.println("thenCombine result: " + future.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
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
