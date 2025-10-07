
package main;

import domaine.Trader;
import domaine.Transaction;

import java.sql.ClientInfoStatus;
import java.util.*;
import java.util.stream.Collectors;

public class ExercicesPanaches {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        ExercicesPanaches main = new ExercicesPanaches(transactions);
        main.run();
    }

    private List<Transaction> transactions;

    public ExercicesPanaches(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void run() {
        // Complete the methods below based on the exercise descriptions
        exercice1();
        exercice2();
        exercice3();
        exercice4();
        exercice5();
        exercice6();
    }

    private void exercice1() {
        // TODO: Filter transactions of Cambridge, map to their values, and find max.
        //var list = transactions
        Optional<Integer> maxTransactionValueInCambridge = transactions
                .stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))//filter ne va garder que ce aui répond a la condition
                .map(Transaction::getValue) //map va changer le type d'information qu'on récupere
                .reduce((t1,t2)-> t1>t2? t1 : t2);//REDUIT ET VA PRENDRE LE PLUS GRAND A CHAQUE FOIS --> MAX
                //.orElse(-1);
        System.out.println(maxTransactionValueInCambridge);
    }

    private void exercice2() {
        // TODO: Filter transactions for traders in Cambridge, group by trader, and count their transactions.
        //var list = transactions
        Map<Trader, Long> transactionCountInCambridge = transactions
                .stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .collect(Collectors.groupingBy(Transaction::getTrader,Collectors.counting()));
        System.out.println(transactionCountInCambridge);
    }

    private void exercice3() {
        // TODO: Filter transactions over 500, map trader names, sort by name length, find the longest.
        //var list = transactions
        Optional<String>longestTraderName = transactions
                .stream()
                .filter(transaction -> transaction.getValue()>500)
                .map(transaction -> transaction.getTrader().getName())
                //.sorted(Comparator.comparing(String::length))
                .sorted((l1,l2)-> Integer.compare(l2.length(),l1.length()))
                //.reduce((t1,t2)->t1.length() > t2.length() ? t1 : t2);
                .reduce((l1,l2)->l1);

        System.out.println(longestTraderName);
    }

    private void exercice4() {
        // TODO: Group transactions by city, map to transaction values, and compute the average.
        //var list = transactions
        Map<String, Double> averageTransactionByCity = transactions
                .stream()
                .collect(Collectors.groupingBy(t-> t.getTrader().getCity(),Collectors.averagingDouble(Transaction::getValue)));
    }

    private void exercice5() {
        // TODO: Filter transactions in Milan, map to values, find the min, and handle empty results.
        //var list = transactions
        Optional<Integer> minTransactionValueInMilan = transactions
                .stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Milan"))
                //.map(transaction -> transaction.getValue())
                .map(Transaction::getValue)//les deux expressions veulent dirent la même chose
                .reduce((t1,t2)-> t1<t2 ? t1 : t2);
                //.orElse(-1);
        System.out.println(minTransactionValueInMilan);
    }
    private void exercice6() {
        // TODO: group transaction by year
        Map<Integer, List<Transaction>> transactionsByYear;
        transactionsByYear = transactions
                .stream()
                .collect(Collectors.groupingBy(Transaction::getYear));
        System.out.println(transactionsByYear);
    }
}
