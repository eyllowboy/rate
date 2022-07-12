package com.example.rate.repository;

import com.example.rate.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CoinRepository extends JpaRepository<Coin, Long> {


    Optional<Coin> findByName(String name);

    @Query("select c from Coin c where c.id=:code")
    Optional<Coin> findByCode(@Param(value = "code")Long code);
}
