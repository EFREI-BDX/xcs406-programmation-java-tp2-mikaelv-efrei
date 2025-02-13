package magasin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class CommandeTest {
    
    private Class<?> getClasse() {
        try {
            return Class.forName("magasin.Commande");
        } catch (ClassNotFoundException e) {
            fail("La classe Commande n'existe pas");
            return null;
        }
    }

    @Test
    public void testAttributsExistent() {
        Class<?> classe = getClasse();
        Field[] fields = classe.getDeclaredFields();
        
        // Vérifie que tous les attributs requis existent
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("idCommande")), "L'attribut 'idCommande' est manquant");
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("client")), "L'attribut 'client' est manquant");
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("produitsCommandes")), "L'attribut 'produitsCommandes' est manquant");
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("total")), "L'attribut 'total' est manquant");
        
        // Vérifie les types des attributs
        try {
            assertEquals(int.class, classe.getDeclaredField("idCommande").getType(), "L'attribut 'idCommande' doit être de type int");
            assertEquals(Class.forName("magasin.Client"), classe.getDeclaredField("client").getType(), "L'attribut 'client' doit être de type Client");
            assertEquals(List.class, classe.getDeclaredField("produitsCommandes").getType(), "L'attribut 'produitsCommandes' doit être de type List");
            assertEquals(double.class, classe.getDeclaredField("total").getType(), "L'attribut 'total' doit être de type double");
        } catch (NoSuchFieldException | ClassNotFoundException e) {
            fail("Un attribut requis est manquant");
        }
    }

    @Test
    public void testAttributsPrives() {
        Field[] fields = getClasse().getDeclaredFields();
        for (Field field : fields) {
            assertTrue(Modifier.isPrivate(field.getModifiers()), 
                "L'attribut '" + field.getName() + "' doit être privé");
        }
    }

    @Test
    public void testConstructeurExiste() {
        try {
            Constructor<?> constructeur = getClasse().getConstructor(Class.forName("magasin.Client"), List.class);
            assertTrue(Modifier.isPublic(constructeur.getModifiers()), "Le constructeur doit être public");
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            fail("Le constructeur avec les paramètres (Client, List) est manquant");
        }
    }

    @Test
    public void testGettersExistent() {
        Class<?> classe = getClasse();
        Method[] methods = classe.getDeclaredMethods();
        
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getIdCommande")), "La méthode getIdCommande() est manquante");
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getClient")), "La méthode getClient() est manquante");
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getProduitsCommandes")), "La méthode getProduitsCommandes() est manquante");
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getTotal")), "La méthode getTotal() est manquante");
        
        try {
            assertEquals(int.class, classe.getMethod("getIdCommande").getReturnType(), "getIdCommande() doit retourner un int");
            assertEquals(Class.forName("magasin.Client"), classe.getMethod("getClient").getReturnType(), "getClient() doit retourner un Client");
            assertEquals(List.class, classe.getMethod("getProduitsCommandes").getReturnType(), "getProduitsCommandes() doit retourner une List");
            assertEquals(double.class, classe.getMethod("getTotal").getReturnType(), "getTotal() doit retourner un double");
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            fail("Un getter requis est manquant");
        }
    }

    @Test
    public void testMethodeAfficherDetailsCommandeExiste() {
        try {
            Method method = getClasse().getMethod("afficherDetailsCommande");
            assertEquals(void.class, method.getReturnType(), "afficherDetailsCommande() doit être de type void");
            assertTrue(Modifier.isPublic(method.getModifiers()), "afficherDetailsCommande() doit être public");
        } catch (NoSuchMethodException e) {
            fail("La méthode afficherDetailsCommande() est manquante");
        }
    }

    @Test
    public void testAfficherDetailsCommandeContenu() {
        try {
            Class<?> classeCommande = getClasse();
            Class<?> classeClient = Class.forName("magasin.Client");
            
            // Create a test client
            Constructor<?> constructeurClient = classeClient.getConstructor(String.class, String.class);
            Object client = constructeurClient.newInstance("TestClient", "test@email.com");
            
            // Create an empty list for products
            List<Object> produits = new ArrayList<>();
            
            // Create the command
            Constructor<?> constructeurCommande = classeCommande.getConstructor(classeClient, List.class);
            Object commande = constructeurCommande.newInstance(client, produits);
            
            // Redirect System.out to capture printed output
            java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
            System.setOut(new java.io.PrintStream(out));

            // Call afficherDetailsCommande
            Method afficherDetails = classeCommande.getMethod("afficherDetailsCommande");
            afficherDetails.invoke(commande);

            // Get the printed output
            String output = out.toString().trim();

            // Verify that essential information is present
            assertTrue(output.contains("TestClient"), "L'affichage doit contenir le nom du client");
            assertTrue(output.contains("test@email.com"), "L'affichage doit contenir l'email du client");

            // Restore normal System.out
            System.setOut(System.out);
        } catch (Exception e) {
            fail("Exception lors du test d'affichage: " + e.getMessage());
        }
    }
} 