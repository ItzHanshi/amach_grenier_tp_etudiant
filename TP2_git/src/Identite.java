public class Identite {

    private String nom;
    private String prenom;
    private String nip;

    public Identite(String nom, String prenom, String nip) {
        this.nom = nom;
        this.prenom = prenom;
        this.nip = nip;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNip() {
        return nip;
    }
}
