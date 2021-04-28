package calculator;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Controller {
   Pattern oprators = Pattern.compile("[*X%\\+/-]");
   Matcher matcher;
   @FXML
   private Label panel;
   @FXML
   private Label result;

   public void clearText(){
      panel.setText("");
      result.setText("");
   }
   
   public void insertop(String operator){
      
      if(operator.equals("*")) operator="X";
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
      matcher = oprators.matcher(btext);
      if(matcher.find()){ insertop(btext);return;};
      if(btext=="AC") return;
      panel.setText(panel.getText()+btext);     

      }

   public void calculate(){
      double result = Evaluation.evaluate(this.getexpression().getText());
         setresult(String.valueOf(result));
   }  
   public void keypressed (KeyEvent e){
   if(e.getCode()==KeyCode.BACK_SPACE) delete();
   if(e.getCode()==KeyCode.ENTER) { calculate();e.consume(); }
   if(e.getCode().isDigitKey()) panel.setText(panel.getText()+e.getText());
   if(oprators.matcher(e.getText()).find()) insertop(e.getText());
   }
}
