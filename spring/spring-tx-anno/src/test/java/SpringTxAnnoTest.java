import com.hoshino.spring.tx.anno.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Yy_hoshino
 * @date 2021-04-14 23:32
 */
public class SpringTxAnnoTest {

    @Test
    public void springTransactionAnnoTest() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService) app.getBean("accountService");
        accountService.transfer("Jack", "Tom", 500);
    }

}
