package com.hoshino.study.maven.jdk8;

import com.hoshino.study.maven.domain.Users;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author huangyuehao
 * @date 2022-08-15
 */
public class MyOptional {
    public static void main (String[] args) {
        Users users = new Users();
        users.setAge(20);
//        Optional.ofNullable(users).orElse("users 为空");

        // 1. Optional对象创建
        Optional<String> optEmpty = Optional.empty();
        // 创建包装对象值非空的Optional对象
        Optional<String> optOf = Optional.of("optional");
        // 创建包装对象值允许为空也可以不为空的Optional对象
        Optional<String> optOfNullable1 = Optional.ofNullable(null);
        Optional<String> optOfNullable2 = Optional.ofNullable("optional");

        // 2. Optional.get()方法(返回对象的值)
        Optional.ofNullable(users).get();

        // 3. Optional.isPresent()方法(判读是否为空)
        if (Optional.ofNullable(users).isPresent()){
            //写不为空的逻辑
            System.out.println("不为空");
        } else{
            //写为空的逻辑
            System.out.println("为空");
        }

        // 4. Optional.ifPresent()方法(判读是否为空并返回函数)
        Optional.ofNullable(users).ifPresent(p -> System.out.println(" " + p.getUsername()));

        // 5. Optional.filter()方法(过滤对象)
        Optional.ofNullable(users).filter(p -> p.getAge()>50);

        // 6. Optional.map()方法(对象进行二次包装)
        String optName1 = Optional.ofNullable(users).map(p -> users.getUsername()).orElse("username为空");

        // 7. Optional.flatMap()方法(Optional对象进行二次包装)
        Optional<Object> optName2 = Optional.ofNullable(users).map(p -> Optional.ofNullable(p.getUsername()).orElse("username为空"));

        // 8. Optional.orElse()方法(为空返回对象)

        // 9. Optional.orElseGet()方法(为空返回Supplier对象)
        Optional<Supplier<Users>> sup=Optional.ofNullable(Users :: new);
        //调用get()方法，此时才会调用对象的构造方法，即获得到真正对象
        Optional.ofNullable(users).orElseGet(sup.get());

        // 10. Optional.orElseThrow()方法(为空返回异常)
        Optional.ofNullable(users).orElseThrow(() -> new RuntimeException("没有查询的相关数据"));

    }
}
