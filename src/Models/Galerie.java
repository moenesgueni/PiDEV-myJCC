package Models;

public class Galerie {
    //var
    private int ID_Galerie;
    private String Nom;
    private String Description;
    private int ID_Photographe;
    
    //Constructeurs

    public Galerie() {
    }

    public Galerie(String Nom, String Description, int ID_Photographe) {
        this.Nom = Nom;
        this.Description = Description;
        this.ID_Photographe = ID_Photographe;
    }

    public Galerie(int ID_Galerie, String Nom, String Description, int ID_Photographe) {
        this.ID_Galerie = ID_Galerie;
        this.Nom = Nom;
        this.Description = Description;
        this.ID_Photographe = ID_Photographe;
    }
    
    //Getters & Setters

    public int getID_Galerie() {
        return ID_Galerie;
    }

    public void setID_Galerie(int ID_Galerie) {
        this.ID_Galerie = ID_Galerie;
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

    public int getID_Photographe() {
        return ID_Photographe;
    }

    public void setID_Photographe(int ID_Photographe) {
        this.ID_Photographe = ID_Photographe;
    }
    
    //toString

    @Override
    public String toString() {
        return "Galerie{" + "ID_Galerie=" + ID_Galerie + ", Nom=" + Nom + ", Description=" + Description + ", ID_Photographe=" + ID_Photographe + '}';
    }
    
    

}
