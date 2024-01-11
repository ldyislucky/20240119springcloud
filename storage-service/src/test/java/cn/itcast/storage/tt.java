package cn.itcast.storage;

import cn.itcast.storage.entity.Storage;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = StorageApplication.class)
public class tt {
    @Test
    public void t1(){
        Storage storage = new Storage();

        storage.setCommodityCode("1111");
        storage.setCount(10);
        String jsonString = JSON.toJSONString(storage);
        System.out.println(jsonString);
        System.out.println("6666");
    }
}
