package com.maeteno.study.optional.example1;

import java.util.Optional;

public class Person {
    private final Optional<Car> car = Optional.empty();

    public Optional<Car> getCar() {
        return this.car;
    }
}