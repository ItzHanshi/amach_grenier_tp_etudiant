import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GroupeTest {
    private Groupe groupe;
    private Formation formation;
    private Matiere math, francais, anglais, physique;
    private Etudiant alice, bob, claire;

    @BeforeEach
    void setUp() throws Exception {

        math = new Matiere("Mathématiques");
        francais = new Matiere("Français");
        anglais = new Matiere("Anglais");
        physique = new Matiere("Physique");


        formation = new Formation("L1-INFO");
        formation.ajout_matiere(math, 3.0);
        formation.ajout_matiere(francais, 2.0);
        formation.ajout_matiere(anglais, 1.0);
        formation.ajout_matiere(physique, 2.5);


        groupe = new Groupe(formation);


        Identite id1 = new Identite("Dupont", "Alice", "123456");
        alice = new Etudiant(id1, formation);
        alice.ajouterNote(math, 15.0);
        alice.ajouterNote(math, 17.0);
        alice.ajouterNote(francais, 14.0);
        alice.ajouterNote(francais, 16.0);
        alice.ajouterNote(anglais, 18.0);
        alice.ajouterNote(physique, 13.0);

        Identite id2 = new Identite("Martin", "Bob", "234567");
        bob = new Etudiant(id2, formation);
        bob.ajouterNote(math, 12.0);
        bob.ajouterNote(math, 14.0);
        bob.ajouterNote(francais, 13.0);
        bob.ajouterNote(anglais, 16.0);


        Identite id3 = new Identite("Durand", "Claire", "345678");
        claire = new Etudiant(id3, formation);
        claire.ajouterNote(math, 18.0);
        claire.ajouterNote(francais, 17.0);
        claire.ajouterNote(anglais, 19.0);
        claire.ajouterNote(physique, 16.0);

        groupe.ajouterEtudiant(alice);
        groupe.ajouterEtudiant(bob);
        groupe.ajouterEtudiant(claire);
    }

    @Test
    void testFormationInstanciee() {
        assertFalse(formation.isEmpty());
        assertEquals(4, formation.matiereMap.size());
    }

    @Test
    void testAjoutMatiereCoeffNegatif() {
        Matiere histoire = new Matiere("Histoire");
        assertThrows(IllegalArgumentException.class,
                () -> formation.ajout_matiere(histoire, -1.0));
    }

    @Test
    void testCoeffMatiereInexistante() {
        Matiere histoire = new Matiere("Histoire");
        assertThrows(IllegalArgumentException.class,
                () -> formation.getCoefficient(histoire));
    }

    @Test
    void testAjoutNoteMatiereSansNote() {
        assertThrows(IllegalArgumentException.class,
                () -> bob.calculerMoyenneMatiere(physique));
    }

    @Test
    void testAjoutNoteMatiereInexistante() {
        Matiere histoire = new Matiere("Histoire");
        assertThrows(IllegalArgumentException.class,
                () -> alice.ajouterNote(histoire, 15.0));
    }

    @Test
    void testAjoutNoteNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> alice.ajouterNote(math, -5.0));
    }

    @Test
    void testAjoutNoteSuperieure20() {
        assertThrows(IllegalArgumentException.class,
                () -> alice.ajouterNote(math, 25.0));
    }

    @Test
    void testAjoutEtudiantFormationDifferente() throws Exception {
        Formation autreFormation = new Formation("L2-MATH");
        autreFormation.ajout_matiere(math, 2.0);
        Identite id = new Identite("Test", "Test", "999999");
        Etudiant etudiantAutreFormation = new Etudiant(id, autreFormation);

        assertThrows(IllegalArgumentException.class,
                () -> groupe.ajouterEtudiant(etudiantAutreFormation));
    }

    @Test
    void testAjoutEtudiantNull() {
        assertThrows(IllegalArgumentException.class,
                () -> groupe.ajouterEtudiant(null));
    }
    @Test
    void testTriAlpha() {
        groupe.triAlpha();
        List<Etudiant> tri = groupe.getEtudiants();
        assertEquals("Dupont", tri.get(0).getIdentite().getNom());
        assertEquals("Durand", tri.get(1).getIdentite().getNom());
        assertEquals("Martin", tri.get(2).getIdentite().getNom());
    }

    @Test
    void testTriAntiAlpha() {
        groupe.triAntiAlpha();
        List<Etudiant> tri = groupe.getEtudiants();
        assertEquals("Martin", tri.get(0).getIdentite().getNom());
        assertEquals("Durand", tri.get(1).getIdentite().getNom());
        assertEquals("Dupont", tri.get(2).getIdentite().getNom());
    }


    @Test
    void testSupprimerEtudiant() {
        groupe.supprimerEtudiant(bob);
        assertEquals(2, groupe.getEtudiants().size());
        assertFalse(groupe.getEtudiants().contains(bob));
    }

    @Test
    void testMoyenneMatiereGroupe() {

        double moyenneMath = groupe.calculerMoyenneMatiereGroupe(math);
        assertEquals(15.67, moyenneMath, 0.01);

        double moyennePhysique = groupe.calculerMoyenneMatiereGroupe(physique);
        assertEquals(14.5, moyennePhysique, 0.01);
    }

    @Test
    void testMoyenneGeneraleGroupe() throws Exception {
        double moyenneGenerale = groupe.calculerMoyenneGeneraleGroupe();
        assertTrue(moyenneGenerale > 0);
        assertEquals(15.30, moyenneGenerale, 0.1);
    }

    @Test
    void testTriParMerite() {
        groupe.triParMerite();
        List<Etudiant> tri = groupe.getEtudiants();

        // Vérification de l'ordre décroissant par moyenne
        double m1 = tri.get(0).calculerMoyenneGenerale();
        double m2 = tri.get(1).calculerMoyenneGenerale();
        double m3 = tri.get(2).calculerMoyenneGenerale();

        assertTrue(m1 >= m2 && m2 >= m3, "La liste doit être triée par moyenne générale décroissante");

        // Vérification que Claire est première
        assertEquals("Durand", tri.get(0).getIdentite().getNom());

        // Vérification que Bob est dernier
        assertEquals("Martin", tri.get(2).getIdentite().getNom());
    }




}