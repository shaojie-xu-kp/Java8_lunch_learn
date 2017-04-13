package com.datalex.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by shaojie.xu on 13/04/2017.
 */
public class SupplierStream {

    public static void main(String[] args) {
        List<String> strings =
                Arrays.asList("d2", "a2", "b1", "b3", "c1");

        findStringWith1(strings);

        // find the d element
        System.out.println(findBySupplier(() -> strings.stream().filter(s -> s.contains("d"))));

        // find the a element
        System.out.println(findBySupplier(() -> strings.stream().filter(s -> s.contains("a"))));

        // find the b element
        System.out.println(findBySupplier(() -> strings.stream().filter(s -> s.contains("b"))));


    }

    private static void findStringWith1(List<String> stringCollection) {
        Supplier<Stream<String>> streamSupplier =
                () -> stringCollection
                        .stream()
                        .filter(s -> s.contains("1"));
        System.out.println(streamSupplier.get().collect(Collectors.toList()));
    }


    private static List<String> findBySupplier(Supplier<Stream<String>> streamSupplier){
        return streamSupplier.get().collect(Collectors.toList());
    }

}
