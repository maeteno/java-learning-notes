package com.maeteno.jss.optional.example1;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        Person person = new Person();
        Optional<Person> optPerson = Optional.ofNullable(person);

        String name = optPerson
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknow");

        System.out.println(name);
    }
}