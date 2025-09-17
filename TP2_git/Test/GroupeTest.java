import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GroupeTest {

    private Groupe groupe;
    private Formation formation;
    private Etudiant etudiant1;
    private Etudiant etudiant2;

    @BeforeEach
    void setUp() {
        groupe = new Groupe();

        formation = new Formation("INFO");
        formation.ajout_matiere(new Matiere("Java"), 2.0);

        Identite id1 = new Identite("Dupont", "Jean", "123");
        Identite id2 = new Identite("Martin", "Alice", "456");

        etudiant1 = new Etudiant(id1, formation);
        etudiant2 = new Etudiant(id2, formation);
    }




    @Test
    void testAjouterEtudiant() {
        groupe.ajouterEtudiant(etudiant1);
        Map<Formation, List<Etudiant>> groupes = groupe.getGroupes();

        assertTrue(groupes.containsKey(formation));
        assertTrue(groupes.get(formation).contains(etudiant1));
    }

    @Test
    void testAjouterEtudiantDejaPresent() {
        groupe.ajouterEtudiant(etudiant1);
        int tailleAvant = groupe.getGroupes().get(formation).size();

        groupe.ajouterEtudiant(etudiant1); // deuxième ajout

        int tailleApres = groupe.getGroupes().get(formation).size();
        assertEquals(tailleAvant, tailleApres, "L'étudiant ne doit pas être ajouté deux fois");
    }

    


    @Test
    void testSupprimerEtudiantExistant() {
        groupe.ajouterEtudiant(etudiant1);
        groupe.supprimerEtudiant(etudiant1);

        assertFalse(groupe.getGroupes().containsKey(formation),
                "La formation doit être supprimée si elle n'a plus d'étudiants");
    }


    @Test
    void testSupprimerEtudiantAbsent() {
        groupe.ajouterEtudiant(etudiant1);

        int tailleAvant = groupe.getGroupes().get(formation).size();
        groupe.supprimerEtudiant(etudiant2); // pas ajouté

        int tailleApres = groupe.getGroupes().get(formation).size();
        assertEquals(tailleAvant, tailleApres, "Aucune suppression ne doit avoir lieu");
    }




    @Test
    void testAjouterEtudiantNull() {
        groupe.ajouterEtudiant(null);
        assertTrue(groupe.getGroupes().isEmpty(), "Aucun étudiant ne doit être ajouté si null");
    }

    @Test
    void testSupprimerEtudiantNull() {
        groupe.supprimerEtudiant(null);
        assertTrue(groupe.getGroupes().isEmpty(), "Suppression null ne doit rien changer");
    }
}
