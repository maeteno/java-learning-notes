package com.maeteno.jss.lambda;

import com.maeteno.jss.utils.Utils;

public class Main {

    public static void main(String[] args){
        var list = Utils.mock(100);
        var count = list.stream().filter(item -> item % 3 ==0).count();

        System.out.println(count);
    }
}
