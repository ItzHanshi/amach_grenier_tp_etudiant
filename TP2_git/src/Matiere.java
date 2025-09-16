public class Matiere {
    private String nom;

    public Matiere(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matiere)) return false;
        Matiere matiere = (Matiere) o;
        return nom.equals(matiere.nom);
    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }
}
