package entry;

import controllers.MainManufactoryController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import views.ManufactoryController;

public class MainManufactory extends Application{
    private ManufactoryController manufactoryController;
    @Override
    public void start(Stage primaryStage) throws Exception{
        MainManufactoryController controller = new MainManufactoryController();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/manufact.fxml"));
        Pane mainLayout = loader.load();
        manufactoryController = loader.getController();
        manufactoryController.setController(controller);

        primaryStage.setTitle("ฝ่ายการผลิต");
        primaryStage.setScene(new Scene(mainLayout, 635, 359));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
