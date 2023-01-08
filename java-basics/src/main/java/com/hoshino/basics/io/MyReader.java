package com.hoshino.basics.io;

import org.apache.commons.lang3.time.StopWatch;

import java.io.*;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author huangyuehao
 * @date 2022-11-04
 */
public class MyReader {

    public static void main(String[] args) throws IOException {
        inputStreamReaderTest();
        fileReaderTest();
        bufferedReaderTest();
    }

    /**
     * InputStreamReader 是一个连接字节流和字符流的桥梁，它将字节流转变为字符流。
     * @throws IOException
     */
    private static void inputStreamReaderTest() throws IOException {
        Properties properties = new Properties();
        InputStream in = null;
        Reader r = null;

        try {
            in = MyReader.class.getClassLoader().getResourceAsStream("io/default1.properties");
            r = new InputStreamReader(in);
            properties.load(r);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(r)) {
                r.close();
            }
            if (Objects.nonNull(in)) {
                in.close();
            }
        }

        String username = properties.getProperty("username");
        System.out.println("username = " + username);
        String password = properties.getProperty("password");
        System.out.println("password = " + password);
        System.out.println("==============================");
    }

    /**
     * FileWriter、FileReader 可以用来读写一个含中文字符的文件。
     * @throws IOException
     */
    private static void fileReaderTest() throws IOException {
        Properties properties = new Properties();
        Reader reader = null;
        StopWatch sw = new StopWatch();
        sw.start();

        try {
            reader = new FileReader("example-basics/src/main/resources/io/properties1.txt");
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(reader)) {
                reader.close();
            }
        }

        sw.stop();
        String property = properties.getProperty("username_num10");
        System.out.println("username_num10 = " + property);
        System.out.println("sw.getTotalTimeMillis() = " + sw.getTime(TimeUnit.MILLISECONDS));
        System.out.println("==============================");
    }

    /**
     * BufferedReader 很明显就是一个装饰器，它和其子类负责装饰其它 Reader 对象。
     * @throws IOException
     */
    private static void bufferedReaderTest() throws IOException {
        Properties properties = new Properties();
        Reader reader = null;
        Reader br = null;
        StopWatch sw = new StopWatch();
        sw.start();

        try {
            reader = new FileReader("example-basics/src/main/resources/io/properties2.txt");
            br = new BufferedReader(reader);
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(br)) {
                br.close();
            }
            if (Objects.nonNull(reader)) {
                reader.close();
            }
        }

        sw.stop();
        String property = properties.getProperty("username_num10");
        System.out.println("username_num10 = " + property);
        System.out.println("sw.getTotalTimeMillis() = " + sw.getTime(TimeUnit.MILLISECONDS));
        System.out.println("==============================");
    }

}
