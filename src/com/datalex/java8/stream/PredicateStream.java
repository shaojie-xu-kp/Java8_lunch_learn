package com.datalex.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by shaojie.xu on 13/04/2017.
 */
public class PredicateStream {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(0, 4, 5, 6, 2, 3, 1, 8, 7, 9, 56, 17, 29);

        // print numbers bigger than 5
        printDynamically(numbers, n -> (n > 5));

        // print all the even number
        printDynamically(numbers, n -> (n % 2 == 0));

        // print all the odd number
        printDynamically(numbers, n -> (n % 2 != 0));

        // print all the prime number
        printDynamically(numbers, PredicateStream::isPrime);


    }

    private static boolean isPrime(Integer n) {
        return !IntStream.range(2, n).anyMatch(i -> n % i == 0);
    }


    private static void printDynamically(List<Integer> numbers, Predicate<Integer> predicate) {

        System.out.println(numbers.stream()
                .filter(predicate)
                .collect(Collectors.toList()));

    }


}
