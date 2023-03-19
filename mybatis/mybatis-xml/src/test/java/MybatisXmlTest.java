import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import com.hoshino.mybatis.xml.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Yy_hoshino
 * @date 2021-04-24 16:51
 */
public class MybatisXmlTest {
    @Test
    public void test1() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }
}
