package main;

import domaine.Employe;
import domaine.Genre;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import java.util.function.Function;
import java.util.function.Consumer;

public class ExerciceFunctionalInterface {
    public static class FonctionNomEmploye implements Function<Employe,String>{
        public String apply(Employe employe){
            return employe.getNom();
        }
    }
    public static class TousLesEmployes implements Consumer<Employe>{
        public void accept(Employe employe){
            System.out.println(employe);
        }
    }
    public static List<Employe> employes;
    public static void main(String[] args) {
        employes = new ArrayList<>();

        employes.add(new Employe(Genre.HOMME, 185, "Bob"));
        employes.add(new Employe(Genre.FEMME, 225, "Alice"));
        employes.add(new Employe(Genre.HOMME, 155, "John"));
        employes.add(new Employe(Genre.FEMME, 165, "Carole"));
        employes.add(new Employe(Genre.HOMME, 185, "Alex"));
        employes.add(new Employe(Genre.HOMME, 185, "Bart"));

        Stream<Employe> listDesHommes = employes
                .stream()
                .filter(e -> e.getGenre() == Genre.HOMME);

        exMap();

        exComparator();

        exForEach();

    }

    /**
     * Remplacer l'instatiation de la classe EmployeComparator par un lambda
     */
    private static void exComparator() {
       // employes.sort(new EmployeComparator());
        employes.sort(
                Comparator.comparingInt(Employe::getTaille).reversed()
                        .thenComparing(Employe::getNom)
        );
        System.out.println("Employés triés:");
        System.out.println(employes);


    }

    /**
     * Trouver le type du paramètre de la méthode map.
     * Ensuite créer une classe implémentant la functional interface correspondante pour
     * remplacer le lambda en paramètre par une instance de celle-ci.
     */

    private static void exMap() {
        Stream<String> listeNom = employes.stream()
                .filter(e -> e.getGenre() == Genre.HOMME)
                .sorted(Comparator.comparingInt(Employe::getTaille)
                        .reversed())
                .map(new FonctionNomEmploye());
        listeNom.forEach(System.out::println);
    }


    /**
     * Trouver le type du paramètre de la méthode foreach.
     * Ensuite créer une classe implémentant la functional interface correspondante pour
     * remplacer le lambda en paramètre par une instance de celle-ci.
     */
    private static void exForEach(){
        employes.forEach(new TousLesEmployes());
        System.out.println(employes);
        //on remplace le lambda par une instance de classe
    }
}
