package com.example.rate.controller;

import com.example.rate.entity.Coin;
import com.example.rate.entity.Person;
import com.example.rate.repository.CoinRepository;
import com.example.rate.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
public class CoinController {

    @Autowired
    CoinRepository coinRepository;
    @Autowired
    PersonRepository personRepository;

    @GetMapping("/api/getAllCoins")
    public List<Coin> getCoins() {

        return coinRepository.findAll();
    }

    @GetMapping("/api/getCoin")
    public Coin getCoin(@RequestParam Long code) {

        return coinRepository.findByCode(code).orElseThrow(
                () -> new IllegalArgumentException("Coin with this code not found"));
    }

    @PostMapping("/api/notify")
    public void notify(@RequestParam String name, @RequestParam Long code) {

        Double price_usd = coinRepository.findByCode(code).orElseThrow(
                () -> new IllegalArgumentException("Coin with this code not found")).getPrice_usd();
        Optional<Person> OptionalPerson = personRepository.findByName(name);

        if (OptionalPerson.isEmpty()){
            personRepository.save(Person.builder().name(name).code(code).price(price_usd).build());
        }else {
            Person person = OptionalPerson.get();
            person.setCode(code);
            person.setPrice(price_usd);
            personRepository.save(person);
        }

    }

}
