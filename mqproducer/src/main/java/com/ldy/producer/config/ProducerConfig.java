package com.ldy.producer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProducerConfig implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        /**
         * 在交换机和队列之间出现问题会触发这个方法！如果没有到达交换机不会触发这个方法，但是交换机为空的时候是会触发这个方法的！
         * 返回ACK，及路由失败原因。
         */
        rabbitTemplate.setReturnCallback((message, i, s, s1, s2) -> {
            log.error("消息发送到队列失败，响应码：{}, 失败原因：{}, 交换机: {}, 路由key：{}, 消息: {}",
                     i, s, s1, s2,message);
        });
    }
}
