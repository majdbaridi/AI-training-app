package com.company.trainingapp.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

@Bean
    public NewTopic trainingsTopic(){


        return TopicBuilder.name("training_topic").build();
    }
}
