package ru.kpfu.itis.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.kpfu.itis.controller.MainController;

public class ClientApplication extends Application {

    private static final String FXML_FILE_NAME = "/fxml/Main.fxml";

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = fxmlLoader.load(getClass().getResourceAsStream(FXML_FILE_NAME));



        Scene scene = new Scene(root);



        primaryStage.setTitle("Крокодил");

        primaryStage.setScene(scene);
        MainController mainController = fxmlLoader.getController();

        String css = this.getClass().getResource("/css/style.css").toExternalForm();
        primaryStage.getScene().getStylesheets().add(css);
        primaryStage.show();
        primaryStage.show();
    }
}
