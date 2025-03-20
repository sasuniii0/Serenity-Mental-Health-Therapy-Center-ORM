package lk.ijse.gdse;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("ikjdw");
        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Loading.fxml")));
        stage.setScene(new Scene(load));
        stage.show();

        Task<Scene> loadingTask = new Task<Scene>(){
            @Override
            protected Scene call() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("/view/LoginForm.fxml"));
                return new Scene(fxmlLoader.load());
            }
        };
        loadingTask.setOnSucceeded(event ->{
            Scene value = loadingTask.getValue();

            stage.setTitle("Serenity Health Center");
            stage.setScene(value);
        });
        new Thread(loadingTask).start();
    }
}
