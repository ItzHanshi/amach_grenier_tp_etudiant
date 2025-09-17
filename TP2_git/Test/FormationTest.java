import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FormationTest {
    private Formation formation;
    private Matiere math;
    private Matiere info;
    private Matiere physique;

    @BeforeEach
    void setUp() {
        formation = new Formation("info1");
        math = new Matiere("Mathématiques");
        info = new Matiere("Informatique");
        physique = new Matiere("Physique");
    }

    @Test
    void testFormationInstanciee() {

        assertTrue(formation.isEmpty(), "La formation doit être vide au début");
        assertEquals(0, formation.matiereMap.size());
    }

    @Test
    void testAjoutMatiere() {
        formation.ajout_matiere(math, 2.0);
        formation.ajout_matiere(info, 3.0);


        assertEquals(2.0, formation.matiereMap.get(math));
        assertEquals(3.0, formation.matiereMap.get(info));
        assertFalse(formation.isEmpty());
    }

    @Test
    void testAjoutMatiereCoeffNegatif() {

        assertThrows(IllegalArgumentException.class,
                () -> formation.ajout_matiere(math, -1.0),
                "Devrait lancer une exception pour coefficient négatif");
    }

    @Test
    void testAjoutMatiereExistante() {
        formation.ajout_matiere(math, 2.0);


        assertThrows(IllegalArgumentException.class,
                () -> formation.ajout_matiere(math, 4.0),
                "Devrait lancer une exception pour matière existante");
    }

    @Test
    void testGetCoefficientMatiereInexistante() {

        assertThrows(IllegalArgumentException.class,
                () -> formation.getCoefficient(info),
                "Devrait lancer une exception pour matière inexistante");
    }

    @Test
    void testSupprimerMatiere() {
        formation.ajout_matiere(math, 2.0);
        formation.supprimer_matiere(math);

        assertFalse(formation.matiereMap.containsKey(math));
        assertTrue(formation.isEmpty());
    }


    @Test
    void testAjoutMatiereNull() {
        assertThrows(IllegalArgumentException.class,
                () -> formation.ajout_matiere(null, 2.0),
                "Devrait lancer une exception pour matière null");
    }
}