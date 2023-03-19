import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Yy_hoshino
 * @date 2021-04-09 00:08
 */
public class SpringDataSourceTest {

    /**
     * Spring容器配置c3p0数据源测试
     * @throws SQLException
     */
    @Test
    public void SpringC3P0DataSourceTest() throws SQLException {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource c3p0DataSource = (DataSource) app.getBean("c3p0DataSource");
        Connection connection = c3p0DataSource.getConnection();
        System.out.println(connection);
    }

    /**
     * Spring容器配置druid数据源测试
     * @throws SQLException
     */
    @Test
    public void SpringDruidDataSourceTest() throws SQLException {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource druidDataSource = (DataSource) app.getBean("druidDataSource");
        Connection connection = druidDataSource.getConnection();
        System.out.println(connection);
    }

}
