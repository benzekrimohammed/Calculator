package calculator;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Controller {
   Pattern oprators = Pattern.compile("[*X%\\+/-]");
   Pattern comma = Pattern.compile("[,]");
   Matcher matcher;
   @FXML
   private Label panel;
   @FXML
   private Label result;
   @FXML
   private AnchorPane parent;
   @FXML
   private Button min,max,close;
   private double x=0,y=0;
   private Stage stage;
   @FXML
   public void initialize() {
       dragger();
   }
   public void topbar(Event event){
    Button button =  (Button)event.getSource();
    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    if(button.getId().equals("close")) stage.close();
    if(button.getId().equals("min")) stage.setIconified(true);
    else {stage.setFullScreenExitHint(" ");
    stage.setFullScreen(true);
   }
   }
   public  void error(){
      result.setText("ERROR");
   }

   public void dragger(){
      parent.setOnMousePressed(event ->{
        x= event.getSceneX();
        y= event.getSceneY();
      });
      parent.setOnMouseDragged(event->{
         stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
         stage.setX(event.getScreenX()  -x);
         stage.setY(event.getScreenY()-y);

      });
   }

   public void clearText(){
      panel.setText("");
      result.setText("");
   }
   
   public void insertop(String operator){
      
      if(operator.equals("*")) operator="X";
      if(operator.equals("/")) operator="÷";
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
      if(newresult.equals("Infinity")||newresult.equals("NaN")) error();
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
   boolean StringNoComma=!(comma.matcher(panel.getText()).find());
   if(e.getCode()==KeyCode.BACK_SPACE) delete();
   if(e.getCode()==KeyCode.ENTER) { calculate();e.consume(); }
   if(e.getCode().isDigitKey()) panel.setText(panel.getText()+e.getText());
   if(e.getCode()==KeyCode.DECIMAL && StringNoComma ) 
   panel.setText(panel.getText()+",");
   if(oprators.matcher(e.getText()).find()) insertop(e.getText());
   }
}
