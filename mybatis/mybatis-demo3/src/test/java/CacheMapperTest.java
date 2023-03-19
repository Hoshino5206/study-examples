import com.hoshino.mybatis.demo3.mapper.CacheMapper;
import com.hoshino.mybatis.demo3.entity.Emp;
import com.hoshino.mybatis.demo3.utils.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class CacheMapperTest {

    /**
     * 同一个sqlsession
     */
    @Test
    public void singleSqlSessionOneCacheTest() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper1 = sqlSession.getMapper(CacheMapper.class);
        Emp emp1 = mapper1.getEmpByEid(1);
        System.out.println(emp1);
        CacheMapper mapper2 = sqlSession.getMapper(CacheMapper.class);
        Emp emp2 = mapper2.getEmpByEid(1);
        System.out.println(emp2);
    }

    /**
     * 不同一个sqlsession
     */
    @Test
    public void diffSqlSessionOneCacheTest(){
        SqlSession sqlSession1 = SqlSessionUtils.getSqlSession();
        SqlSession sqlSession2 = SqlSessionUtils.getSqlSession();
        CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
        Emp emp1 = mapper1.getEmpByEid(1);
        System.out.println(emp1);
        CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
        Emp emp2 = mapper2.getEmpByEid(1);
        System.out.println(emp2);

        //1.查询条件不同
        Emp emp3 = mapper1.getEmpByEid(2);
        //2.执行了任何一次增删改操作 ,缓存失效
        mapper1.insertEmp(new Emp(null,"abc",23,"男","123@qq.com"));
        //3.缓存失效手动清空了缓存
        sqlSession1.clearCache();
    }

    @Test
    public void twoCacheTest(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
            CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
            System.out.println(mapper1.getEmpByEid(1));
            sqlSession1.close();
            SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
            CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
            System.out.println(mapper2.getEmpByEid(1));
            sqlSession2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
