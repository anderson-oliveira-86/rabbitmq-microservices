package br.com.aodev.producer.service;

import br.com.aodev.producer.model.Carro;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarroService {

    public List<Carro> listarCarros(){
        List<Carro> carros = new ArrayList<>();
        carros.add(new Carro("Volkswagen", "Gol", "1.6 MSI", 2018, BigDecimal.valueOf(32000)));
        carros.add(new Carro("Chevrolet", "Onix", "1.4 LT", 2017, BigDecimal.valueOf(28000)));
        carros.add(new Carro("Ford", "Ka", "1.0 SE", 2019, BigDecimal.valueOf(35000)));
        carros.add(new Carro("Toyota", "Corolla", "2.0 XEi", 2020, BigDecimal.valueOf(85000)));
        carros.add(new Carro("Honda", "Civic", "2.0 EX", 2016, BigDecimal.valueOf(60000)));
        carros.add(new Carro("Hyundai", "HB20", "1.0 Comfort", 2021, BigDecimal.valueOf(55000)));
        carros.add(new Carro("Renault", "Sandero", "1.0 Expression", 2018, BigDecimal.valueOf(30000)));
        carros.add(new Carro("Nissan", "Versa", "1.6 SV", 2019, BigDecimal.valueOf(45000)));
        carros.add(new Carro("Peugeot", "208", "1.2 Active", 2022, BigDecimal.valueOf(70000)));
        carros.add(new Carro("Citroën", "C3", "1.5 Tendance", 2015, BigDecimal.valueOf(27000)));
        carros.add(new Carro("Jeep", "Renegade", "1.8 Sport", 2020, BigDecimal.valueOf(90000)));
        carros.add(new Carro("Chevrolet", "Cruze", "1.4 Turbo", 2019, BigDecimal.valueOf(75000)));
        carros.add(new Carro("Volkswagen", "Polo", "1.0 TSI", 2021, BigDecimal.valueOf(82000)));
        carros.add(new Carro("Fiat", "Argo", "1.3 Drive", 2022, BigDecimal.valueOf(68000)));
        carros.add(new Carro("Ford", "EcoSport", "2.0 Titanium", 2017, BigDecimal.valueOf(65000)));
        carros.add(new Carro("Toyota", "Yaris", "1.5 XS", 2021, BigDecimal.valueOf(72000)));
        carros.add(new Carro("Honda", "Fit", "1.5 LX", 2018, BigDecimal.valueOf(58000)));
        carros.add(new Carro("Hyundai", "Creta", "1.6 Pulse", 2020, BigDecimal.valueOf(88000)));
        carros.add(new Carro("Renault", "Duster", "1.6 Dynamique", 2019, BigDecimal.valueOf(62000)));
        return  carros;
    }
}
