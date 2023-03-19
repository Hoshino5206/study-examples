import com.hoshino.spring.aop.jdk_proxy.dao.UserDao;
import com.hoshino.spring.aop.jdk_proxy.dao.impl.UserDaoImpl;
import com.hoshino.spring.aop.jdk_proxy.proxy.JdkProxy;
import org.junit.Test;

/**
 * @author Yy_hoshino
 * @date 2021-04-10 14:10
 */
public class JdkProxyTest {

    @Test
    public void JdkTest() {
        // 创建代理对象
        JdkProxy jdkProxy = new JdkProxy();
        // 创建目标对象
        com.hoshino.spring.aop.jdk_proxy.dao.UserDao userDao = new UserDaoImpl();
        userDao.addUser();
        userDao.deleteUser();
        System.out.println("----------------------------------");
        // 从代理对象中获取增强后的日标对象
        com.hoshino.spring.aop.jdk_proxy.dao.UserDao userDao1 = (UserDao) jdkProxy.createProxy(userDao);
        userDao1.addUser();
        System.out.println("----------------------------------");
        userDao1.deleteUser();
    }

}
