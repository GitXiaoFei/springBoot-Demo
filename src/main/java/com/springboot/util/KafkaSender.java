package com.springboot.util;

import org.springframework.stereotype.Component;

@Component
public class KafkaSender {

   /* @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    *//**
     * 发送消息到kafka
     * 
     * @param channel
     * @param message
     *//*
    public void sendChannelMess(String channel, String message) {
        kafkaTemplate.send(channel, message);
    }

    public static void main(String[] args) {
        KafkaSender kafka = new KafkaSender();
        kafka.sendChannelMess("001", "我是生产者");
    }*/

}