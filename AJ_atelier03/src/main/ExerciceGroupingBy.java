package main;


import domaine.Trader;
import domaine.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ExerciceGroupingBy {
    enum TransactionsLevel {
        VERY_HI, HI, LO, ME;
    }

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
        ExerciceGroupingBy main = new ExerciceGroupingBy(transactions);
        main.run();
    }

    /**
     * La liste de base de toutes les transactions.
     */
    private List<Transaction> transactions;
    // Pour tester le vide
    private List<Transaction> pasDeTransaction = new ArrayList<>();
    /**
     * Crée un objet comprenant toutes les transactions afin de faciliter leur usage pour chaque point de l'énoncé
     *
     * @param transactions la liste des transactions
     */
    public ExerciceGroupingBy(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void run() {
        this.groupBy1();
        this.groupBy2();
        this.groupBy3();
    }

    private void groupBy1() {
        System.out.println("GroupBy1");
        Map<Trader, List<Transaction>>transactionsByTrader = transactions
                .stream()
                .collect(groupingBy(Transaction::getTrader));
        System.out.println(transactionsByTrader);
    }

    private void groupBy2() {
        System.out.println("GroupBy2");
        Map<Trader,Long> numberOfTransactionsByTrader = transactions
                .stream()
                .collect(groupingBy(Transaction::getTrader,counting()));
        System.out.println(numberOfTransactionsByTrader);
    }


    private void groupBy3() {
        System.out.println("GroupBy3");
        Map<TransactionsLevel,List<Transaction>> transactionsByLevel = transactions
                .stream()
                .collect(groupingBy(transaction ->{
                    if (transaction.getValue()>=1000) return TransactionsLevel.VERY_HI;
                    else if (transaction.getValue()<1000 && transaction.getValue()>=800) return TransactionsLevel.HI;
                    else if (transaction.getValue()<800 && transaction.getValue()>=600) return TransactionsLevel.ME;
                    else return TransactionsLevel.LO;
                }));
        System.out.println(transactionsByLevel);

    }
}
