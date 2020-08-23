package com.maeteno.jss.optional.example2;

import java.util.Optional;

public class Main {
    public static void main(String[] args) throws MyException {
        Person person = new Person();

        String name = Optional.ofNullable(person)
                .map(Person::getCar)
                .map(Car::getInsurance)
                .map(Insurance::getName)
                .orElseThrow(MyException::new);

        System.out.println(name);
    }
}