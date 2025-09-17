import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class FormationTest {

    private Formation formation;
    private Matiere math;
    private Matiere info;

    @BeforeEach
    void setUp() {
        formation = new Formation("info1");
        math = new Matiere("Mathématiques");
        info = new Matiere("Informatique");
    }



    @Test
    void testAjoutMatiere() {
        formation.ajout_matiere(math, 2.0);
        formation.ajout_matiere(info, 3.0);

        // Vérifier que les matières ont été ajoutées
        assertEquals(2, formation.matiereMap.get(math));
        assertEquals(3, formation.matiereMap.get(info));
    }

    @Test
    void testAjoutMatiereExistante() {
        formation.ajout_matiere(math, 2.0);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        formation.ajout_matiere(math, 4.0);

        assertTrue(outContent.toString().contains("La matiere existe deja"));
    }


    @Test
    void testSupprimerMatiere() {
        formation.ajout_matiere(math, 2.0);
        formation.supprimer_matiere(math, 2.0);

        assertFalse(formation.matiereMap.containsKey(math));
    }

    @Test
    void testSupprimerMatiereInexistante() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        formation.supprimer_matiere(info, 3.0);

        assertTrue(outContent.toString().contains("La matiere n'existe pas"));
    }

    @Test
    void testAfficherCoefficient() {
        formation.ajout_matiere(math, 2.0);


        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        formation.afficher_coefficient(math, 2.0);

        assertTrue(outContent.toString().contains("Coefficient de Mathématiques : 2.0"));
    }

    @Test
    void testAfficherCoefficientMatiereInexistante() {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        formation.afficher_coefficient(info, 3.0);

        assertTrue(outContent.toString().contains("La matiere n'existe pas"));
    }
}
