package com.datalex.java8.optional;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Optional;

/**
 * Created by shaojie.xu on 11/04/2017.
 */
public class OptionalBasic {

    public static void main(String[] args) {

        Optional<String> optionalGoal = Optional.of("goal");
        Optional<String> optionalNull = Optional.ofNullable(null);

        System.out.println(optionalGoal.isPresent());           // true
        System.out.println(optionalGoal.get());           // "goal"
        System.out.println(optionalGoal.orElse("fallback"));  //"goal"

        System.out.println(optionalNull.isPresent());    //false
        System.out.println(optionalNull.orElse("fallback"));  //"fallback"

        // here an IllegalArgumentException will be thrown, in real case
        // in real scenario an application checked exception could be defined.
        System.out.println(optionalNull.orElseThrow(() ->
                                    new IllegalArgumentException("there is no element in it")));

        optionalGoal.ifPresent((s) -> System.out.println(s.charAt(0)));     // "g"
        optionalNull.ifPresent((s) -> System.out.println(s.charAt(0)));     // no output, no NPE

    }

}
