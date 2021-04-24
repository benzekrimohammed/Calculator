package calculator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage)  throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("calculator.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Calculator :)");
        scene.setOnKeyPressed(e -> {
            controller.keypressed(e);
        });
        stage.show();
    }
}