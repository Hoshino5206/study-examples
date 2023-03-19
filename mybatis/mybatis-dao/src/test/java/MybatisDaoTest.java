import com.hoshino.mybatis.dao.dao.UserDao;
import com.hoshino.mybatis.dao.dao.impl.UserDaoImpl;
import org.junit.Test;
import com.hoshino.mybatis.dao.entity.User;

import java.util.List;

/**
 * @author Yy_hoshino
 * @date 2021-04-20 2:05
 */
public class MybatisDaoTest {
    /**
     * 查询所有数据
     */
    @Test
    public void findAllTest() {
        UserDao userDao = new UserDaoImpl();
        List<User> userList = userDao.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 查询单条数据
     */
    @Test
    public void findByIdTest() {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.findById(4);
        System.out.println(user);
    }

    /**
     * 添加数据
     */
    @Test
    public void addTest() {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.add();
        System.out.println(user);
    }

    /**
     * 更新数据
     */
    @Test
    public void updateByIdTest() {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.updateById(4);
        System.out.println(user);
    }

    /**
     * 删除数据
     */
    @Test
    public void deleteByIdTest() {
        UserDao userDao = new UserDaoImpl();
        int delete = userDao.deleteById(4);
        System.out.println("成功删除" + delete + "条数据");
    }

}
