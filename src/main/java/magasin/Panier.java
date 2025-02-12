package magasin;

import java.util.ArrayList;

public class Panier {
    private ArrayList<Produit> produits;

    public Panier() {
        this.produits = new ArrayList<>();
    }

    public ArrayList<Produit> getProduits() {
        return produits;
    }
    
    public void ajouterProduit(Produit produit) {
        produits.add(produit);
    }

    public void supprimerProduit(Produit produit) {
        produits.remove(produit);
    }

    public void afficher() {
        for (Produit produit : produits) {
            produit.afficherDetails();
        }
    }

    public void vider() {
        produits.clear();
    }
    
    public double calculerTotal() {
        double total = 0;
        for (Produit produit : produits) {
            total += produit.getPrix();
        }
        return total;
    }
    
    
}
