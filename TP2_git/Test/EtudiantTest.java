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
    private Matiere anglais;
    private Identite identite;

    @BeforeEach
    public void setUp() {
        identite = new Identite("Dupont", "Jean", "123456789");
        formation = new Formation("info1");

        mathematiques = new Matiere("Mathématiques");
        physique = new Matiere("Physique");
        informatique = new Matiere("Informatique");
        anglais = new Matiere("Anglais");


        formation.ajout_matiere(mathematiques, 3.0);
        formation.ajout_matiere(physique, 2.0);
        formation.ajout_matiere(informatique, 4.0);
        formation.ajout_matiere(anglais, 1.5);

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
        assertThrows(IllegalArgumentException.class,
                () -> etudiant.ajouterNote(chimie, 15.0),
                "Devrait lancer une exception pour matière inexistante");
    }

    @Test
    public void testAjouterNote_NoteTropBasse() {

        assertThrows(IllegalArgumentException.class,
                () -> etudiant.ajouterNote(mathematiques, -5.0),
                "Devrait lancer une exception pour note négative");
    }

    @Test
    public void testAjouterNote_NoteTropHaute() {

        assertThrows(IllegalArgumentException.class,
                () -> etudiant.ajouterNote(mathematiques, 25.0),
                "Devrait lancer une exception pour note > 20");
    }

    @Test
    public void testCalculerMoyenneMatiere_AvecNotes() {
        etudiant.ajouterNote(physique, 12.0);
        etudiant.ajouterNote(physique, 16.0);
        etudiant.ajouterNote(physique, 14.0);

        double moyenne = etudiant.calculerMoyenneMatiere(physique);
        assertEquals(14.0, moyenne, 0.01);
    }

    @Test
    public void testCalculerMoyenneMatiere_SansNote() {

        assertThrows(IllegalArgumentException.class,
                () -> etudiant.calculerMoyenneMatiere(physique),
                "Devrait lancer une exception pour matière sans note");
    }

    @Test
    public void testCalculerMoyenneMatiere_MatiereInexistante() {

        Matiere chimie = new Matiere("Chimie");
        assertThrows(IllegalArgumentException.class,
                () -> etudiant.calculerMoyenneMatiere(chimie),
                "Devrait lancer une exception pour matière inexistante");
    }

    @Test
    public void testCalculerMoyenneGenerale_ToutesMatieresAvecNotes() {

        etudiant.ajouterNote(mathematiques, 15.0);
        etudiant.ajouterNote(mathematiques, 17.0); // moyenne: 16.0, coeff: 3.0

        etudiant.ajouterNote(physique, 12.0);
        etudiant.ajouterNote(physique, 14.0); // moyenne: 13.0, coeff: 2.0

        etudiant.ajouterNote(informatique, 18.0); // moyenne: 18.0, coeff: 4.0

        etudiant.ajouterNote(anglais, 14.0);
        etudiant.ajouterNote(anglais, 16.0); // moyenne: 15.0, coeff: 1.5

        double moyenneGenerale = etudiant.calculerMoyenneGenerale();
        // Calcul: (16*3 + 13*2 + 18*4 + 15*1.5) / (3+2+4+1.5) = 164.5 / 10.5 = 15.67
        assertEquals(15.67, moyenneGenerale, 0.01);
    }

    @Test
    public void testCalculerMoyenneGenerale_MatieresSansNotes() {

        etudiant.ajouterNote(mathematiques, 16.0);
        etudiant.ajouterNote(physique, 14.0);


        double moyenneGenerale = etudiant.calculerMoyenneGenerale();

        assertEquals(15.2, moyenneGenerale, 0.01);
    }

    @Test
    public void testCalculerMoyenneGenerale_AucuneNote() {

        assertThrows(IllegalArgumentException.class,
                () -> etudiant.calculerMoyenneGenerale(),
                "Devrait lancer une exception quand aucune note n'existe");
    }

    @Test
    public void testCalculerMoyenneGenerale_NotesDecimales() {
        etudiant.ajouterNote(mathematiques, 15.75);
        etudiant.ajouterNote(mathematiques, 16.25);

        etudiant.ajouterNote(physique, 13.5);

        double moyenneGenerale = etudiant.calculerMoyenneGenerale();

        assertEquals(15.0, moyenneGenerale, 0.01);
    }
}