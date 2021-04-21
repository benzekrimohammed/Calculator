package calculator;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class Controller {
   @FXML
   private Label panel;
   @FXML
   private Label result;

   public void clearText(){
      panel.setText("");
      result.setText("");
   }
   public void insertNumber(String btext){
      panel.setText(panel.getText()+btext);}
   
   public void insertop(String operator){
      panel.setText(panel.getText() + " " + operator + " ");
   }
   public Label getexpression(){
      return panel;
   }
   public void delete(){
      if(!getexpression().getText().isEmpty()){
         StringBuilder text = new StringBuilder(getexpression().getText());
         text.deleteCharAt(text.length()-1);
         getexpression().setText(text.toString());
      }
   }
   
   public void setresult(String newresult){
      this.result.setText("= " + newresult);
   }
   public void insert(Event e){
      Button button =  (Button)e.getSource();
      String btext = button.getText();

      switch(btext){
         case "1": 
         case "2": 
         case "3": 
         case "4": 
         case "5": 
         case "6": 
         case "7": 
         case "8": 
         case "9": 
         case "0":
         case ",":
         insertNumber(btext);
         break;
         case "+":
         case "-":
         case "X":
         case "÷":
         case "%":
         insertop(btext);
         break;
         case "AC":
         clearText();
         break;
         case "=":
         double result = Evaluation.evaluate(this.getexpression().getText());
         setresult(String.valueOf(result));
         break;
         

      }


 
 }
 
}
