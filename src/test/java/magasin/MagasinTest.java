package magasin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

public class MagasinTest {
    
    private Class<?> getClasse() {
        try {
            return Class.forName("magasin.Magasin");
        } catch (ClassNotFoundException e) {
            fail("La classe Magasin n'existe pas");
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
            assertEquals(List.class, classe.getDeclaredField("produits").getType(), "L'attribut 'produits' doit être de type List");
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
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("afficherProduitsDisponibles")), "La méthode afficherProduitsDisponibles() est manquante");
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("trouverProduitParNom")), "La méthode trouverProduitParNom() est manquante");
    }
} 