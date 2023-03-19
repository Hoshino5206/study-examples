import com.hoshino.spring.tx.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Yy_hoshino
 * @date 2021-04-14 0:51
 */
public class SpringTxTest {

    @Test
    public void springTransactionTest() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService) app.getBean("accountService");
        accountService.transfer("Jack", "Tom", 500);
    }

}
