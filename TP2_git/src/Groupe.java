import java.util.ArrayList;
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
}