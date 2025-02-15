package com.deepjyot.java_stream_api;

import java.util.Arrays;
import java.util.List;

public class UpperCase {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("java", "streams", "practice", "questions");

        var upperCaseWords = words.stream().map(String::toUpperCase).toList() ;

        System.out.println(upperCaseWords);

    }


}
