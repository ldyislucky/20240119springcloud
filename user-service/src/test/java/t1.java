import com.alibaba.fastjson.JSON;
import com.ldy.user.UserApplication;
import com.ldy.user.entity.AccountTbl;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.spring.web.json.Json;

@SpringBootTest(classes = UserApplication.class)
public class t1 {
    @Test
    public void t1(){
        AccountTbl accountTbl = new AccountTbl();
        accountTbl.setUserId("xiaoli");
        accountTbl.setMoney(100);
        String jsonString = JSON.toJSONString(accountTbl);
        System.out.println(jsonString);
    }
}
