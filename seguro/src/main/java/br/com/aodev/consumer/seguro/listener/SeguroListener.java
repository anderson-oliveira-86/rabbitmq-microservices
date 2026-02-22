package br.com.aodev.consumer.seguro.listener;

import br.com.aodev.consumer.seguro.model.Carro;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SeguroListener {

    @RabbitListener(queues = "seguro")
    public void recebeMensagem(Carro carro) {

        if (carro.valor().compareTo(BigDecimal.valueOf(50000)) < 0) {
            throw new RuntimeException();
        }

        String message = """
                ### Carro ###
                Marca: %s
                Modelo: %s
                Versao: %s
                """.formatted(carro.marca(), carro.modelo(), carro.versao());
        System.out.println(message);
    }
}
