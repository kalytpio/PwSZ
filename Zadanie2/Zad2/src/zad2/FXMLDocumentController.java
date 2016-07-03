package zad2;

import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button przycisk;
    @FXML
    private Pane obszar;
    
    @FXML
    private void ucieknij(MouseEvent e) {
        double x,y,wysokosc,szerokosc;
        Random generator = new Random();
        int l;
        wysokosc = obszar.getHeight();
        szerokosc = obszar.getWidth();
        l = generator.nextInt((int)wysokosc);
        y = generator.nextDouble()*l;
        przycisk.setLayoutY(y);
        l = generator.nextInt((int)szerokosc);
        x = generator.nextDouble()*l;
        przycisk.setLayoutX(x);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
