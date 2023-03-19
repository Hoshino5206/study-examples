import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.hoshino.spring.ioc.anno.service.UserService;
import com.hoshino.spring.ioc.anno.util.SpringConfiguration;

/**
 * @author Yy_hoshino
 * @date 2021-04-09 22:31
 */
public class SpringIoC_AnnotationTest {

    /**
     * 基于原始注解开发的依赖注入测试
     */
    @Test
    public void SpringAnnotationTest() {
        ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) app.getBean("userService");
        userService.save();
        app.close();
    }

    /**
     * 自动装配方式依赖注入测试
     */
    @Test
    public void SpringAutoWireTest() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) app.getBean("userService");
        userService.save();
    }

    /**
     * 基于新注解开发的依赖注入测试
     */
    @Test
    public void SpringNewAnnotationTest() {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UserService userService = (UserService) app.getBean("userService");
        userService.save();
        app.close();
    }

}
