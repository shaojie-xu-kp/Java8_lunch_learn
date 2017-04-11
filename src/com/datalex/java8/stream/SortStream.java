package com.datalex.java8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shaojie.xu on 10/04/2017.
 */
public class SortStream {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(0,4,5,6,2,3,1,8,7,9,56,17,29);

        // natural sorting
        System.out.println(numbers.stream()
                .sorted()
                .collect(Collectors.toList()));
        System.out.println("\n====================================");



        // natural sorting
        System.out.println(numbers.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList()));
        System.out.println("\n====================================");



        // filter out the bigger ones and sort them with lambda ex
        System.out.println(numbers.stream()
                .filter( number -> number > 10)
                .sorted((a,b) -> (a.compareTo(b)))
                .collect(Collectors.toList()));

    }

}
