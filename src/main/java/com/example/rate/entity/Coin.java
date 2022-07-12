package com.example.rate.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "coin")
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long pid;
    Long id;
    String symbol;
    String name;
    String nameid;
    Integer rank;
    Double price_usd;
    Double percent_change_24h;
    Double percent_change_1h;
    Double percent_change_7d;
    Double market_cap_usd;
    Double volume24;
    Double volume24_native;
    Double csupply;
    Double price_btc;
    Double tsupply;
    Double msupply;
}
