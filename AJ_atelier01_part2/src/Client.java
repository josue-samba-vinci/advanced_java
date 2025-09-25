import java.util.Objects;

public class Client {
    private static int numeroSuivant = 1;
    private int numero;
    private String nom,prenom,telephone;
    private Commande commandeEnCours;

    public Client( String nom, String prenom, String telephone) {
        this.numero=numeroSuivant++;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Commande getCommandeEnCours() {return commandeEnCours;}

    public boolean enregistrer(Commande commande){
        if (commandeEnCours!=null||commande.getClient()!=this){
            return false;
        }
        this.commandeEnCours=commande;
        return true;
    }

    public boolean cloturerCommandeEnCours(){
        if (commandeEnCours==null){
            return false;
        }
        commandeEnCours=null;
        return true;
    }

    public String getTelephone() {
        return telephone;
    }
    public boolean equals(Object o){
        if (this==o) return true;
        if (o==null || getClass()!= o.getClass()) return false;
        Client client = (Client) o;
        return numero == client.numero;
    }
    public int hashCode(){
        return Objects.hashCode(numero);
    }

    @Override
    public String toString() {
        return "client n° " + numero + " (" + prenom  + " " + nom + ", telephone : " + telephone +")";
    }
}
