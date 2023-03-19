import com.hoshino.spring.ioc.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.hoshino.spring.ioc.model.User;
import com.hoshino.spring.ioc.service.UserService;

/**
 * @author Yy_hoshino
 * @date 2021-04-08 23:28
 */
public class SpringTest {

    /**
     * Bean实例化测试
     */
    @Test
    public void SpringBeanTest() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) app.getBean("userDao");
        userDao.save();
    }

    /**
     * 依赖注入测试
     */
    @Test
    public void SpringIoCTest (){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) app.getBean("userService");
        userService.save();
    }

    /**
     * Bean的作用域：singleton(单例)测试
     */
    @Test
    public void SingletonTest() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao1 = (UserDao) app.getBean("userDao");
        UserDao userDao2 = (UserDao) app.getBean("userDao");
        System.out.println(userDao1);
        System.out.println(userDao2);
    }

    /**
     * Bean的作用域：prototype(原型)测试
     */
    @Test
    public void PrototypeTest() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao1 = (UserDao) app.getBean("userDao");
        UserDao userDao2 = (UserDao) app.getBean("userDao");
        System.out.println(userDao1);
        System.out.println(userDao2);
    }

    /**
     * 普通数据类型的依赖注入测试
     */
    @Test
    public void DIType1Test() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user1 = (User) app.getBean("user1");
        User user2 = (User) app.getBean("user2");
        System.out.println(user1);
        System.out.println(user2);
    }

    /**
     * 引用数据类型的依赖注入测试
     */
    @Test
    public void DIType2Test() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) app.getBean("userService");
        userService.save();
    }


    /**
     * 集合数据类型的依赖注入测试
     */
    @Test
    public void DIType3Test() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) app.getBean("userDao");
        userDao.save();
    }
}
