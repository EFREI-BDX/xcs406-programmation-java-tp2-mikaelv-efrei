package magasin;

public class Produit {
    private int id;
    private String nom;
    private double prix;
    private int quantite;

    public Produit(int id, String nom, double prix, int quantite) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }

    public int getId() {
        return id;  
    }

    public String getNom() {
        return nom;
    }       

    public double getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }
}
