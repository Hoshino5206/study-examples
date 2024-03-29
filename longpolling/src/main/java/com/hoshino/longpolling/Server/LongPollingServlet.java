package com.hoshino.longpolling.Server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 服务端Code
 *
 * @author huangyuehao
 * @date 2022-08-22
 */
public class LongPollingServlet extends HttpServlet {
    private Random random = new Random();

    private AtomicLong sequenceId = new AtomicLong();

    private AtomicLong count = new AtomicLong();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("第" + (count.incrementAndGet()) + "次 longpolling");

        int sleepSecends = random.nextInt(100);
        //随机获取等待时间，来通过sleep模拟服务端是否准备好数据
        System.out.println("wait " + sleepSecends + " second");
        try {
            TimeUnit.SECONDS.sleep(sleepSecends);//sleep
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        long value = sequenceId.getAndIncrement();
        out.write(Long.toString(value));
    }
}
