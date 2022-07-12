package com.example.rate.util;

import com.example.rate.entity.Coin;
import com.example.rate.entity.Person;
import com.example.rate.repository.CoinRepository;
import com.example.rate.repository.PersonRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
@Component
public class GetAndSaveValute {

    RestTemplate restTemplate = new RestTemplate();

    CoinRepository coinRepository;

    PersonRepository personRepository;

    @Scheduled(cron = "0 * * * * *")
    public void getSaveCoinAndCheckPersonCoin() throws URISyntaxException {

        Coin[] getCoins = restTemplate.getForObject(new URI("https://api.coinlore.net/api/ticker/?id=90,80,48543"), Coin[].class);

        for (Coin coin : getCoins) {
            Coin existingCoin = coinRepository.findByName(coin.getName()).orElseGet(() -> coinRepository.save(coin));
            BeanUtils.copyProperties(coin, existingCoin, "pid");
            coinRepository.save(existingCoin);
        }

        List<Person> persons = personRepository.findAll();
        for (Person person : persons) {
            Coin coinFromDB = coinRepository.findByCode(person.getCode()).orElseThrow(
                    () -> new IllegalArgumentException("Coin of person with this code not found"));

            Double differnceBetwenCourses =
                    Math.abs((coinFromDB.getPrice_usd() - person.getPrice()) / person.getPrice() * 100);

            if (differnceBetwenCourses > 1) {
                log.warn("Code of coin: " + person.getCode() + " Name of person: " + person.getName() + " changed to: " + differnceBetwenCourses);
            }
        }


    }


}
