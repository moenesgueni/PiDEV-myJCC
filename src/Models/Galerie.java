package Models;

public class Galerie {

    //var
    private int ID_Galerie;
    private String couleurHtml;
    private String Nom;
    private String Description;
    private User Photographe;

    //Constructeurs
    public Galerie() {
    }

    public Galerie(String couleurHtml, String Nom, String Description, User Photographe) {
        this.couleurHtml = couleurHtml;
        this.Nom = Nom;
        this.Description = Description;
        this.Photographe = Photographe;
    }

    public Galerie(int ID_Galerie, String couleurHtml, String Nom, String Description, User Photographe) {
        this.ID_Galerie = ID_Galerie;
        this.couleurHtml = couleurHtml;
        this.Nom = Nom;
        this.Description = Description;
        this.Photographe = Photographe;
    }

    public int getID_Galerie() {
        return ID_Galerie;
    }

    public void setID_Galerie(int ID_Galerie) {
        this.ID_Galerie = ID_Galerie;
    }

    public String getCouleurHtml() {
        return couleurHtml;
    }

    public void setCouleurHtml(String couleurHtml) {
        this.couleurHtml = couleurHtml;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public User getPhotographe() {
        return Photographe;
    }

    public void setPhotographe(User Photographe) {
        this.Photographe = Photographe;
    }

    @Override
    public String toString() {
        return "Galerie{" + "ID_Galerie=" + ID_Galerie + ", couleurHtml=" + couleurHtml + ", Nom=" + Nom + ", Description=" + Description + ", Photographe=" + Photographe + '}';
    }

    
}