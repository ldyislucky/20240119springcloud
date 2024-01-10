package cn.itcast.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 虎哥
 */
@MapperScan("cn.itcast.storage.mapper")
//@ComponentScan({"cn.itcast.storage.web","cn.itcast.storage.service","cn.itcast.storage.mapper",})
@SpringBootApplication
public class StorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }
}
