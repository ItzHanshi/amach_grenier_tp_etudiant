import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Formation {


    private final String id;

    Map<Matiere, Double> matiereMap = new HashMap<>();

    public Formation(String id) {
        this.id = id;
        this.matiereMap = new HashMap<>();
    }


    /* ajouter ou supprimer une matiere dans une formation
    *
    */
    public void ajout_matiere (Matiere NOM,Double coef){
        if(matiereMap.containsKey(NOM)){
            System.out.println("La matiere existe deja");
        }else{
            matiereMap.put(NOM, coef);
        }

    }

    public void supprimer_matiere (Matiere matiere , double coef){
        if(matiereMap.containsKey(matiere)){
            matiereMap.remove(matiere, coef);
        }else{
            System.out.println("La matiere n'existe pas ");
        }
    }

    public void afficher_coefficient (Matiere matiere , double coef){
        if(matiereMap.containsKey(matiere)){
            System.out.println("Coefficient de " + matiere.getNom() + " : " + matiereMap.get(matiere));
        }else{
            System.out.println("La matiere n'existe pas ou il n'y a pas de coefficient assigné a la matiere ");
        }
    }




}
