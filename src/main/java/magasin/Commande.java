package magasin;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Commande {
    private Client client;
    private ArrayList<Produit> produitsCommandes;
    private double total;
    private LocalDateTime dateHeure;
    
    public Commande(Client client, Panier panier, LocalDateTime dateHeure) {
        this.client = client;
        this.produitsCommandes = new ArrayList<>(panier.getProduits());
        this.total = panier.calculerTotal();
        this.dateHeure = dateHeure;
    }

    public void afficherDetails() {
        System.out.println("Commande en date du " + dateHeure + ", Client: " + client.getNom() + ", Total: " + total);
        for (Produit produit : produitsCommandes) {
            System.out.print("\t"); 
            produit.afficherDetails();
        }
    }
    
}
