package com.example.rate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rate.entity.Person;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {

    Optional<Person> findByName(String name);
}
