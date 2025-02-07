package magasin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.*;
import java.util.Arrays;

public class ProduitTest {
    
    private Class<?> getClasse() {
        try {
            return Class.forName("magasin.Produit");
        } catch (ClassNotFoundException e) {
            fail("La classe Produit n'existe pas");
            return null;
        }
    }

    @Test
    public void testAttributsExistent() {
        Class<?> classe = getClasse();
        Field[] fields = classe.getDeclaredFields();
        
        // Vérifie que tous les attributs requis existent
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("id")), "L'attribut 'id' est manquant");
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("nom")), "L'attribut 'nom' est manquant");
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("prix")), "L'attribut 'prix' est manquant");
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("quantite")), "L'attribut 'quantite' est manquant");
        
        // Vérifie les types des attributs
        try {
            assertEquals(int.class, classe.getDeclaredField("id").getType(), "L'attribut 'id' doit être de type int");
            assertEquals(String.class, classe.getDeclaredField("nom").getType(), "L'attribut 'nom' doit être de type String");
            assertEquals(double.class, classe.getDeclaredField("prix").getType(), "L'attribut 'prix' doit être de type double");
            assertEquals(int.class, classe.getDeclaredField("quantite").getType(), "L'attribut 'quantite' doit être de type int");
        } catch (NoSuchFieldException e) {
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
            Constructor<?> constructeur = getClasse().getConstructor(int.class, String.class, double.class, int.class);
            assertTrue(Modifier.isPublic(constructeur.getModifiers()), "Le constructeur doit être public");
        } catch (NoSuchMethodException e) {
            fail("Le constructeur avec les paramètres (int, String, double, int) est manquant");
        }
    }

    @Test
    public void testGettersExistent() {
        Class<?> classe = getClasse();
        Method[] methods = classe.getDeclaredMethods();
        
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getId")), "La méthode getId() est manquante");
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getNom")), "La méthode getNom() est manquante");
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getPrix")), "La méthode getPrix() est manquante");
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getQuantite")), "La méthode getQuantite() est manquante");
        
        try {
            assertEquals(int.class, classe.getMethod("getId").getReturnType(), "getId() doit retourner un int");
            assertEquals(String.class, classe.getMethod("getNom").getReturnType(), "getNom() doit retourner un String");
            assertEquals(double.class, classe.getMethod("getPrix").getReturnType(), "getPrix() doit retourner un double");
            assertEquals(int.class, classe.getMethod("getQuantite").getReturnType(), "getQuantite() doit retourner un int");
        } catch (NoSuchMethodException e) {
            fail("Un getter requis est manquant");
        }
    }

    @Test
    public void testSettersExistent() {
        Class<?> classe = getClasse();
        Method[] methods = classe.getDeclaredMethods();
        
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("setPrix")), "La méthode setPrix() est manquante");
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("setQuantite")), "La méthode setQuantite() est manquante");
        
        try {
            assertEquals(void.class, classe.getMethod("setPrix", double.class).getReturnType(), 
                "setPrix() doit être de type void");
            assertEquals(void.class, classe.getMethod("setQuantite", int.class).getReturnType(), 
                "setQuantite() doit être de type void");
        } catch (NoSuchMethodException e) {
            fail("Un setter requis est manquant");
        }
    }

    @Test
    public void testMethodeAfficherDetailsExiste() {
        try {
            Method method = getClasse().getMethod("afficherDetails");
            assertEquals(void.class, method.getReturnType(), "afficherDetails() doit être de type void");
            assertTrue(Modifier.isPublic(method.getModifiers()), "afficherDetails() doit être public");
        } catch (NoSuchMethodException e) {
            fail("La méthode afficherDetails() est manquante");
        }
    }
} 