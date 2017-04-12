package com.datalex.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by shaojie.xu on 11/04/2017.
 */
public class CollectorsStream {

    public static void main(String... args) {


        List<Person> persons = new ArrayList<>();
        persons.add(new Person("perter", 24));
        persons.add(new Person("ian", 30));
        persons.add(new Person("jacob", 3));
        persons.add(new Person("angela", 28));
        persons.add(new Person("mike", 24));
        persons.add(new Person("steve", 60));
        persons.add(new Person("andy", 30));

        sortAndCollectToPersonList(persons);
        collectToPersonNameSet(persons);
        groupByAge(persons);


    }


    private static void sortAndCollectToPersonList(List<Person> persons){

        List<Person> sortedPersons = persons.stream()
                                            .sorted((p1, p2) -> (p1.getAge() - p2.getAge() > 0 ? 1 : -1))
                                            .collect(Collectors.toList());
        System.out.println(sortedPersons);


    }

    private static void collectToPersonNameSet(List<Person> persons){

        Set<String> personNames = persons.stream()
//                                    .map(p -> p.getName())
                                    .map(Person::getName)
                                    .collect(Collectors.toSet());
        System.out.println(personNames);

    }

    private static void groupByAge(List<Person> persons){

        Map<Integer, List<Person>> ageMap = persons.stream()
                                                    .collect(Collectors.groupingBy(Person::getAge));
        System.out.println(ageMap);

    }



}


class Person {

    private String name;
    private int age;

    public Person(String name,  int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("%s : %d", name, age);
    }
}
