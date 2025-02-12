package magasin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

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
    public void testConstructeurExiste() {
        try {
            Constructor<?> constructeur = getClasse().getConstructor(Class.forName("magasin.Client"), List.class);
            assertTrue(Modifier.isPublic(constructeur.getModifiers()), "Le constructeur doit être public");
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            fail("Le constructeur avec les paramètres (Client, List) est manquant");
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
} 