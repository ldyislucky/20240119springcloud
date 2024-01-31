package com.ldy.producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("pro")
@Slf4j
public class GeneralProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @GetMapping("/callback")//getmapping只支持路径参数，不支持json请求体参数
    public void t7( String exc, String key, String m1, String m2) throws InterruptedException {
        log.info("执行t7!交换机：{}, 队列key: {}",exc,key);
        Map<String, String> map = new HashMap<>();
        map.put("1",m1);
        map.put("2",m2);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        correlationData.getFuture().addCallback(new SuccessCallback<CorrelationData.Confirm>() {
            @Override
            public void onSuccess(CorrelationData.Confirm res) {

                if (res.isAck()){
                    /**
                     * 消息成功投递到交换机，返回ack
                     */
                    log.error("消息已投递到交换机！消息ID：{}, 消息内容: {}",correlationData.getId(),map);
                } else {
                    /**
                     * 消息未投递到交换机，返回nack
                     */
                    log.error("消息未投递到交换机！消息ID：{}, 消息内容: {}",correlationData.getId(),map);
                }
            }
        }, new FailureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("消息发送失败！",throwable);
            }
        });
        rabbitTemplate.convertAndSend(exc,key,map,correlationData);
        // 休眠一会儿，等待ack回执
        //Thread.sleep(1000);
    }
}
