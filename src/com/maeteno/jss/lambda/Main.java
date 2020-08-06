package com.maeteno.jss.lambda;

import java.util.function.Predicate;

public class Main {
    
    public static void main(String[] args){
        Predicate<String> p = (String a) -> {System.out.println(a);return false;};
        lambda(new Predicate<String>(){
            public boolean test(String s){
                return true;
            }
        });
    }

    public static void lambda(Predicate<String> p){
        p.test("12233");
    }

    class Demo implements Predicate<String> {
        public boolean test(String t){
            return false;
        }
    }
}
