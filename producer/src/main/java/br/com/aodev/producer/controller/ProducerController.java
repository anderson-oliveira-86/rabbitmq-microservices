package br.com.aodev.producer.controller;

import br.com.aodev.producer.model.Carro;
import br.com.aodev.producer.service.CarroService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CarroService service;

    @GetMapping
    public void sendMessage() {
        List<Carro> carros = service.listarCarros();
        carros.forEach(carro -> {
            rabbitTemplate.convertAndSend("producer.ex","", carro);
        });

    }
}
