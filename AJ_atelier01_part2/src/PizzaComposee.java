import java.security.PublicKey;
import java.util.ArrayList;

public class PizzaComposee extends Pizza{
    public int REMISE = 15;

    public PizzaComposee(String titre, String description, ArrayList<Ingredient>ingredients){
        super(titre,description,ingredients);
    }

    @Override
    public boolean ajouter(Ingredient ingredient) {
        throw new UnsupportedOperationException("Les ingrédients d'une pizza composée ne peuvent pas être modifiés");
    }

    @Override
    public boolean supprimer(Ingredient ingredient) {
        throw new UnsupportedOperationException("Les ingrédients d'une pizza composée ne peuvent pas être modifiés");
    }

    public double calculerPrix(){
        double prixParent = super.calculerPrix();
        return Math.ceil(prixParent - (prixParent * 15 / 100));
        //double valeur = super.calculerPrix()*((REMISE/100));
        //double prixFinal = super.calculerPrix() - valeur;
        //return Math.ceil(prixFinal); //math ceil permet d'arrondir à l'entier supérieur comme demandé dans l'exercice
    }
}
