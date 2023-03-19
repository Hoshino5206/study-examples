import com.hoshino.spring.aop.cglib_proxy.dao.UserDao;
import com.hoshino.spring.aop.cglib_proxy.proxy.CglibProxy;
import org.junit.Test;

/**
 * @author Yy_hoshino
 * @date 2021-04-10 14:10
 */
public class CglibProxyTest {

    @Test
    public void CglibTest() {
        // 创建代理对象
        CglibProxy cglibProxy = new CglibProxy();
        // 创建目标对象
        UserDao userDao = new UserDao();
        // 获取增强后的目标对象
        UserDao userDao1 = (UserDao) cglibProxy.createProxy(userDao);
        // 执行方法
        userDao.addUser();
        userDao1.deleteUser();
    }

}
