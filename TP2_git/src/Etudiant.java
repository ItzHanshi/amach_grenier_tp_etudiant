import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;


public class Etudiant {
    private Identite identite;
    private Formation formation;
    private Map<Matiere, List<Double>> resultats;

    public Etudiant(Identite identite, Formation formation) {
        this.identite = identite;
        this.formation = formation;
        this.resultats = new HashMap<>();
    }

    public Identite getIdentite() {
        return identite;
    }

    public Formation getFormation() {
        return formation;
    }
    public Map<Matiere, List<Double>> getRésultats() {
        return resultats;
    }

    public void ajouterNote(Matiere matiere, Double note) {
        if (!formation.matiereMap.containsKey(matiere)) {
            System.out.println("la matière n'est pas dans la formation de l'étudiant.");
            return;
        }
        if (note < 0 || note > 20) {
            System.out.println("la note doit être entre 0 et 20.");
            return;
        }
        resultats.computeIfAbsent(matiere, k -> new ArrayList<>()).add(note);
    }


    public void calculerMoyenneMatiere(Matiere matiere) {
        if (!formation.matiereMap.containsKey(matiere)) {
            System.out.println("la matière n'est pas dans la formation de l'étudiant");
            return;
        }
        List<Double> notes = resultats.get(matiere);
        if (notes == null || notes.isEmpty()) {
            System.out.println("pas de note pour cette matière");
            return;
        }
        double somme = 0;
        for (double n : notes) {
            somme += n;
        }
        double moyenne = somme / notes.size();
        System.out.println("moyenne de : " + matiere.getNom() + " est : " + moyenne);
    }

    public void calculerMoyenneGenerale() {
        double total = 0;
        double totalCoeff = 0;
        for (Matiere matiere : formation.matiereMap.keySet()) {
            List<Double> notes = resultats.get(matiere);
            if (notes != null && !notes.isEmpty()) {
                double somme = 0;
                for (double n : notes) {
                    somme += n;
                }
                double moyenneMatiere = somme / notes.size();
                double coeff = formation.matiereMap.get(matiere);
                total += moyenneMatiere * coeff;
                totalCoeff += coeff;
            }
        }
        if (totalCoeff == 0) {
            System.out.println("aucune note pour calculer la moyenne générale.");
        } else {
            double moyenneGenerale = total / totalCoeff;
            System.out.println("moyenne générale de l'étudiant : " + moyenneGenerale);
        }
    }


}
