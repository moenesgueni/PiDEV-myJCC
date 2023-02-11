package Models;

public class Photographie {
    //var
    private int ID_Photographie;
    private String Nom;
    private String Description;
    private String PhotographieB64;
    private int ID_Galerie;
    
    //Constructeurs

    public Photographie() {
    }

    public Photographie(String Nom, String Description, String PhotographieB64, int ID_Galerie) {
        this.Nom = Nom;
        this.Description = Description;
        this.PhotographieB64 = PhotographieB64;
        this.ID_Galerie = ID_Galerie;
    }

    public Photographie(int ID_Photographie, String Nom, String Description, String PhotographieB64, int ID_Galerie) {
        this.ID_Photographie = ID_Photographie;
        this.Nom = Nom;
        this.Description = Description;
        this.PhotographieB64 = PhotographieB64;
        this.ID_Galerie = ID_Galerie;
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

    public String getPhotographieB64() {
        return PhotographieB64;
    }

    public void setPhotographieB64(String PhotographieB64) {
        this.PhotographieB64 = PhotographieB64;
    }

    public int getID_Galerie() {
        return ID_Galerie;
    }

    public void setID_Galerie(int ID_Galerie) {
        this.ID_Galerie = ID_Galerie;
    }
    
    //toString
    @Override
    public String toString() {
        return "Photographie{" + "ID_Photographie=" + ID_Photographie + ", Nom=" + Nom + ", Description=" + Description + ", PhotographieB64=" + PhotographieB64 + ", ID_Galerie=" + ID_Galerie + '}';
    }
    

}
