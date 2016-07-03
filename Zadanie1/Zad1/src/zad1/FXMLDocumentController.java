package zad1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField in;
    @FXML
    private TextField out;
    @FXML
    private RadioButton c1,c2,f1,f2,k1,k2;
    
    @FXML
    public void wpisz(KeyEvent e) { 
        if(e.getCode() == KeyCode.ENTER) {
            double x = Integer.parseInt(in.getText());
            if(c1.isSelected()) {
                if(c2.isSelected()) out.setText(String.valueOf(x));
                if(f2.isSelected()) { x = Math.round((x*1.8+32)*100); out.setText(String.valueOf(x/100)); }
                if(k2.isSelected()) { x = Math.round((x+273.15)*100); out.setText(String.valueOf(x/100)); }
            }
            if(f1.isSelected()) {
                if(c2.isSelected()) { x = Math.round(((x-32)/1.8)*100); out.setText(String.valueOf(x/100)); }
                if(f2.isSelected()) out.setText(String.valueOf(x));
                if(k2.isSelected()) { Math.round(((x+459.67)*5/9)*100); out.setText(String.valueOf(x/100)); }
            }
            if(k1.isSelected()) {
                if(c2.isSelected()) { Math.round((x-273.15)*100); out.setText(String.valueOf(x/100)); }
                if(f2.isSelected()) { Math.round((x*1.8-459.67)*100); out.setText(String.valueOf(x/100)); }
                if(k2.isSelected()) out.setText(String.valueOf(x));
            }
        }    
    }  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
