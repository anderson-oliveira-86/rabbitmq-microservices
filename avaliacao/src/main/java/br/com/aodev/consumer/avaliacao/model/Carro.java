package br.com.aodev.consumer.avaliacao.model;

import java.math.BigDecimal;

public record Carro(
        String marca,
        String modelo,
        String versao,
        int ano,
        BigDecimal valor
) {
}
