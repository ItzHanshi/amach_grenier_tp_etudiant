import java.util.HashMap;
import java.util.Map;

public class Formation {
    private final String id;
    Map<Matiere, Double> matiereMap;

    public Formation(String id) {
        this.id = id;
        this.matiereMap = new HashMap<>();
    }

    public void ajout_matiere(Matiere matiere, Double coef) throws IllegalArgumentException {
        if (matiere == null) {
            throw new IllegalArgumentException("La matière ne peut pas être null");
        }
        if (coef < 0) {
            throw new IllegalArgumentException("Le coefficient ne peut pas être négatif");
        }
        if (matiereMap.containsKey(matiere)) {
            throw new IllegalArgumentException("La matière existe déjà");
        }
        matiereMap.put(matiere, coef);
    }

    public void supprimer_matiere(Matiere matiere) throws IllegalArgumentException {
        if (!matiereMap.containsKey(matiere)) {
            throw new IllegalArgumentException("La matière n'existe pas");
        }
        matiereMap.remove(matiere);
    }

    public double getCoefficient(Matiere matiere) throws IllegalArgumentException {
        if (!matiereMap.containsKey(matiere)) {
            throw new IllegalArgumentException("La matière n'existe pas dans la formation");
        }
        return matiereMap.get(matiere);
    }

    public boolean isEmpty() {
        return matiereMap.isEmpty();
    }

    public String getId() {
        return id;
    }
}