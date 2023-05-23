package com.hoshino.springboot.multisource.aspect;

import com.hoshino.springboot.multisource.annotation.DataSource;
import com.hoshino.springboot.multisource.constant.DataSourceType;
import com.hoshino.springboot.multisource.datasource.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源AOP切面
 * aop的顺序要早于spring的事务，也可以实现Ordered类重写getOrder方法
 *
 * @author huangyuehao
 * @date 2023-04-23
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class DataSourceAspect {

    /**
     * 所有配置MyDataSource注解的Service实现类。
     */
    @Pointcut("@target(com.hoshino.springboot.multisource.annotation.DataSource)")
    public void datasourcePointCut() {
    }

    @Around("datasourcePointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        MethodSignature methodSignature = (MethodSignature) signature;

        Object target = joinPoint.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

        DataSource dataSource = currentMethod.getAnnotation(DataSource.class);
        // 通过判断 DataSource 中的值来判断当前方法应用哪个数据源
        if (dataSource != null) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value());
            log.debug("设置数据源为：" + dataSource.value());
        } else {
            DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.MASTER);
            log.debug("设置数据源为：dataSourceCore");
        }
        try {
            return joinPoint.proceed();
        } finally {
            log.debug("清空数据源信息！");
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

}
