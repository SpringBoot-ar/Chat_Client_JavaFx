package springboot_ar.chat.client.main;

import javafx.application.Application;
import javafx.stage.Stage;
import springboot_ar.chat.client.core.Core;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Core.start();
    }
}
