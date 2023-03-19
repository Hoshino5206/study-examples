import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.hoshino.spring.ioc.anno.service.UserService;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author Yy_hoshino
 * @date 2021-04-10 1:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring核心配置文件
@ContextConfiguration(locations = "classpath:applicationContext.xml")
// 加载spring核心配置类
//@ContextConfiguration(classes = SpringConfiguration.class)
public class SpringJunitTest {

    @Autowired
    private UserService userService;

    @Autowired
    private DataSource dataSource;

    /**
     * 使用原始注解和新注解，Spring集成JUnit
     * @throws SQLException
     */
    @Test
    public void UserServiceTest() throws SQLException {
        userService.save();
        System.out.println(dataSource.getConnection());
    }

}
