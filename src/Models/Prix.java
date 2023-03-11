package Models;

/**
 *
 * @author wael
 */
public class Prix {
    
    private int ID_Prix;
    private Film Film;
    private String TypePrix;
   

    public Prix() {
    }

    public Prix(Film Film, String TypePrix) {
        this.Film = Film;
        this.TypePrix = TypePrix;
    }

    public Prix(int ID_Prix, Film Film, String TypePrix) {
        this.ID_Prix = ID_Prix;
        this.Film = Film;
        this.TypePrix = TypePrix;
    }

    public int getID_Prix() {
        return ID_Prix;
    }

    public void setID_Prix(int ID_Prix) {
        this.ID_Prix = ID_Prix;
    }

    public Film getFilm() {
        return Film;
    }

    public void setFilm(Film Film) {
        this.Film = Film;
    }

    public String getTypePrix() {
        return TypePrix;
    }

    public void setTypePrix(String TypePrix) {
        this.TypePrix = TypePrix;
    }

    @Override
    public String toString() {
        return "Prix{" + "ID_Prix=" + ID_Prix + ", Film=" + Film + ", TypePrix=" + TypePrix + '}';
    }

   
}