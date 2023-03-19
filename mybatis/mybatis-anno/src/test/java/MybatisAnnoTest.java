import com.hoshino.mybatis.anno.dao.UserDao;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.hoshino.mybatis.anno.entity.User;
import com.hoshino.mybatis.anno.utils.MybatisUtils;

import java.util.List;

/**
 * @author Yy_hoshino
 * @date 2021-04-20 19:13
 */
public class MybatisAnnoTest {
    /**
     * 查询所有数据
     */
    @Test
    public void findAllTest() {
        SqlSession sqlSession = MybatisUtils.getSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    /**
     * 查询单条数据
     */
    @Test
    public void findByIdTest() {
        SqlSession sqlSession = MybatisUtils.getSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.findById(3);
        System.out.println(user);
        sqlSession.close();
    }

    /**
     * 添加数据
     */
    @Test
    public void addTest() {
        SqlSession sqlSession = MybatisUtils.getSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setId(4);
        user.setName("王五");
        user.setPassword("wang123");
        user.setPhone("13577889900");
        int add = userDao.add(user);
        System.out.println("添加了" + add + "条数据");
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 更新数据
     */
    @Test
    public void updateByIdTest() {
        SqlSession sqlSession = MybatisUtils.getSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setId(4);
        user.setName("老六");
        user.setPassword("liu6666");
        user.setPhone("13711445522");
        int update = userDao.updateById(user);
        System.out.println("更新了" + update + "条数据");
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除数据
     */
    @Test
    public void deleteByIdTest() {
        SqlSession sqlSession = MybatisUtils.getSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int delete = userDao.deleteById(4);
        System.out.println("删除了" + delete + "条数据");
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

}
