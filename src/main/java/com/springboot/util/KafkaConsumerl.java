package com.springboot.util;

import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerl {
    /**
     * 监听seckill主题,有消息就读取
     * @param message
     */
    /*@KafkaListener(topics = {"seckill"})
    public void receiveMessage(String message){
        //收到通道的消息之后执行秒杀操作
    }*/
}