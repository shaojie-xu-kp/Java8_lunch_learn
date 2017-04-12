package com.datalex.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by shaojie.xu on 12/04/2017.
 */
public class ForkJoinPoolInStream {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("a1", "a2", "b1", "b2", "c1", "c2", "d1", "d2", "e1", "e2");
        checkForkJoinParallelism();
        filterMapSortThreadMonitoring(strings);
    }

    private static void filterMapSortThreadMonitoring(List<String> strings) {
        strings
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n", s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n", s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .sorted((s1, s2) -> {
                    System.out.format("sort: %s <> %s [%s]\n", s1, s2, Thread.currentThread().getName());
                    return s1.compareTo(s2);
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n", s, Thread.currentThread().getName()));
    }

    private static void checkForkJoinParallelism() {
        // -Djava.util.concurrent.ForkJoinPool.common.parallelism=5
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());
    }


}
