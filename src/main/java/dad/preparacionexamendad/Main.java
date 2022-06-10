package dad.preparacionexamendad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        RootController rootController = new RootController();
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(new Scene(rootController.getView()));
        primaryStage.show();


        //SceneController
        /*try {
            Parent root = FXMLLoader.load(getClass().getResource("/scene1.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }*/
    }

    public static void main(String[] args) {
        launch();
    }
}