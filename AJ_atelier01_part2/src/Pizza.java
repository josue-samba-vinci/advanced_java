import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public abstract class Pizza implements Iterable<Ingredient>{
    private static final double PRIX_BASE = 5.0;
    private String titre;
    private String description;
    private ArrayList<Ingredient> ingredients;

    public Pizza(String titre,String description){
        this.titre = titre;
        this.description = description;
        this.ingredients = new ArrayList<>();
        }
    public Pizza(String titre, String description, ArrayList<Ingredient>ingredients){
        this(titre, description);
        for (Ingredient ingredient : ingredients){
            int compt=0;
            for (Ingredient ingredient1 : ingredients){
                if (ingredient.equals(ingredient1)){
                    compt++;
                }
            }
            if (compt>=2){
                throw new IllegalArgumentException("Deux fois le même ingrédient dans une pizza");
            }
        }
        this.ingredients=ingredients;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public boolean ajouter(Ingredient ingredient){
        if (ingredients.contains(ingredient)){
            return false;
        }
        ingredients.add(ingredient);
        return true;
    }

    public boolean supprimer(Ingredient ingredient){
        if (ingredients.contains(ingredient)){
            ingredients.remove(ingredient);
            return true;
        }
        return false;
    }

    public double calculerPrix(){
        double prixTotal = PRIX_BASE;
        for(Ingredient ingredient : ingredients){
            prixTotal+=ingredient.getPrix();
        }
        return prixTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza that = (Pizza) o;
        return Objects.equals(titre, that.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titre);
    }

    @Override
    public Iterator<Ingredient> iterator() {
        return this.ingredients.iterator();
    }

    @Override
    public String toString() {
        String infos = titre + "\n" + description + "\nIngrédients : ";
        for (Ingredient ingredient : ingredients){
            infos +="\n" + ingredient.getNom();
        }
        infos +="\nprix : " + calculerPrix() + " euros";
        return infos;
    }
}

