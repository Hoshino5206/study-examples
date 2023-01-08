package com.hoshino.basics.utils;

/**
 * @author huangyuehao
 * @date 2022-09-01
 */

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.hoshino.basics.domain.JobSettings;
import com.hoshino.basics.domain.Users;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1.将JavaBean转换为json，或将json字符串转换为JavaBean。
 * 2.将List集合转换为json，或将json转换为List集合。
 * 3.将Map集合转换为json，或将json转换为Map集合。
 */
public class MyGson {

    private static Users users1 = new Users(001, "艾伦", "2000", 18);

    private static Users users2 = new Users(002, "三立", "1000", 16);

    public static void gson1() {
        System.out.println("users: " + users1);

        Gson gson = new Gson();
        String json = gson.toJson(users1);
        System.out.println("Gson-toJson: " + json);

        Users fromJson = gson.fromJson(json, Users.class);
        System.out.println("Gson-fromJson: " + fromJson);
    }

    public static void gson2() {
        List<Users> usersList = new ArrayList<>();
        usersList.add(users1);
        usersList.add(users2);
        System.out.println("usersList: " + usersList);

        Gson gson = new Gson();
        String json = gson.toJson(usersList);
        System.out.println("Gson-toJson: " + json);

        Type type = new TypeToken<List<Users>>(){}.getType();
        List<Users> fromJson = gson.fromJson(json, type);
        System.out.println("Gson-fromJson: " + fromJson);

    }

    public static void gson3() {
        Map<String, Users> usersMap = new HashMap<>();
        usersMap.put("p1", users1);
        usersMap.put("p2", users2);
        System.out.println("usersMap: " + usersMap);

        Gson gson = new Gson();
        String json = gson.toJson(usersMap);
        System.out.println("Gson-toJson: " + json);

        Type type = new TypeToken<Map<String, Users>>(){}.getType();
        Map<String, Users> fromJson = gson.fromJson(json, type);
        System.out.println("Gson-fromJson: " + fromJson);
    }

    public static void gson4() {
        String str = "{\n" +
                "  \"jobName\": \"testElasticJob\",\n" +
                "  \"jobClass\": \"com.xhqb.kael.example.elasticjob.job.TestElasticJob\",\n" +
                "  \"jobType\": \"SIMPLE\",\n" +
                "  \"cron\": \"0 */1 * * * ?\",\n" +
                "  \"shardingTotalCount\": 3,\n" +
                "  \"shardingItemParameters\": \"\",\n" +
                "  \"jobParameter\": \"\",\n" +
                "  \"failover\": true,\n" +
                "  \"misfire\": true,\n" +
                "  \"description\": \"test\",\n" +
                "  \"jobProperties\": {\n" +
                "    \"job_exception_handler\": \"com.dangdang.ddframe.job.executor.handler.impl.DefaultJobExceptionHandler\",\n" +
                "    \"executor_service_handler\": \"com.dangdang.ddframe.job.executor.handler.impl.DefaultExecutorServiceHandler\"\n" +
                "  },\n" +
                "  \"monitorExecution\": true,\n" +
                "  \"maxTimeDiffSeconds\": -1,\n" +
                "  \"monitorPort\": -1,\n" +
                "  \"jobShardingStrategyClass\": \"\",\n" +
                "  \"reconcileIntervalMinutes\": 10,\n" +
                "  \"disabled\": false,\n" +
                "  \"overwrite\": true\n" +
                "}";
        Gson gson = new Gson();
        JobSettings jobSettings = gson.fromJson(str, JobSettings.class);
        System.out.println(jobSettings.toString());

    }

    public static void main (String[] args) {
        gson1();
        System.out.println("--------------");
        gson2();
        System.out.println("--------------");
        gson3();
        System.out.println("--------------");
        gson4();
    }
}
