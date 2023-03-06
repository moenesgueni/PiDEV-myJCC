package sample;

import GUI.myListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Models.Evenement;

/**
 * Created by Mahmoud Hamwi on 02-Feb-21.
 */
public class SongController {

    @FXML
    private Label songName;

    @FXML
    private Label artist;

    private Evenement song;
    
    
   
    private myListener myListener;

    public void setData(Evenement fruit, myListener myListener) {
        this.song = fruit;
        this.myListener = myListener;
        songName.setText(song.getNom_event());
        artist.setText(song.getLieu());
        
    }
    
    
    
    
    
   
}
