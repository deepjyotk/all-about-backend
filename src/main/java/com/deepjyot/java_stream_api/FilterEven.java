package com.deepjyot.java_stream_api;

import java.util.Arrays;
import java.util.List;

public class FilterEven {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);


        var evenList = numbers.stream().filter(x->x%2==0).toList() ;

        for (int x : evenList){
            System.out.println(x);
        }
    }
}
