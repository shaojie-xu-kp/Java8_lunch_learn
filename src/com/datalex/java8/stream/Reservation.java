package com.datalex.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * this is an typical case in TDP where we have to deal with reservation
 * to fetch sub attributes or to check if there is a certain sub attribute populated in the request
 * before java 8, we normally need to do a bunch of for loop and NULL checks from the top reservation level
 * all the way done to the policyCode level.
 * With Java 8, we can see how the code is neat and concise to tackle with the situation.
 * Another benefit we will gain here is when it come to refactoring the code to allow multi-thread access.
 * In the method of many for loop and if check, we need to synchronize the whole block or use reentrant lock to be sure
 * only one thread will access each time, there are a lot of efforts involved
 * By using steam, there is nothing needs to be done but add a parallel() after stream() call
 * Created by shaojie.xu on 02/05/2017.
 */
public class Reservation {

    private List<ResComponent> resComponents;

    public List<ResComponent> getResComponents() {
        return resComponents;
    }

    public void setResComponents(List<ResComponent> resComponents) {
        this.resComponents = resComponents;
    }

    public Reservation(List<ResComponent> resComponents) {
        this.resComponents = resComponents;
    }

    /**
     *     simulate the realistic scenario in TDP, all the attribute of type list will be instantiate
     *     with an empty list
     */
    public static void main (String... args){


        // see the out put of code 1
        ResComponent resComponent1 = new ResComponent(1,Arrays.asList(new Policy("code1")));
        // if no policy code, we expect to have an exception thrown in method validateAnyPolicyCodeInJava8
        // ResComponent resComponent1 = new ResComponent(1,Arrays.asList());
        ResComponent resComponent2 = new ResComponent(2,Arrays.asList());
        Reservation res1 = new Reservation(Arrays.asList(resComponent1));
        Reservation res2 = new Reservation(Arrays.asList(resComponent2));
        List<Reservation> reservations = Arrays.asList(res1,res2);

        System.out.println(getAllPolicyCodeWithResComSequence1BeforeJava8(reservations));
        System.out.println(getAllPolicyCodeWithResComSequence1InJava8(reservations));
        System.out.println(validateAnyPolicyCodeInJava8(reservations));

    }

    private static List<String> getAllPolicyCodeWithResComSequence1BeforeJava8(List<Reservation> reservations) {

        List<String> policyCodes = new ArrayList<>();
        for(Reservation reservation : reservations){
            for(ResComponent resComponent : reservation.getResComponents())
                if (resComponent.getSequence() == 1) {
                    for (Policy policy : resComponent.getPolicies()) {
                            if(policy.getPolicyCode() != null)
                                policyCodes.add(policy.getPolicyCode());
                    }
                }
        }
        return policyCodes;
    }


    private static List<String> getAllPolicyCodeWithResComSequence1InJava8(List<Reservation> reservations) {

        return reservations
                    .stream()
//                    .parallel()
                        .map(reservation -> reservation.getResComponents())
//                        .flatMap(resvCom -> resvCom.stream())
                        .flatMap(Collection::stream)
                        .filter(resComponent ->  resComponent.getSequence() == 1)
                        .map(resComponent -> resComponent.getPolicies())
                        .flatMap(Collection::stream)
                        .map(Policy::getPolicyCode)
                        .collect(toList());
    }

    private static String validateAnyPolicyCodeInJava8(List<Reservation> reservations) {
        return reservations.stream()
                .map(reservation -> reservation.getResComponents())
                .flatMap(Collection::stream)
                .map(resComponent -> resComponent.getPolicies())
                .flatMap(Collection::stream)
                .map(Policy::getPolicyCode)
                .findAny()
                .orElseThrow(() -> new AnyException("there is no policy code"));
    }
}

class ResComponent {

    private int sequence;

    private List<Policy> policies;

    public List<Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public ResComponent(int sequence, List<Policy> policies) {
        this.sequence = sequence;
        this.policies = policies;
    }
}

class Policy {
    private String policyCode;

    public String getPolicyCode() {
        return policyCode;
    }

    public Policy(String policyCode) {
        this.policyCode = policyCode;
    }

    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }
}

class AnyException extends RuntimeException
{
    /**
     * Context code.
     */
    private int m_context = 0;
    /**
     * Major code.
     */
    private int m_major = 0;
    /**
     * Minor code.
     */
    private int m_minor = 0;
    /**
     * Severity.
     */
    private int m_severity = 1;
    /**
     * Host message.
     */
    private String m_hostMessage = null;

    public AnyException(String m_hostMessage) {
        this.m_hostMessage = m_hostMessage;
    }

    @Override
    public String toString() {
        return "AnyException{" +
                "m_context=" + m_context +
                ", m_major=" + m_major +
                ", m_minor=" + m_minor +
                ", m_severity=" + m_severity +
                ", m_hostMessage='" + m_hostMessage + '\'' +
                '}';
    }
}