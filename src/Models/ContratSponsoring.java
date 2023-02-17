package Models;

import Utils.EnumEtatContrat;
import Utils.EnumTypeContrat;
import java.sql.Date;

public class ContratSponsoring {
    //var
    private int ID_Contrat;
    private Date DateDebut;
    private Date DateFin;
    private EnumTypeContrat Type;
    private EnumEtatContrat Etat;
    private float SalaireDt;
    private String TermesPDF;
    private User Sponsor;
    private User Photoraphe;

    //Constructeurs
    public ContratSponsoring() {
    }

    public ContratSponsoring(Date DateDebut, Date DateFin, EnumTypeContrat Type, EnumEtatContrat Etat, float SalaireDt, String TermesPDF, User Sponsor, User Photoraphe) {
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.Type = Type;
        this.Etat = Etat;
        this.SalaireDt = SalaireDt;
        this.TermesPDF = TermesPDF;
        this.Sponsor = Sponsor;
        this.Photoraphe = Photoraphe;
    }

    public ContratSponsoring(int ID_Contrat, Date DateDebut, Date DateFin, EnumTypeContrat Type, EnumEtatContrat Etat, float SalaireDt, String TermesPDF, User Sponsor, User Photoraphe) {
        this.ID_Contrat = ID_Contrat;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.Type = Type;
        this.Etat = Etat;
        this.SalaireDt = SalaireDt;
        this.TermesPDF = TermesPDF;
        this.Sponsor = Sponsor;
        this.Photoraphe = Photoraphe;
    }



    //Getters & Setters
    public int getID_Contrat() {
        return ID_Contrat;
    }

    public void setID_Contrat(int ID_Contrat) {
        this.ID_Contrat = ID_Contrat;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date DateDebut) {
        this.DateDebut = DateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    public EnumTypeContrat getType() {
        return Type;
    }

    public void setType(EnumTypeContrat Type) {
        this.Type = Type;
    }

    public EnumEtatContrat getEtat() {
        return Etat;
    }

    public void setEtat(EnumEtatContrat Etat) {
        this.Etat = Etat;
    }

    public float getSalaireDt() {
        return SalaireDt;
    }

    public void setSalaireDt(float SalaireDt) {
        this.SalaireDt = SalaireDt;
    }

    public String getTermesPDF() {
        return TermesPDF;
    }

    public void setTermesPDF(String TermesPDF) {
        this.TermesPDF = TermesPDF;
    }

    public User getSponsor() {
        return Sponsor;
    }

    public void setSponsor(User Sponsor) {
        this.Sponsor = Sponsor;
    }

    public User getPhotoraphe() {
        return Photoraphe;
    }

    public void setPhotoraphe(User Photoraphe) {
        this.Photoraphe = Photoraphe;
    }

    

    //toString

    @Override
    public String toString() {
        return "ContratSponsoring{" + "ID_Contrat=" + ID_Contrat + ", DateDebut=" + DateDebut + ", DateFin=" + DateFin + ", Type=" + Type + ", Etat=" 
                + Etat + ", SalaireDt=" + SalaireDt + ", TermesPDF=" + TermesPDF + "\n Sponsor=" + Sponsor + "\n Photoraphe=" + Photoraphe + '}';
    }
   

}
