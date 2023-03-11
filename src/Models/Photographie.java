package Models;

public class Photographie {

    //var
    private int ID_Photographie;
    private String Nom;
    private String Description;
    private String PhotographiePath;
    private Galerie Galerie;

    //Constructeurs
    public Photographie() {
    }

    public Photographie(String Nom, String Description, String PhotographiePath, Galerie Galerie) {
        this.Nom = Nom;
        this.Description = Description;
        this.PhotographiePath = PhotographiePath;
        this.Galerie = Galerie;
    }

    public Photographie(int ID_Photographie, String Nom, String Description, String PhotographiePath, Galerie Galerie) {
        this.ID_Photographie = ID_Photographie;
        this.Nom = Nom;
        this.Description = Description;
        this.PhotographiePath = PhotographiePath;
        this.Galerie = Galerie;
    }

    //Getters & Setters
    public int getID_Photographie() {
        return ID_Photographie;
    }

    public void setID_Photographie(int ID_Photographie) {
        this.ID_Photographie = ID_Photographie;
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

    public String getPhotographiePath() {
        return PhotographiePath;
    }

    public void setPhotographiePath(String PhotographiePath) {
        this.PhotographiePath = PhotographiePath;
    }

    public Galerie getGalerie() {
        return Galerie;
    }

    public void setGalerie(Galerie Galerie) {
        this.Galerie = Galerie;
    }

    //toString
    @Override
    public String toString() {
        return "Photographie{" + "ID_Photographie=" + ID_Photographie + ", Nom=" + Nom + ", Description=" + Description + ", PhotographiePath=" + PhotographiePath + "\n Galerie=" + Galerie + '}';
    }
    

}
