package br.com.aodev.consumer.seguro.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeguroAMQPConfig {

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return  rabbitTemplate;
    }

    @Bean
    public Queue filaSeguro(){
        return QueueBuilder
                .nonDurable("seguro")
                .deadLetterExchange("producer.dlx")
                .build();
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return ExchangeBuilder
                .fanoutExchange("producer.ex")
                .build();
    }

    @Bean
    public Binding bindSeguro(){
        return BindingBuilder
                .bind(filaSeguro())
                .to(fanoutExchange());
    }

    @Bean
    public Queue filaDlqSeguro(){
        return QueueBuilder
                .nonDurable("seguro.dlq")
                .build();
    }

    @Bean
    public FanoutExchange deadLetterExchange(){
        return ExchangeBuilder
                .fanoutExchange("producer.dlx")
                .build();
    }

    @Bean
    public Binding bindDlxSeguro(){
        return BindingBuilder
                .bind(filaDlqSeguro())
                .to(deadLetterExchange());
    }

    @Bean
    public RabbitAdmin criaRabbitAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }
}
