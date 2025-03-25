package lk.ijse.gdse;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.UserBO;

import java.io.IOException;
import java.util.Objects;

public class Launcher extends Application {

    UserBO userBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("ikjdw");

        userBO.initializeDefaultUsers();

        Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Loading.fxml")));
        stage.setScene(new Scene(load));
        stage.show();

        Task<Scene> loadingTask = new Task<Scene>(){
            @Override
            protected Scene call() throws IOException {
                FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("/view/DashBoard.fxml"));
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
