package com.tbd.twitter.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Value("${kafka.topicName}")
    private String jsonTopic;

    @Autowired
    private KafkaTemplate<String, Tweet> kafkaJsonTemplate;

    public void send(Tweet tweet) {
        LOGGER.info("sending tweet='{}'", tweet.toString());
        kafkaJsonTemplate.send(jsonTopic, tweet);
    }
}