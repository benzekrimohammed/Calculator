package calculator;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;

public class Controller {
    
   public Label panel;
 public void insert(Event e){
    Button button =  (Button)e.getSource();
    String number= button.getText();
    panel.setText(panel.getText()+number);     
    
 }
}
