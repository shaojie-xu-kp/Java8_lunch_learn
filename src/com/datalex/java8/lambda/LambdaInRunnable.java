package com.datalex.java8.lambda;

/**
 * Created by shaojie.xu on 10/04/2017.
 */
public class LambdaInRunnable {


    public static void main(String... args) {

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Lambda");
            }
        };
        new Thread(runnable1).start();


        Runnable runnable2 = () -> System.out.println("Hello Lambda");
        new Thread(runnable2).start();

    }


}
