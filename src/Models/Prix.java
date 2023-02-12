/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author wael
 */
public class Prix {
    private int ID_Prix;
    private int ID_Film;
    private String TypePrix;

    public Prix() {
    }

    public Prix(int ID_Film, String TypePrix) {
        this.ID_Film = ID_Film;
        this.TypePrix = TypePrix;
    }

    public Prix(int ID_Prix, int ID_Film, String TypePrix) {
        this.ID_Prix = ID_Prix;
        this.ID_Film = ID_Film;
        this.TypePrix = TypePrix;
    }

    public int getID_Prix() {
        return ID_Prix;
    }

    public void setID_Prix(int ID_Prix) {
        this.ID_Prix = ID_Prix;
    }

    public int getID_Film() {
        return ID_Film;
    }

    public void setID_Film(int ID_Film) {
        this.ID_Film = ID_Film;
    }

    public String getTypePrix() {
        return TypePrix;
    }

    public void setTypePrix(String TypePrix) {
        this.TypePrix = TypePrix;
    }

    @Override
    public String toString() {
        return "Prix{" + "ID_Prix=" + ID_Prix + ", ID_Film=" + ID_Film + ", TypePrix=" + TypePrix + '}';
    }
    
    
}
