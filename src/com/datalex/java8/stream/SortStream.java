package com.datalex.java8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by shaojie.xu on 10/04/2017.
 */
public class SortStream {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(0,4,5,6,2,3,1,8,7,9,56,17,29);

        // natural sorting
        numbers.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("\n====================================");



        // natural sorting
        numbers.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
        System.out.println("\n====================================");



        // filter out the bigger ones and sort them with lambda
        numbers.stream()
                .filter( number -> number > 10)
                .sorted((a,b) -> (a.compareTo(b)))
                .forEach(System.out::println);

    }

}
