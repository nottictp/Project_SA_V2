import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import views.ManufactoryController;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        MainController controller = new MainController();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/manufactory.fxml"));
        Pane mainLayout = loader.load();
        ManufactoryController marketingController = loader.getController();
        marketingController.setController(controller);

        primaryStage.setTitle("Manufactory");
        primaryStage.setScene(new Scene(mainLayout, 774, 582));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
