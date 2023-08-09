package com.hoshino.basics.juc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.hoshino.basics.thread.MyThreadPool;

import java.util.concurrent.*;

/**
 * @author huangyuehao
 * @date 2023-02-15
 */
public class MyCompletableFuture {

    public static void main(String[] args) {
        // 创建无返回值的异步任务，runAsync()
        for (int i = 0; i < 6; i++) {
            try {
                CompletableFuture.runAsync(() -> {
                    System.out.println(Thread.currentThread().getName() + " 正在运行 CompletableFuture.runAsync方法");
                }, MyThreadPool.getExecutorService());
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
                }, MyThreadPool.getExecutorService());
                // 可以通过get或者join方法，阻塞等待并从CompletableFuture中获取对应的值，join()不抛异常的阻塞时获取执行结果
                String join = rFuture.join();
                System.out.println(rFuture.get());
            } catch (ExecutionException | InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("=================================================================");

        // thenApply()接收supplyAsync()的执行结果，返回的是CompletableFuture<String>
        try {
            CompletableFuture<String> future = CompletableFuture
                    .supplyAsync(() -> "hello", MyThreadPool.getExecutorService())
                    .thenApply(result -> result + ", thenApply");
            System.out.println("thenApply result: " +future.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=================================================================");

        // thenAccept()接收supplyAsync()的执行结果，返回的是CompletableFuture<Void>
        try {
            CompletableFuture<Void> future = CompletableFuture
                    .supplyAsync(() -> "hello", MyThreadPool.getExecutorService())
                    .thenAccept(result -> System.out.println(result + ", thenAccept"));
            System.out.println("thenAccept result: " + future.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=================================================================");

        // thenRun()不接收任务的结果，继续执行任务逻辑，返回的是CompletableFuture<Void>
        try {
            CompletableFuture<Void> future = CompletableFuture
                    .supplyAsync(() -> "hello , thenRun", MyThreadPool.getExecutorService())
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
                    .supplyAsync(() -> "hello", MyThreadPool.getExecutorService())
                    .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + ", thenCompose"));
            System.out.println("thenCompose result: " + future.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=================================================================");

        // thenCombine()组合两个相互独立的任务
        try {
            CompletableFuture<Integer> positiveNumber = CompletableFuture.supplyAsync(() -> 500, MyThreadPool.getExecutorService());
            CompletableFuture<Integer> negativeNumber = CompletableFuture.supplyAsync(() -> -400, MyThreadPool.getExecutorService());

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
        System.out.println("=================================================================");

        // 手动关闭线程池
        MyThreadPool.getExecutorService().shutdown();

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
