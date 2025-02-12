package magasin;

public class Produit {
    private int id;
    private String nom;
    private double prix;

    public Produit(int id, String nom, double prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
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


    protected void setPrix(double prix) {
        this.prix = prix;
    }

    public void afficherDetails() {
        System.out.println("ID: " + id + ", Nom: " + nom + ", Prix: " + prix);
    }

}
