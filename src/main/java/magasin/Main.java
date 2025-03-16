package magasin;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Commande> commandes = new ArrayList<>();
        Panier panier = new Panier();
        Magasin magasin = initialiserMagasin();
        Client client = new Client(demanderNomClient(scanner), demanderEmailClient(scanner));

        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            int choix = demanderChoix(scanner);
            boolean result = true;
            switch (choix) {
                case 1:
                    magasin.afficherProduitsDisponibles();
                    break;
                case 2:
                    ajouterProduitAuPanier(magasin, panier, scanner);
                    break;
                case 3:
                    panier.afficher();
                    break;
                case 4:
                    Commande commande = new Commande(client, panier, LocalDateTime.now());
                    panier = new Panier();
                    commandes.add(commande);
                    System.out.println("Commande passée avec succès !");
                    break;
                case 5:
                    afficherCommandes(commandes);
                    break;
                case 6:
                    System.out.println("Au revoir !");
                    result = false;
                    break;
                default:
                    System.out.println("Choix invalide !");
            }
            continuer = result;
        }

        scanner.close();
    }

    private static Magasin initialiserMagasin() {
        Magasin magasin = new Magasin();
        magasin.ajouterProduit(new Produit(1, "Livre", 10));
        magasin.ajouterProduit(new Produit(2, "Crayon", 2));
        magasin.ajouterProduit(new Produit(3, "Stylo", 3));
        return magasin;
    }

    private static String demanderNomClient(Scanner scanner) {
        System.out.print("Entrez votre nom : ");
        return scanner.nextLine();
    }

    private static String demanderEmailClient(Scanner scanner) {
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

    private static int demanderChoix(Scanner scanner) {
        System.out.print("Votre choix : ");
        return scanner.nextInt();
    }

    private static void ajouterProduitAuPanier(Magasin magasin, Panier panier, Scanner scanner) {
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

    private static void afficherCommandes(ArrayList<Commande> commandes) {
        for (Commande c : commandes) {
            c.afficherDetails();
        }
    }
}
