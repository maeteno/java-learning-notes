package com.maeteno.jss.optional.example1;

import java.util.Optional;

public class Person {
    private Optional<Car> car = Optional.empty();

    public Optional<Car> getCar() {
        return this.car;
    }
}