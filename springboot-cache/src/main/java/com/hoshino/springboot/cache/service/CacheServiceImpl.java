package com.hoshino.springboot.cache.service;

import com.hoshino.springboot.cache.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-01-16
 */
@Service
@Slf4j
public class CacheServiceImpl implements CacheService {

    private static List<User> userList = Arrays.asList(new User(1, "John", "000111"));

    /**
     * 1.注解@Cacheable属性
     *   value(String[]): 缓存名称
     *   cacheNames(String[]): 缓存组件的名字,value和cacheNames二选一
     *   key(String): SpEL表达式,自定义缓存key
     *   keyGenerator(String): 自动生成缓存key，key 和 keyGenerator 二选一使用
     *   cacheManager(String): 可以用来指定缓存管理器。从哪个缓存管理器里面获取缓存
     *   condition(String): SpEL表达式，指定条件下才缓存，即为true时才缓存
     *   unless(String): SpEL表达式，如果结果为true则返回值不缓存
     *   sync(boolean):  是否使用异步
     * 2.注解@CachePut.
     *   使用@CachePut仅存储缓存，它会把方法的返回值存储到缓存，其不会去查询缓存，这是与@Cacheable不同的地方，被@CachePut注解的方法始终会被执行。
     * 3.注解@CacheEvict属性.
     *   allEntries(boolean): 是否清空所有缓存内容，缺省为 false，如果指定为 true，则方法调用后将立即清空所有缓存
     *   beforeInvocation(boolean): 是否在方法执行前就清空，缺省为 false，如果指定为 true，则在方法还没有执行的时候就清空缓存，缺省情况下，如果方法执行抛出异常，则不会清空缓存
     * 4.总结.
     *   Cacheable: 存储及读取缓存。会先查询缓存，当缓存为空，被注解的方法才会被执行
     *   CachePut: 仅存储缓存。不查询缓存，被注解的方法始终会被执行
     *   CacheEvict: 仅删除缓存，被注解方法会被执行
     */

    @Cacheable(value = "userCache", key = "#id", unless="#result == null")
    @Override
    public User getUser(Integer id) {
        log.info("获取用户id ................");
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Cacheable(value = "allUsersCache", unless = "#result.size() == 0")
    @Override
    public List<User> getUserList() {
        log.info("获取所有用户列表 ................");
        return userList;
    }

    /**
     * 创建用户，同时使用新的返回值的替换缓存中的值
     * 创建用户后会将allUsersCache缓存全部清空
     */
    @CachePut(value = "userCache", key = "#user.id", unless="#result == null")
    @CacheEvict(value = "allUsersCache", allEntries = true)
    @Override
    public User create(User user) {
        log.info("创建用户id ................, user.id=" + user.getId());
        userList.add(user);
        return user;
    }

    /**
     * 更新用户，同时使用新的返回值的替换缓存中的值
     * 更新用户后会将allUsersCache缓存全部清空
     */
    @Caching(
            put = {@CachePut(value = "userCache", key = "#user.id")},
            evict = {@CacheEvict(value = "allUsersCache", allEntries = true)}
    )
    @Override
    public User updateById(User user) {
        log.info("更新用户id ................");
        for (User result : userList) {
            if (user.getId().equals(result.getId())) {
                userList.remove(result);
                userList.add(user);
            }
        }
        return user;
    }

    /**
     * 对符合key条件的记录从缓存中移除
     * 删除用户后会将allUsersCache缓存全部清空
     */
    @Caching(
            evict = {
                    @CacheEvict(value = "userCache", key = "#id"),
                    @CacheEvict(value = "allUsersCache", allEntries = true)
            }
    )
    @Override
    public void deleteById(Integer id) {
        log.info("删除用户id  ................");
        userList.removeIf(user -> user.getId().equals(id));
    }

}
