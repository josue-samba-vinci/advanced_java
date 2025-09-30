package domaine;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Plat {
    public enum Difficulte {
        X, XX, XXX, XXXX, XXXXX;

        public String toString() {
            return super.toString().replace("X", "*");
    }
}

    public enum Cout {
        $, $$, $$$, $$$$, $$$$$;

        public String toString() {
            return super.toString().replace("$", "€");
        }
    }
    private final String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes;
    private List<Instruction> recette = new ArrayList<>();
    private Set<Ingredient> ingredients = new HashSet<>();

    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout) {
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
        this.dureeEnMinutes = Duration.ofMinutes(0);
    }
    public String getNom() {
        return nom;
    }
    public int getNbPersonnes() {
        return nbPersonnes;
    }
    public Difficulte getNiveauDeDifficulte() {
        return niveauDeDifficulte;
    }
    public Cout getCout() {
        return cout;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }
    public void insererInstruction (int position, Instruction instruction) {
        if (position < 0 || position > recette.size()) {
            throw new IllegalArgumentException("Position invalide");
        }
        recette.add(position, instruction);
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }  
    
    public void ajouterInstruction (Instruction instruction) {
        recette.add(instruction);
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }

    public Instruction remplacerInstruction (int position, Instruction instruction) {
        if (position < 0 || position >= recette.size()) {
            throw new IllegalArgumentException("Position invalide");
        }
        if (recette.get(position-1) == null) {
            throw new IllegalArgumentException("Instruction ne peut pas etre null");
        }
        Instruction ancienneInstruction = recette.get(position-1);
        recette.set(position-1, instruction);
        dureeEnMinutes = dureeEnMinutes.minusMinutes(ancienneInstruction.getDureeEnMinutes().toMinutes());//moins les minutes de l'ancienne instruction
        dureeEnMinutes = dureeEnMinutes.plusMinutes(instruction.getDureeEnMinutes().toMinutes());//plus les minutes de la nouvelle instruction
        return ancienneInstruction;
    }

   public Instruction supprimerInstruction (int position) {
       if (position < 0 || position >= recette.size()) {
           throw new IllegalArgumentException("Position invalide");
       }
       Instruction instructionASupprimer = recette.get(position-1);
       recette.remove(position-1);
       dureeEnMinutes = dureeEnMinutes.minusMinutes(instructionASupprimer.getDureeEnMinutes().toMinutes());
       return instructionASupprimer;
   }
    public List<Instruction> instructions(){
        return Collections.unmodifiableList(recette);
    }

    

    @Override
	public String toString() {
		String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60);
		String res = this.nom + "\n\n";
		res += "Pour " + this.nbPersonnes + " personnes\n";
		res += "Difficulté : " + this.niveauDeDifficulte + "\n";
		res += "Coût : " + this.cout + "\n";
		res += "Durée : " + hms + " \n\n";
		res += "Ingrédients :\n";
		for (Ingredient ing : this.ingredients) {
			res += ing + "\n";
		}
		int i = 1;
		res += "\n";
		for (Instruction instruction : this.recette) {
			res += i++ + ". " + instruction + "\n";
		}
		return res;
	}
    

}
