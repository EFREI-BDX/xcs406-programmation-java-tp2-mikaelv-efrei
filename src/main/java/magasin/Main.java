package magasin;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Magasin magasin = new Magasin();
    private static ArrayList<Commande> commandes = new ArrayList<>();
    private static Panier panier = new Panier();

    public static void main(String[] args) {
        initialiserMagasin();
        Client client = new Client(demanderNomClient(), demanderEmailClient());
        
        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            int choix = demanderChoix();
            continuer = traiterChoix(choix, client);
        }
        
        scanner.close();
    }

    private static void initialiserMagasin() {
        magasin.ajouterProduit(new Produit(1, "Livre", 10));
        magasin.ajouterProduit(new Produit(2, "Crayon", 2));
        magasin.ajouterProduit(new Produit(3, "Stylo", 3));
    }

    private static String demanderNomClient() {
        System.out.print("Entrez votre nom : ");
        return scanner.nextLine();
    }

    private static String demanderEmailClient() {
        System.out.print("Entrez votre email : ");
        return scanner.nextLine();
    }

    private static void afficherMenu() {
        System.out.println("\n--- Menu Magasin ---");
        System.out.println("1. Afficher les produits disponibles");
        System.out.println("2. Ajouter un produit au panier");
        System.out.println("3. Afficher le panier");
        System.out.println("4. Passer la commande");
        System.out.println("5. Afficher les commandes");
        System.out.println("6. Quitter");
    }

    private static int demanderChoix() {
        System.out.print("Votre choix : ");
        return scanner.nextInt();
    }

    private static boolean traiterChoix(int choix, Client client) {
        switch (choix) {
            case 1:
                magasin.afficherProduitsDisponibles();
                break;
            case 2:
                ajouterProduitAuPanier();
                break;
            case 3:
                panier.afficher();
                break;
            case 4:
                passerCommande(client);
                break;
            case 5:
                afficherCommandes();
                break;
            case 6:
                System.out.println("Au revoir !");
                return false;
            default:
                System.out.println("Choix invalide !");
        }
        return true;
    }

    private static void ajouterProduitAuPanier() {
        System.out.print("Entrez l'ID du produit : ");
        int id = scanner.nextInt();
        Produit produit = magasin.trouverProduitParId(id);
        if (produit != null) {
            panier.ajouterProduit(produit);
            System.out.println("Produit ajouté au panier !");
        } else {
            System.out.println("Produit non trouvé.");
        }
    }

    private static void passerCommande(Client client) {
        Commande commande = new Commande(client, panier, LocalDateTime.now());
        panier = new Panier();
        commandes.add(commande);
        System.out.println("Commande passée avec succès !");
    }

    private static void afficherCommandes() {
        for (Commande c : commandes) {
            c.afficherDetails();
        }
    }
}
