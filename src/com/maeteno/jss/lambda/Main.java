package com.maeteno.jss.lambda;

import java.util.function.Predicate;

public class Main {

    public static void main(String[] args){
        lambda((String a) -> {System.out.println(a);return false;});
    }

    public static void lambda(Predicate<String> p){
        p.test("12233");
    }
}
