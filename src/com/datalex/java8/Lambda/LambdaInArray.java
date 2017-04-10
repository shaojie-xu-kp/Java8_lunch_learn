package com.datalex.java8.Lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by shaojie.xu on 10/04/2017.
 */
public class LambdaInArray {

    public static void main(String... args) {

        List<String> names = Arrays.asList("peter", "anna", "mike", "luna");

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

        // java 8 code with forEach with lambda with type
        names.forEach((String name) -> System.out.println(name));

        // java 8 code with forEach with lambda without type
        names.forEach(name -> System.out.println(name));

        // java 8 code with forEach with method reference
        names.forEach(System.out::println);


    }

}
