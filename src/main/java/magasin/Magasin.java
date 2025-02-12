package magasin;

import java.util.ArrayList;

public class Magasin {
    private ArrayList<Produit> produits;
    
    public Magasin() {
        this.produits = new ArrayList<>();
    }

    public void ajouterProduit(Produit produit) {
        produits.add(produit);
    }

    public void afficherProduitsDisponibles() {
        for (Produit produit : produits) {
            produit.afficherDetails();
        }
    }   

    public Produit trouverProduitParId(int id) {
        for (Produit produit : produits) {
            if (produit.getId() == id) {
                return produit;
            }
        }
        return null;
    }
    
}