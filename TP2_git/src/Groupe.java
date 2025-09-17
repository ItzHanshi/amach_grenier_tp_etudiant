import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Groupe {
    private Map<Formation, List<Etudiant>> groupes;

    public Groupe() {
        this.groupes = new HashMap<>();
    }


    public void ajouterEtudiant(Etudiant etudiant) {
        if (etudiant == null) {
            System.out.println("L'étudiant ne peut pas être null");
            return;
        }

        Formation formation = etudiant.getFormation();
        if (formation == null) {
            System.out.println("L'étudiant doit avoir une formation");
            return;
        }

        groupes.computeIfAbsent(formation, k -> new ArrayList<>());


        List<Etudiant> etudiants = groupes.get(formation);
        if (etudiants.contains(etudiant)) {
            System.out.println("L'étudiant " + etudiant.getIdentite().getPrenom() + " " +
                    etudiant.getIdentite().getNom() + " est déjà dans le groupe");
            return;
        }


        etudiants.add(etudiant);
        System.out.println("Étudiant " + etudiant.getIdentite().getPrenom() + " " +
                etudiant.getIdentite().getNom() + " ajouté au groupe");
    }


    public void supprimerEtudiant(Etudiant etudiant) {
        if (etudiant == null) {
            System.out.println("L'étudiant ne peut pas être null");
            return;
        }

        Formation formation = etudiant.getFormation();
        if (formation == null) {
            System.out.println("L'étudiant doit avoir une formation");
            return;
        }

        List<Etudiant> etudiants = groupes.get(formation);
        if (etudiants == null || etudiants.isEmpty()) {
            System.out.println("Aucun étudiant dans cette formation");
            return;
        }

        if (etudiants.remove(etudiant)) {
            System.out.println("Étudiant " + etudiant.getIdentite().getPrenom() + " " +
                    etudiant.getIdentite().getNom() + " supprimé du groupe");


            if (etudiants.isEmpty()) {
                groupes.remove(formation);
            }
        } else {
            System.out.println("L'étudiant " + etudiant.getIdentite().getPrenom() + " " +
                    etudiant.getIdentite().getNom() + " n'est pas dans le groupe");
        }
    }


    public Map<Formation, List<Etudiant>> getGroupes() {
        return groupes;
    }
}