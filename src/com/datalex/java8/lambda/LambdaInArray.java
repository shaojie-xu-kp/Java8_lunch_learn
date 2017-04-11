package com.datalex.java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by shaojie.xu on 10/04/2017.
 */
public class LambdaInArray {

    public static void main(String... args) {

        List<String> names = Arrays.asList("peter", "anna", "mike", "luna", "jacob", "angela");

        LambdaInArray.testLambdaWithConsumer(names);
        LambdaInArray.testLambdaWithComparator(names);

    }

    private static void testLambdaWithComparator(List<String> names) {

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });

        Collections.sort(names, (String a, String b) -> b.compareTo(a));

        Collections.sort(names, (a, b) -> b.compareTo(a));

        Collections.sort(names, Comparator.reverseOrder());

    }


    private static void testLambdaWithConsumer(List<String> names) {

        // java 7 code
        for (String name : names)
            System.out.println(name);

        // java 8 code with forEach
        names.forEach(new Consumer<String>() {
            @Override
            public void accept(String name) {
                System.out.println(name);
            }
        });

        // java 8 code with lambda
        names.forEach((String name) -> System.out.println(name));

        // java 8 code with lambda
        names.forEach(name -> System.out.println(name));

        // java 8 code with method reference
        names.forEach(System.out::println);

    }

}
