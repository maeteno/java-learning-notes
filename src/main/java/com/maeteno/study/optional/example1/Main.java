package com.maeteno.study.optional.example1;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class Main {

    public static void main(String[] args) {
        Person person = new Person();
        Optional<Person> optPerson = Optional.ofNullable(person);

        String name = optPerson
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknow");

        log.info(name);
    }
}