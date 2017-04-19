package com.datalex.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by shaojie.xu on 11/04/2017.
 */
public class ParallelStream {


    public static final int MAX = 1_000_000;

    public static long T1;

    public static long T2;

    public static void sortSequential() {
        List<String> values = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // sequential sorting
        T1 = System.currentTimeMillis();

        values.stream().sorted().findFirst();

        T2 = System.currentTimeMillis();

        System.out.println(String.format("sequential sort %d number took: %d ms", MAX, T2-T1));
    }

    public static void sortParallel() {
        List<String> values = new ArrayList<>(MAX);
        for (int i = 0; i < MAX; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // parallel sorting
        T1 = System.currentTimeMillis();

        values.parallelStream().sorted().findFirst();

        T2 = System.currentTimeMillis();

        System.out.println(String.format("parallel sort %d number took: %d ms", MAX, T2-T1));
    }

    public static void main(String[] args) {
        sortSequential();
        sortParallel();
    }
}
