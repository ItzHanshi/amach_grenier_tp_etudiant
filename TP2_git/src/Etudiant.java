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

    public void ajouterNote(Matiere matiere, Double note) throws IllegalArgumentException {
        if (!formation.matiereMap.containsKey(matiere)) {
            throw new IllegalArgumentException("La matière n'est pas dans la formation de l'étudiant");
        }
        if (note < 0 || note > 20) {
            throw new IllegalArgumentException("La note doit être entre 0 et 20");
        }
        resultats.computeIfAbsent(matiere, k -> new ArrayList<>()).add(note);
    }

    public double calculerMoyenneMatiere(Matiere matiere) throws IllegalArgumentException {
        if (!formation.matiereMap.containsKey(matiere)) {
            throw new IllegalArgumentException("La matière n'est pas dans la formation de l'étudiant");
        }
        List<Double> notes = resultats.get(matiere);
        if (notes == null || notes.isEmpty()) {
            throw new IllegalArgumentException("Pas de note pour cette matière");
        }
        double somme = 0;
        for (double n : notes) {
            somme += n;
        }
        return somme / notes.size();
    }

    public double calculerMoyenneGenerale() throws IllegalArgumentException {
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
            throw new IllegalArgumentException("Aucune note pour calculer la moyenne générale");
        }

        return total / totalCoeff;
    }
}