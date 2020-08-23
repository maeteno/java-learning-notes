package com.maeteno.jss.optional.example1;

import java.util.Optional;

public class Car {
   private Optional<Insurance> insurance = Optional.empty();

   public Optional<Insurance> getInsurance() {
      return this.insurance;
   }
}