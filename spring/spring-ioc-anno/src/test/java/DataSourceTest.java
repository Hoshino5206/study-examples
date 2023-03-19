import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.util.ResourceBundle;

/**
 * @author Yy_hoshino
 * @date 2021-04-09 00:03
 */
public class DataSourceTest {

    /**
     * 手动创建c3p0数据源测试
     * @throws Exception
     */
    @Test
    public void C3p0Test() throws Exception {
        // 创建数据源
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        // 设置数据库连接参数
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/example");
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        // 获得连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    /**
     * 手动创建Druid数据源测试
     * @throws Exception
     */
    @Test
    public void DruidTest() throws Exception {
        // 创建数据源
        DruidDataSource dataSource = new DruidDataSource();
        // 设置数据库连接参数
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/example");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        // 获得连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    /**
     * 加载properties配置文件创建c3p0数据源测试
     * @throws Exception
     */
    @Test
    public void C3p0ByPropertiesTest() throws Exception {
        // 加载类路径下的jdbc.properties，获取配置文件对象
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        // 创建数据源对象
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        // 设置数据库连接参数
        dataSource.setDriverClass(rb.getString("jdbc.driver"));
        dataSource.setJdbcUrl(rb.getString("jdbc.url"));
        dataSource.setUser(rb.getString("jdbc.username"));
        dataSource.setPassword(rb.getString("jdbc.password"));
        // 获得连接对象
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

}
