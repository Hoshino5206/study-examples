package com.hoshino.example.basics.io;

import org.springframework.util.StopWatch;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * @author huangyuehao
 * @date 2022-11-04
 */
public class MyInputStream {

    public static void main(String[] args) throws IOException {
        inputStreamTest();
        fileInputStreamTest();
        bufferedInputStreamTest();
    }

    /**
     * 读取类路径下的配置文件、单个字节读取、字节数组读取、一次性读取
     * @throws IOException
     */
    private static void inputStreamTest() throws IOException {
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = MyInputStream.class.getClassLoader().getResourceAsStream("io/default1.properties");
            properties.load(in);

            // 1.单字节读，一个字节一个字节的读出数据
//            int by = 0;
//            while ((by = in.read()) != -1) {
//                System.out.print((char) by);
//            }

            // 2.一个字节数组的读出数据，高效
//            int by = 0;
//            byte[] bytes = new byte[10];
//            while ((by = in.read(bytes)) != -1) {
//                for (int i = 0; i < by; i++) {
//                    System.out.print((char) bytes[i]);
//                }
//            }

            // 3.一次性读取
//            int iAvail = in.available();
//            int by = 0;
//            byte[] bytesAll = new byte[iAvail];
//            while ((by = in.read(bytesAll)) != -1) {
//                for (int i = 0; i < by; i++) {
//                    System.out.print((char) bytesAll[i]);
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
     * 读取外部路径文件
     * @throws IOException
     */
    private static void fileInputStreamTest() throws IOException {
        Properties properties = new Properties();
        InputStream in = null;
        StopWatch sw = new StopWatch();
        sw.start();

        try {
            //绝对路径
            //in = new FileInputStream("/Users/huangyuehao/Desktop/IdeaProjects/study-examples/properties1.txt");
            //相对路径
            //in = new FileInputStream("properties1.txt");
            in = new FileInputStream("example-basics/src/main/resources/io/properties1.txt");
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
        }

        sw.stop();
        String property = properties.getProperty("username_num10");
        System.out.println("username_num10 = " + property);
        System.out.println("sw.getTotalTimeMillis() = " + sw.getTotalTimeMillis());
        System.out.println("==============================");
    }

    /**
     * BufferedInputStream有一个内部缓冲区数组，一次性读取较多的字节缓存起来，默认读取defaultBufferSize = 8192，作用于读文件时可以提高性能。
     * BufferedInputStream 是带缓冲区的，在复制、移动文件操作会快一点。
     * @throws IOException
     */
    private static void bufferedInputStreamTest() throws IOException {
        Properties properties = new Properties();
        InputStream fis = null;
        InputStream bis = null;
        StopWatch sw = new StopWatch();
        sw.start();

        try {
            // 绝对路径
            //in = new FileInputStream("/Users/huangyuehao/Desktop/IdeaProjects/study-examples/properties1.txt");
            // 相对路径
            //in = new FileInputStream("properties2.txt");
            fis = new FileInputStream("example-basics/src/main/resources/io/properties2.txt");
            bis = new BufferedInputStream(fis);
            properties.load(bis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(bis)) {
                bis.close();
            }
            if (Objects.nonNull(fis)) {
                fis.close();
            }
        }

        sw.stop();
        String property = properties.getProperty("username_num10");
        System.out.println("username_num10 = " + property);
        System.out.println("sw.getTotalTimeMillis() = " + sw.getTotalTimeMillis());
        System.out.println("==============================");
    }

}
