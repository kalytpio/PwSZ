package zad4;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {
    
    int zaklad,x,y,i;
    double gotowka = 500;
    String[] kolor = {"(Kier","(Pik","(Karo","(Trefl"};
    String[] karty = new String[10];
    Random gen = new Random();
    boolean s;
    boolean c = false;
    String karta,pkarty,ckarty;
    int gsuma,csuma,gsuma2,csuma2,wart;
    
    @FXML private TextField money,bet;
    @FXML private Label pscore,cscore;
    @FXML private TextArea croupier,player;
    @FXML private Button confirm,hit,stand;
      
    @FXML
    public void start(ActionEvent e) {
        zaklad = Integer.parseInt(bet.getText());
        if(zaklad>gotowka) JOptionPane.showMessageDialog(null,"Nie masz tyle gotówki!");
        else rozdanie();
    }
    
    @FXML
    public void rozdanie() {
        bet.setDisable(true);
        for(int l=0;l<karty.length;l++) {
            karty[l] = "";
        }
        pkarty = "";
        ckarty = "";
        i = 0;
        gsuma = 0;
        csuma = 0;
        gsuma2 = 0;
        csuma2 = 0;
        boolean b = false;
        hit.setDisable(false);
        stand.setDisable(false);
        player.clear();
        croupier.clear();
        pscore.setText("");
        cscore.setText("");
        
        while(i<3) {
            do {
                s = false;
                x = gen.nextInt(4);
                y = gen.nextInt(13)+1;
                for(int l=0;l<karty.length;l++) {
                    if(karty[l] == String.valueOf(x) + String.valueOf(y)) s = true;
                }
            } while(s == true); 
            
            karty[i] = String.valueOf(x) + String.valueOf(y);
            
            if(y == 1) { karta = "As"; wart = 11; }
            if(y == 11) { karta = "Walet"; wart = 10; }
            if(y == 12) { karta = "Dama"; wart = 10; }
            if(y == 13) { karta = "Król"; wart = 10; }
            if(y>1 && y<11) { karta = String.valueOf(y); wart = y; }
            
            if(i == 2) { 
                ckarty = ckarty + kolor[x] + " " + karta + ") "; 
                csuma += wart; 
                if(karta == "As") csuma2 +=1; 
                else csuma2 += wart; 
            } else { 
                pkarty = pkarty + kolor[x] + " " + karta + ") "; 
                gsuma += wart; 
                if(karta == "As") gsuma2 += 1; 
                else gsuma2 += wart;
                if(wart == 10) b = true;
            }
            i++;
        }
        
        croupier.setText(ckarty);
        if(croupier.getText().contains("As")) cscore.setText(String.valueOf(csuma) + " (" + String.valueOf(csuma2) + ")");
        else cscore.setText(String.valueOf(csuma));
        
        player.setText(pkarty);
        if(player.getText().contains("As")) pscore.setText(String.valueOf(gsuma) + " (" + String.valueOf(gsuma2) + ")");
        else pscore.setText(String.valueOf(gsuma));
        confirm.setDisable(true);
        if(player.getText().contains("As") && b == true) black();
                                          
    }
    
    @FXML 
    public void black() {
        if(croupier.getText().contains("As") || croupier.getText().contains("Walet") || croupier.getText().contains("Dama") || croupier.getText().contains("Król") || croupier.getText().contains("10")) {
            do {
                s = false;
                x = gen.nextInt(4);
                y = gen.nextInt(13)+1;
                for(int l=0;l<karty.length;l++) 
                    if(karty[l] == String.valueOf(x) + String.valueOf(y)) s = true;              
            } while(s == true);
            ckarty = ckarty + kolor[x] + " " + karta + ") ";
            croupier.setText(ckarty);
            if((croupier.getText().contains("As") && y >9)) JOptionPane.showMessageDialog(null,"Remis!");
            else {
                gotowka = gotowka + zaklad * 2.5;
                JOptionPane.showMessageDialog(null,"Wygrałeś!");
                money.setText(String.valueOf(gotowka));
            }
        } else {
            gotowka = gotowka + zaklad * 2.5;
            JOptionPane.showMessageDialog(null,"Wygrałeś!");
            money.setText(String.valueOf(gotowka));
        }
        bet.setDisable(false);
        bet.clear();
        confirm.setDisable(false);
        hit.setDisable(true);
        stand.setDisable(true);
    }
    
    @FXML
    public void card(ActionEvent event) {
        do {
                s = false;
                x = gen.nextInt(4);
                y = gen.nextInt(13)+1;
                for(int l=0;l<karty.length;l++) {
                    if(karty[l] == String.valueOf(x) + String.valueOf(y)) s = true;
                }
            } while(s == true); 
            
            karty[i] = String.valueOf(x) + String.valueOf(y);
            
            if(y == 1) { karta = "As"; wart = 11; }
            if(y == 11) { karta = "Walet"; wart = 10; }
            if(y == 12) { karta = "Dama"; wart = 10; }
            if(y == 13) { karta = "Król"; wart = 10; }
            if(y>1 && y<11) { karta = String.valueOf(y); wart = y; }
            
            pkarty = pkarty + kolor[x] + " " + karta + ") "; 
            gsuma += wart; 
            if(karta == "As") gsuma2 += 1; 
            else gsuma2 += wart;
            
            player.setText(pkarty);
            if(player.getText().contains("As")) pscore.setText(String.valueOf(gsuma) + " (" + String.valueOf(gsuma2) + ")");
            else pscore.setText(String.valueOf(gsuma));
            
            i++;
            
            if(gsuma2>21 || (gsuma>21 && !player.getText().contains("As"))) {
                gotowka = gotowka - zaklad;
                JOptionPane.showMessageDialog(null,"Przegrałeś!");
                money.setText(String.valueOf(gotowka));
                bet.setDisable(false);
                bet.clear();
                confirm.setDisable(false);
                hit.setDisable(true);
                stand.setDisable(true);
            }
            if(gotowka==0) {
                JOptionPane.showMessageDialog(null,"Koniec gry!");
                System.exit(0);
            }   
    }
    
    @FXML
    public void stop() {
        int o=0;
        while((csuma<17 && !croupier.getText().contains("As")) || csuma2<17) {
            do {
                    s = false;
                    x = gen.nextInt(4);
                    y = gen.nextInt(13)+1;
                    for(int l=0;l<karty.length;l++) {
                        if(karty[l] == String.valueOf(x) + String.valueOf(y)) s = true;
                    }                   
                } while(s == true); 
            
            o++;
            if((croupier.getText().contains("As") && y>9 && o==1) || (o==1 && y==1 && csuma>9 && !croupier.getText().contains("As"))) {
                gotowka = gotowka - zaklad;
                JOptionPane.showMessageDialog(null,"Przegrałeś!");
                break;
            } else {
            
            karty[i] = String.valueOf(x) + String.valueOf(y);
            
            if(y == 1) { karta = "As"; wart = 11; }
            if(y == 11) { karta = "Walet"; wart = 10; }
            if(y == 12) { karta = "Dama"; wart = 10; }
            if(y == 13) { karta = "Król"; wart = 10; }
            if(y>1 && y<11) { karta = String.valueOf(y); wart = y; }
                
            ckarty = ckarty + kolor[x] + " " + karta + ") "; 
            csuma += wart; 
            if(karta == "As") csuma2 +=1; 
            else csuma2 += wart;
                
            croupier.setText(ckarty);
            if(croupier.getText().contains("As")) cscore.setText(String.valueOf(csuma) + " (" + String.valueOf(csuma2) + ")");
            else cscore.setText(String.valueOf(csuma));
                
            i++;
        }
        }
        
        if((csuma>21 && !croupier.getText().contains("As")) || csuma2>21) {
            JOptionPane.showMessageDialog(null,"Wygrałeś!");
                gotowka = gotowka + zaklad;  
        } else {
            int pwynik,cwynik;
            if(gsuma>21) pwynik=gsuma2; else pwynik=gsuma;
            if(csuma>21) cwynik=csuma2; else cwynik=csuma;
            if(pwynik>cwynik) {
                JOptionPane.showMessageDialog(null,"Wygrałeś!");
                gotowka = gotowka + zaklad;           
            } else {
                if(pwynik == cwynik) JOptionPane.showMessageDialog(null,"Remis!");
                else {
                    gotowka = gotowka - zaklad;
                    JOptionPane.showMessageDialog(null,"Przegrałeś!");
                }
            }      
        }
        
        money.setText(String.valueOf(gotowka));                
        bet.setDisable(false);
        bet.clear();
        confirm.setDisable(false);
        hit.setDisable(true);
        stand.setDisable(true);
        
        if(gotowka==0) {
            JOptionPane.showMessageDialog(null,"Koniec gry!");
            System.exit(0);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        money.setText(String.valueOf(gotowka));
        money.setDisable(true);
        stand.setDisable(true);
        hit.setDisable(true);
    }    
    
}
