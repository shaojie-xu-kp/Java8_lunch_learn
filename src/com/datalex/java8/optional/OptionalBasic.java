package com.datalex.java8.optional;

import java.util.Optional;

/**
 * Created by shaojie.xu on 11/04/2017.
 */
public class OptionalBasic {

    public static void main(String[] args) {

        Optional<String> optional = Optional.of("goal");

        optional.isPresent();           // true
        optional.get();                 // "goal"
        optional.orElse("fallback");    // "goal"

        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "g"

    }

}
