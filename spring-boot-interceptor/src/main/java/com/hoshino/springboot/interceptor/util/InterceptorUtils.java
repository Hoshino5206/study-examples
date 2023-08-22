package com.hoshino.springboot.interceptor.util;

import com.alibaba.fastjson.JSON;
import com.hoshino.springboot.interceptor.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author huangyuehao
 * @date 2023-05-25
 */
@Slf4j
public class InterceptorUtils {

    public static void outputResponseMessage(HttpServletResponse response, ResponseResult<Object> respObj) {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            log.error("Failed to call OutputResponseMessage.", e);
            return;
        }
        out.print(JSON.toJSONString(respObj));
        out.flush();
        out.close();
    }

    private InterceptorUtils() {

    }

}
