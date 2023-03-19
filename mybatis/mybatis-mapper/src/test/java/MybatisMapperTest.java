import com.hoshino.mybatis.mapper.dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.hoshino.mybatis.mapper.entity.User;
import com.hoshino.mybatis.mapper.utils.MybatisUtils;

import java.util.List;

/**
 * @author Yy_hoshino
 * @date 2021-04-20 2:39
 */
public class MybatisMapperTest {
    /**
     * 查询所有数据
     */
    @Test
    public void testFindAll() {
        SqlSession sqlSession = MybatisUtils.getSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    /**
     * 查询单条数据
     */
    @Test
    public void testFindById() {
        SqlSession sqlSession = MybatisUtils.getSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findById(4);
        System.out.println(user);
        sqlSession.close();
    }

    /**
     * 添加数据
     */
    @Test
    public void testAdd() {
        SqlSession sqlSession = MybatisUtils.getSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(4);
        user.setName("王五");
        user.setPassword("wang123");
        user.setPhone("13577889900");
        int add = userMapper.add(user);
        System.out.println("添加了" + add + "条数据");
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 更新数据
     */
    @Test
    public void testUpdateById() {
        SqlSession sqlSession = MybatisUtils.getSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(4);
        user.setName("老六");
        user.setPassword("liu6666");
        user.setPhone("13711445522");
        int update = userMapper.updateById(user);
        System.out.println("更新了" + update + "条数据");
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除数据
     */
    @Test
    public void testDeleteById() {
        SqlSession sqlSession = MybatisUtils.getSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int delete = userMapper.deleteById(4);
        System.out.println("删除了" + delete + "条数据");
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }
}
