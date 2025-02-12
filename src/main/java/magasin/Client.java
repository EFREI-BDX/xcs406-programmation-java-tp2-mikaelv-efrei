package magasin;

public class Client {
    private String nom;
    private String email;

    public Client(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }


    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    protected void setNom(String nom) {
        this.nom = nom;
    }

    protected void setEmail(String email) { 
        this.email = email;
    }

    public void afficherDetails() {
        System.out.println("Nom: " + nom + ", Email: " + email);
    }
    
}
