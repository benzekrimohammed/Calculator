package calculator;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    
   public Label panel;
 public void insert(Event e){
    Button button =  (Button)e.getSource();
    String number= button.getText();
    if(panel.getText()=="0"){ 
       panel.setText("");
    }
    panel.setText(panel.getText()+number);  
 }
 public void calculate(){
 Pattern numbers = Pattern.compile("(\\d+)([+-\\*])+");
 Matcher phrase = numbers.matcher(panel.getText());
 double result=0;
 for(int i=0;i<phrase.groupCount();i+=2){
    switch(phrase.group(i)){
       case "÷": 
       result += Float.parseFloat(phrase.group(i-1))/Float.parseFloat(phrase.group(i+1));
       System.out.println(result);
       break;
    }
 }
 
 
 }
}
