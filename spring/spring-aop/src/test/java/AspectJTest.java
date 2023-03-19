import com.hoshino.spring.aop.aspect.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Yy_hoshino
 * @date 2021-04-10 14:10
 */
public class AspectJTest {

    @Test
    public void AspectTest() {
        String xmlPath = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        // 从Spring容器获得内容
        com.hoshino.spring.aop.aspect.dao.UserDao userDao = (com.hoshino.spring.aop.aspect.dao.UserDao) applicationContext.getBean("userDaoProxy");
        // 执行方法
        userDao.addUser();
        userDao.deleteUser();
    }

    /**
     * 基于XML的声明式 AspectJ
     */
    @Test
    public void XMLAspectTest() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        com.hoshino.spring.aop.aspectJ_xml.dao.UserDao userDao = (com.hoshino.spring.aop.aspectJ_xml.dao.UserDao) app.getBean("userDao");
        userDao.save();
        System.out.println();
        userDao.delete();
        System.out.println();
        userDao.update();
        System.out.println();
        userDao.find();
    }

    /**
     * 基于注解的声明式 AspectJ
     */
    @Test
    public void AnnoAspectTest() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        com.hoshino.spring.aop.aspectJ_anno.dao.UserDao userDao = (com.hoshino.spring.aop.aspectJ_anno.dao.UserDao) app.getBean("userDao");
        userDao.save();
        System.out.println();
        userDao.delete();
        System.out.println();
        userDao.update();
        System.out.println();
        userDao.find();
    }

}
