import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class EtudiantTest {

    private Etudiant etudiant;
    private Formation formation;
    private Matiere mathematiques;
    private Matiere physique;
    private Matiere informatique;
    private Identite identite;

    @BeforeEach
    public void setUp() {

        identite = new Identite("Dupont", "Jean", "123456789");
        formation = new Formation("info1");

        mathematiques = new Matiere("Mathématiques");
        physique = new Matiere("Physique");
        informatique = new Matiere("Informatique");


        formation.ajout_matiere(mathematiques, 3.0);
        formation.ajout_matiere(physique, 2.0);
        formation.ajout_matiere(informatique, 4.0);


        etudiant = new Etudiant(identite, formation);
    }


    @Test
    public void testAjouterNote_NoteValide() {

        etudiant.ajouterNote(mathematiques, 15.5);

        List<Double> notes = etudiant.getRésultats().get(mathematiques);
        assertNotNull(notes);
        assertEquals(1, notes.size());
        assertEquals(15.5, notes.get(0));
    }

    @Test
    public void testAjouterNote_PlusieursNotes() {

        etudiant.ajouterNote(mathematiques, 12.0);
        etudiant.ajouterNote(mathematiques, 16.0);
        etudiant.ajouterNote(mathematiques, 14.5);

        List<Double> notes = etudiant.getRésultats().get(mathematiques);
        assertEquals(3, notes.size());
        assertTrue(notes.contains(12.0));
        assertTrue(notes.contains(16.0));
        assertTrue(notes.contains(14.5));
    }

    @Test
    public void testAjouterNote_MatiereInexistante() {

        Matiere chimie = new Matiere("Chimie");
        etudiant.ajouterNote(chimie, 15.0);


        assertFalse(etudiant.getRésultats().containsKey(chimie));
    }

    @Test
    public void testAjouterNote_NoteTropBasse() {

        etudiant.ajouterNote(mathematiques, -5.0);


        List<Double> notes = etudiant.getRésultats().get(mathematiques);
        assertTrue(notes == null || notes.isEmpty());
    }


    @Test
    public void testCalculerMoyenneMatiere_PlusieursNotes() {

        etudiant.ajouterNote(physique, 12.0);
        etudiant.ajouterNote(physique, 16.0);
        etudiant.ajouterNote(physique, 14.0);
        etudiant.calculerMoyenneMatiere(physique);

    }

    @Test
    public void testCalculerMoyenneMatiere_MatiereInexistante() {

        Matiere chimie = new Matiere("Chimie");
        etudiant.calculerMoyenneMatiere(chimie);

    }


    @Test
    public void testCalculerMoyenneGenerale_ToutesMatieresAvecNotes() {
        // Test avec des notes dans toutes les matières
        etudiant.ajouterNote(mathematiques, 15.0);
        etudiant.ajouterNote(mathematiques, 17.0); // moyenne: 16.0, coeff: 3.0

        etudiant.ajouterNote(physique, 12.0);
        etudiant.ajouterNote(physique, 14.0); // moyenne: 13.0, coeff: 2.0

        etudiant.ajouterNote(informatique, 18.0); // moyenne: 18.0, coeff: 4.0

        etudiant.calculerMoyenneGenerale();

    }



    @Test
    public void testCalculerMoyenneGenerale_NotesDecimales() {

        etudiant.ajouterNote(mathematiques, 15.75);
        etudiant.ajouterNote(mathematiques, 16.25); // moyenne: 16.0, coeff: 3.0

        etudiant.ajouterNote(physique, 13.5); // moyenne: 13.5, coeff: 2.0

        etudiant.calculerMoyenneGenerale();

    }
}