package com.ldy.producer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
public class ProducerController {
    private final RabbitTemplate rabbitTemplate;
//    @GetMapping("/p1")
//    public void t1(){
//        log.info("模式一生产消息到队列");
//        String queue = "ldyqueues1";
//        String str = "holle world!";
//        rabbitTemplate.convertAndSend(queue,str);
//    }

//    @GetMapping("/p2")
//    public void t2() throws InterruptedException {
//        log.info("模式二生产消息到队列");
//        String queue = "ldyqueues1";
//        String str;
//        for (int i = 0; i < 50; i++) {
//            str = "holle world! -"+i;
//            rabbitTemplate.convertAndSend(queue,str);
//            Thread.sleep(20);
//        }
//    }
    @GetMapping("/pudsub")
    public void t3(){
        log.info("发布订阅模式生产消息到队列");
        String exfanout = "ldy.ex";
        String str;
        for (int i = 0; i < 50; i++) {
            str = "holle world! -"+i;
            rabbitTemplate.convertAndSend(exfanout,"",str);
        }
    }
    @GetMapping("/dir/{key}")
    public void t4(@PathVariable("key")String key){
        log.info("执行t4");
        String direx = "ldy.dir";
        String str;
        for (int i = 0; i < 50; i++) {
            str = "holle world! -"+key+i;
            rabbitTemplate.convertAndSend(direx,key,str);
        }
    }
    @GetMapping("/topic/{key}")
    public void t5(@PathVariable("key")String key){
        log.info("执行t5");
        String direx = "ldy.topic";
        String str;
        for (int i = 0; i < 50; i++) {
            str = "holle world! -"+key+i;
            rabbitTemplate.convertAndSend(direx,key,str);
        }
    }
    @GetMapping("/object/{key}")
    public void t6(@PathVariable("key")String key){
        log.info("执行t6");
        String direx = "ldy.topic";
        Map<String, String> map = new HashMap<>();
        map.put("1","一");
        map.put("2","二");
        rabbitTemplate.convertAndSend(direx,key,map);
    }
    @GetMapping("/callback")//getmapping只支持路径参数，不支持json请求体参数
    public void t7( String exc, String key) throws InterruptedException {
        log.info("执行t7!交换机：{}, 队列key: {}",exc,key);
        Map<String, String> map = new HashMap<>();
        map.put("1","一");
        map.put("2","二");
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        correlationData.getFuture().addCallback(new SuccessCallback<CorrelationData.Confirm>() {
            @Override
            public void onSuccess(CorrelationData.Confirm res) {

                if (res.isAck()){
                    /**
                     * 消息成功投递到交换机，返回ack
                     */
                    log.error("消息已投递到交换机！消息ID：{}, 消息内容: {}",correlationData.getId(),correlationData.getReturnedMessage());
                } else {
                    /**
                     * 消息未投递到交换机，返回nack
                     */
                    log.error("消息未投递到交换机！消息ID：{}, 消息内容: {}",correlationData.getId(),correlationData.getReturnedMessage());
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
        Thread.sleep(3000);
    }

//    @GetMapping("/callback1")//
//    public void t8( @PathVariable("exc") String exc, @PathVariable("key") String key) throws InterruptedException {
//        log.info("执行t8!交换机：{}, 队列key: {}",exc,key);
//        Map<String, String> map = new HashMap<>();
//        map.put("1","一");
//        map.put("2","二");
//        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
//        correlationData.getFuture().addCallback(new SuccessCallback<CorrelationData.Confirm>() {
//            @Override
//            public void onSuccess(CorrelationData.Confirm res) {
//
//                if (res.isAck()){
//                    /**
//                     * 消息成功投递到交换机，返回ack
//                     */
//                    log.debug("消息已投递到交换机！消息ID：{}, 消息内容: {}",correlationData.getId(),correlationData.getReturnedMessage());
//                } else {
//                    /**
//                     * 消息未投递到交换机，返回nack
//                     */
//                    log.error("消息未投递到交换机！消息ID：{}, 消息内容: {}",correlationData.getId(),correlationData.getReturnedMessage());
//                }
//            }
//        }, new FailureCallback() {
//            @Override
//            public void onFailure(Throwable throwable) {
//                log.error("消息发送失败！",throwable);
//            }
//        });
//        rabbitTemplate.convertAndSend(exc,key,map,correlationData);
//        // 休眠一会儿，等待ack回执
//        Thread.sleep(2000);
//    }


}
