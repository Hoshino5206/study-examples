import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import com.hoshino.mybatis.quick.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Yy_hoshino
 * @date 2021-04-18 14:43
 */
public class MybatisQuickTest {
    /**
     * 查询所有数据
     * @throws IOException
     */
    @Test
    public void findAllTest() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    /**
     * 查询单条数据
     * @throws IOException
     */
    @Test
    public void findByIdTest() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("userMapper.findById",4);
        System.out.println(user);
        sqlSession.close();
    }

    /**
     * 添加数据
     * @throws IOException
     */
    @Test
    public void addTest() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(4);
        user.setName("王五");
        user.setPassword("wang123");
        user.setPhone("13577889900");
        sqlSession.insert("userMapper.add",user);
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 更新数据
     * @throws IOException
     */
    @Test
    public void updateByIdTest() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(4);
        user.setName("老六");
        user.setPassword("liu6666");
        user.setPhone("13711445522");
        sqlSession.update("userMapper.updateById",user);
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除数据
     * @throws IOException
     */
    @Test
    public void deleteByIdTest() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("userMapper.deleteById",4);
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

}
