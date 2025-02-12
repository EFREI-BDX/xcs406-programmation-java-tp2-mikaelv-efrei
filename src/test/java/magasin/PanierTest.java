package magasin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.ArrayList;

public class PanierTest {
    
    private Class<?> getClasse() {
        try {
            return Class.forName("magasin.Panier");
        } catch (ClassNotFoundException e) {
            fail("La classe Panier n'existe pas");
            return null;
        }
    }

    @Test
    public void testAttributsExistent() {
        Class<?> classe = getClasse();
        Field[] fields = classe.getDeclaredFields();
        
        // Vérifie que tous les attributs requis existent
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("produits")), "L'attribut 'produits' est manquant");
        
        // Vérifie les types des attributs
        try {
            assertEquals(ArrayList.class, classe.getDeclaredField("produits").getType(), "L'attribut 'produits' doit être de type ArrayList");
        } catch (NoSuchFieldException e) {
            fail("Un attribut requis est manquant");
        }
    }

    @Test
    public void testConstructeurExiste() {
        try {
            Constructor<?> constructeur = getClasse().getConstructor();
            assertTrue(Modifier.isPublic(constructeur.getModifiers()), "Le constructeur doit être public");
        } catch (NoSuchMethodException e) {
            fail("Le constructeur sans paramètres est manquant");
        }
    }

    @Test
    public void testMethodesExistent() {
        Class<?> classe = getClasse();
        Method[] methods = classe.getDeclaredMethods();
        
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("ajouterProduit")), "La méthode ajouterProduit() est manquante");
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("supprimerProduit")), "La méthode supprimerProduit() est manquante");
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("afficherPanier")), "La méthode afficherPanier() est manquante");
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("calculerTotal")), "La méthode calculerTotal() est manquante");
    }
} 