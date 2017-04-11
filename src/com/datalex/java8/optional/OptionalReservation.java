package com.datalex.java8.optional;

import java.util.Optional;

/**
 * Created by shaojie.xu on 11/04/2017.
 */
public class OptionalReservation {


    static class Reservation {

        ResComponent resComponent;

        public ResComponent getResComponent() {
            return resComponent;
        }
    }

    static class ResComponent {

        Policy policy;

        public Policy getPolicy() {
            return policy;
        }
    }

    static class Policy {

        String policyName;

        public String getPolicyName() {
            return policyName;
        }
    }

    public static void main(String... args ) {

        Reservation reservation = new Reservation();


        OptionalReservation.printPolicyNameWithNullCheck(reservation);
        OptionalReservation.printPolicyNameWithOptional(reservation);

    }

    private static void printPolicyNameWithNullCheck(Reservation reservation) {

        if(reservation != null
                && reservation.getResComponent() != null
                && reservation.getResComponent().getPolicy() != null) {
            System.out.println(reservation.getResComponent().getPolicy().getPolicyName());
        }
    }


    private static void printPolicyNameWithOptional(Reservation reservation) {

        //lambda
        Optional.of(reservation)
                .flatMap(res -> Optional.ofNullable(res.getResComponent()))
                .flatMap(resc -> Optional.ofNullable(resc.getPolicy()))
                .flatMap(p -> Optional.ofNullable(p.getPolicyName()))
                .ifPresent(System.out::println);


        //method reference
        Optional.of(reservation)
                .map(Reservation::getResComponent)
                .map(ResComponent::getPolicy)
                .map(Policy::getPolicyName)
                .ifPresent(System.out::println);

    }

}
