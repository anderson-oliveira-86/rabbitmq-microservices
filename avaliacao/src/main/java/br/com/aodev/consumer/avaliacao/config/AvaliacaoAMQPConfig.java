package br.com.aodev.consumer.avaliacao.config;

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
public class AvaliacaoAMQPConfig {

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
    public Queue filaAvaliacao(){
        return QueueBuilder
                .nonDurable("avaliacao")
                .deadLetterExchange("producer.dlx")
                .build();
    }

    @Bean
    public Queue filaDlqAvaliacao(){
        return QueueBuilder
                .nonDurable("avaliacao.dlq")
                .build();
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return ExchangeBuilder
                .fanoutExchange("producer.ex")
                .build();
    }

    @Bean
    public FanoutExchange deadLetterExchange(){
        return ExchangeBuilder
                .fanoutExchange("producer.dlx")
                .build();
    }

    @Bean
    public Binding bindAvaliacao(){
        return BindingBuilder
                .bind(filaAvaliacao())
                .to(fanoutExchange());
    }

    @Bean
    public Binding bindDlxAvaliacao(){
        return BindingBuilder
                .bind(filaDlqAvaliacao())
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
