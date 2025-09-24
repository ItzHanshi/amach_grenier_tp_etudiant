import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Groupe {
    private Formation formation;
    private List<Etudiant> etudiants;

    public Groupe(Formation formation) {
        if (formation == null) {
            throw new IllegalArgumentException("La formation ne peut pas être null");
        }
        this.formation = formation;
        this.etudiants = new ArrayList<>();
    }

    public void ajouterEtudiant(Etudiant etudiant) throws IllegalArgumentException {
        if (etudiant == null) {
            throw new IllegalArgumentException("L'étudiant ne peut pas être null");
        }

        if (!etudiant.getFormation().getId().equals(formation.getId())) {
            throw new IllegalArgumentException("L'étudiant doit avoir la même formation que le groupe");
        }

        if (etudiants.contains(etudiant)) {
            throw new IllegalArgumentException("L'étudiant est déjà dans le groupe");
        }

        etudiants.add(etudiant);
    }

    public void supprimerEtudiant(Etudiant etudiant) throws IllegalArgumentException {
        if (etudiant == null) {
            throw new IllegalArgumentException("L'étudiant ne peut pas être null");
        }

        if (!etudiants.remove(etudiant)) {
            throw new IllegalArgumentException("L'étudiant n'est pas dans le groupe");
        }

    }

    //tri normale
    public void triAlpha() {
        etudiants.sort(Comparator.comparing(e -> e.getIdentite().getNom()));
    }

    // Tri inverse
    public void triAntiAlpha() {
        etudiants.sort(Comparator.comparing((Etudiant e) -> e.getIdentite().getNom()).reversed());
    }

    public double calculerMoyenneMatiereGroupe(Matiere matiere) throws IllegalArgumentException {
        if (matiere == null) {
            throw new IllegalArgumentException("La matière ne peut pas être null");
        }

        if (!formation.matiereMap.containsKey(matiere)) {
            throw new IllegalArgumentException("La matière n'existe pas dans cette formation");
        }

        if (etudiants.isEmpty()) {
            throw new IllegalArgumentException("Aucun étudiant dans le groupe");
        }

        double sommeMoyennes = 0;
        int nbEtudiantsAvecNotes = 0;

        Iterator<Etudiant> iterator = etudiants.iterator();
        while (iterator.hasNext()) {
            Etudiant etudiant = iterator.next();
            try {
                double moyenneEtudiant = etudiant.calculerMoyenneMatiere(matiere);
                sommeMoyennes += moyenneEtudiant;
                nbEtudiantsAvecNotes++;
            } catch (IllegalArgumentException e) {
                // Étudiant sans notes pour cette matière, on l'ignore
            }
        }

        if (nbEtudiantsAvecNotes == 0) {
            throw new IllegalArgumentException("Aucun étudiant n'a de notes pour cette matière");
        }

        return sommeMoyennes / nbEtudiantsAvecNotes;
    }

    public double calculerMoyenneGeneraleGroupe() throws IllegalArgumentException {
        if (etudiants.isEmpty()) {
            throw new IllegalArgumentException("Aucun étudiant dans le groupe");
        }

        double sommeMoyennesGenerales = 0;
        int nbEtudiantsAvecNotes = 0;

        Iterator<Etudiant> iterator = etudiants.iterator();
        while (iterator.hasNext()) {
            Etudiant etudiant = iterator.next();
            try {
                double moyenneGeneraleEtudiant = etudiant.calculerMoyenneGenerale();
                sommeMoyennesGenerales += moyenneGeneraleEtudiant;
                nbEtudiantsAvecNotes++;
            } catch (IllegalArgumentException e) {
                // Étudiant sans notes, on l'ignore
            }
        }

        if (nbEtudiantsAvecNotes == 0) {
            throw new IllegalArgumentException("Aucun étudiant n'a de notes");
        }

        return sommeMoyennesGenerales / nbEtudiantsAvecNotes;
    }

    public Formation getFormation() {
        return formation;
    }

    public List<Etudiant> getEtudiants() {
        return new ArrayList<>(etudiants);
    }


}