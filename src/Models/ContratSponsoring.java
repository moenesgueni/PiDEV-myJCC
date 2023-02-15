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
    private int ID_Sponsor;    //de type Object user
    private int ID_Photoraphe; //de type Object user

    //Constructeurs
    public ContratSponsoring() {
    }

    public ContratSponsoring(Date DateDebut, Date DateFin, EnumTypeContrat Type, EnumEtatContrat Etat, float SalaireDt, String TermesPDF, int ID_Sponsor, int ID_Photoraphe) {
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.Type = Type;
        this.Etat = Etat;
        this.SalaireDt = SalaireDt;
        this.TermesPDF = TermesPDF;
        this.ID_Sponsor = ID_Sponsor;
        this.ID_Photoraphe = ID_Photoraphe;
    }

    public ContratSponsoring(int ID_Contrat, Date DateDebut, Date DateFin, EnumTypeContrat Type, EnumEtatContrat Etat, float SalaireDt, String TermesPDF, int ID_Sponsor, int ID_Photoraphe) {
        this.ID_Contrat = ID_Contrat;
        this.DateDebut = DateDebut;
        this.DateFin = DateFin;
        this.Type = Type;
        this.Etat = Etat;
        this.SalaireDt = SalaireDt;
        this.TermesPDF = TermesPDF;
        this.ID_Sponsor = ID_Sponsor;
        this.ID_Photoraphe = ID_Photoraphe;
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

    public int getID_Sponsor() {
        return ID_Sponsor;
    }

    public void setID_Sponsor(int ID_Sponsor) {
        this.ID_Sponsor = ID_Sponsor;
    }

    public int getID_Photoraphe() {
        return ID_Photoraphe;
    }

    public void setID_Photoraphe(int ID_Photoraphe) {
        this.ID_Photoraphe = ID_Photoraphe;
    }

    //toString
    @Override
    public String toString() {
        return "--------------------\nContratSponsoring :" + "\nID_Contrat : " + ID_Contrat + "\nDateDebut : " + DateDebut + "\nDateFin : " + DateFin + "\nType : " + Type
                + "\nEtat Contart : " + Etat + "\nSalaireDt : " + SalaireDt + "\nTermesPDF : " + TermesPDF + "\nID_Sponsor : " + ID_Sponsor + "\nID_Photoraphe : " + ID_Photoraphe;
    }

}
