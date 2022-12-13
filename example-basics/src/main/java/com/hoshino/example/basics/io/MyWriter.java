package com.hoshino.example.basics.io;

import org.apache.commons.lang3.time.StopWatch;

import java.io.*;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author huangyuehao
 * @date 2022-11-04
 */
public class MyWriter {

    public static void main(String[] args) throws IOException {
        outputStreamWriterTest();
        fileWriterTest();
        bufferedWriter();
    }

    /**
     * OutputStreamWriter 是一个连接字节流和字符流的桥梁，它将字节流转变为字符流。
     * @throws IOException
     */
    private static void outputStreamWriterTest() throws IOException {
        OutputStream fos = null;
        Writer w = null;
        StopWatch sw = new StopWatch();
        sw.start();

        try {
            fos = new FileOutputStream("example-basics/src/main/resources/io/properties1.txt");
            w = new OutputStreamWriter(fos);
            for (int i = 0; i < 10000; i++) {
                // username1 = 1000以内的随机数
                String str = "username_num" + i + "=" + new Random().nextInt(10000);
                // getBytes()方法把字符串转为字节数组
                char[] chars = str.toCharArray();
                w.write(chars);
                w.write('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(w)) {
                w.close();
            }
            if (Objects.nonNull(fos)) {
                fos.close();
            }
        }

        sw.stop();
        System.out.println("sw.getTotalTimeMillis() = " + sw.getTime(TimeUnit.MILLISECONDS));
        System.out.println("==============================");
    }

    /**
     * FileWriter、FileReader 可以用来读写一个含中文字符的文件。
     * @throws IOException
     */
    private static void fileWriterTest() throws IOException{
        Writer fr = null;
        StopWatch sw = new StopWatch();
        sw.start();

        try {
            fr = new FileWriter("example-basics/src/main/resources/io/properties1.txt", false);
            for (int i = 0; i < 10000; i++) {
                // username1 = 1000以内的随机数
                String str = "username_num" + i + "=" + new Random().nextInt(10000);
                // getBytes()方法把字符串转为字节数组
                char[] chars = str.toCharArray();
                fr.write(chars);
                fr.write('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(fr)) {
                fr.close();
            }
        }

        sw.stop();
        System.out.println("sw.getTotalTimeMillis() = " + sw.getTime(TimeUnit.MILLISECONDS));
        System.out.println("==============================");
    }

    private static void bufferedWriter() throws IOException {
        Writer fr = null;
        Writer bfr = null;
        StopWatch sw = new StopWatch();
        sw.start();

        try {
            fr = new FileWriter("example-basics/src/main/resources/io/properties2.txt", false);
            bfr = new BufferedWriter(fr);
            for (int i = 0; i < 10000; i++) {
                // username1 = 1000以内的随机数
                String str = "username_num" + i + "=" + new Random().nextInt(10000);
                // getBytes()方法把字符串转为字节数组
                char[] chars = str.toCharArray();
                bfr.write(chars);
                bfr.write('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(bfr)) {
                bfr.close();
            }
            if (Objects.nonNull(fr)) {
                fr.close();
            }
        }

        sw.stop();
        System.out.println("sw.getTotalTimeMillis() = " + sw.getTime(TimeUnit.MILLISECONDS));
        System.out.println("==============================");
    }

}
