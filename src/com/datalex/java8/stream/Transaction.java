package com.datalex.java8.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * Created by shaojie.xu on 19/04/2017.
 */
public class Transaction {

    private Integer id;
    private Integer value;
    private TransactionType type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    private static List<Integer> getListOfIdSortByValueWithStream(List<Transaction> transactions) {

        List<Integer> transactionsIds =
                        transactions.stream()
                        .filter(t -> t.getType() == TransactionType.GROCERY)
                        .sorted(comparing(Transaction::getValue).reversed())
                        .map(Transaction::getId)
                        .collect(toList());
        return transactionsIds;
    }

    private static List<Integer> getListOfIdSortByValueWithCollection(List<Transaction> transactions) {

        // filter out the GROCERY type
        List<Transaction> groceryTransactions = new ArrayList<>();
        for(Transaction t: transactions){
            if(t.getType() == TransactionType.GROCERY){
                groceryTransactions.add(t);
            }
        }

        // sort by value
        Collections.sort(groceryTransactions, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                return t2.getValue().compareTo(t1.getValue());
            }
        });

        // add to another List of Integer
        List<Integer> transactionIds = new ArrayList<>();
        for(Transaction t: groceryTransactions){
            transactionIds.add(t.getId());
        }

        return transactionIds;

    }
}
enum TransactionType{
    FAHSION, GROCERY
}
