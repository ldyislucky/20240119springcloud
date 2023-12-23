import com.ldy.producer.MqProApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MqProApplication.class)
@RunWith(SpringRunner.class)//不加这个注解没法跑
public class Springtest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void t1(){
        String queue = "ldyqueues1";
        String str = "holle world!";
        rabbitTemplate.convertAndSend(queue,str);
        System.out.println(rabbitTemplate);
    }
}
