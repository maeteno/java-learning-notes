package com.maeteno.study.utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    
    private Utils(){}

    public static List<Integer> mock(int len){
        var list = new ArrayList<Integer>();

        for (int index = 0; index < len ;index++){
            list.add(index);
        }

        return list;
    }
}
