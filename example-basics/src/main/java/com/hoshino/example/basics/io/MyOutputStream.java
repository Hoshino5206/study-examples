package com.hoshino.example.basics.io;

import org.springframework.util.StopWatch;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Random;

/**
 * @author huangyuehao
 * @date 2022-11-04
 */
public class MyOutputStream {

    public static void main(String[] args) throws IOException {
        fileOutputStreamTest();
        bufferedOutputStreamTest();
    }

    /**
     * FileOutputStream写文件，分别为单个字节写、字节数组写
     * @throws IOException
     */
    private static void fileOutputStreamTest() throws IOException {
        OutputStream fos = null;
        StopWatch sw = new StopWatch();
        sw.start();

        try {
            // 第二个参数表示文件是否支持追加，false表示重置文件内容，true表示文件内容追加
            fos = new FileOutputStream("/Users/huangyuehao/Documents/IdeaProjects/study-examples/properties1.txt", false);
            for (int i = 0; i < 10000; i++) {
                // username1 = 1000以内的随机数
                String str = "username_num" + i + "=" + new Random().nextInt(10000);
                // getBytes()方法把字符串转为字节数组
                byte[] bytes = str.getBytes();
                fos.write(bytes);
                fos.write('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(fos)) {
                fos.close();
            }
        }
        sw.stop();
        System.out.println("sw.getTotalTimeMillis() = " + sw.getTotalTimeMillis());
    }

    /**
     * BufferedOutputStream有一个内部缓冲区数组，一次性读取较多的字节缓存起来，默认读取大小为 8192，作用于写文件时可以提高性能。
     * @throws IOException
     */
    private static void bufferedOutputStreamTest() throws IOException {
        OutputStream fos = null;
        OutputStream bos = null;
        StopWatch sw = new StopWatch();
        sw.start();

        try {
            // 第二个参数表示文件是否支持追加，false表示重置文件内容，true表示文件内容追加
            fos = new FileOutputStream("/Users/huangyuehao/Documents/IdeaProjects/study-examples/properties2.txt", false);
            bos = new BufferedOutputStream(fos);
            for (int i = 0; i < 10000; i++) {
                // username1 = 1000以内的随机数
                String str = "username_num" + i + "=" + new Random().nextInt(10000);
                // getBytes()方法把字符串转为字节数组
                byte[] bytes = str.getBytes();
                bos.write(bytes);
                bos.write('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(bos)) {
                bos.close();
            }
            if (Objects.nonNull(fos)) {
                fos.close();
            }
        }

        sw.stop();
        System.out.println("sw.getTotalTimeMillis() = " + sw.getTotalTimeMillis());
        System.out.println("==============================");
    }

}
