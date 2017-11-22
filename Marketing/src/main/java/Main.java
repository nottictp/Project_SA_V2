import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import views.MarketingController;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        MainController controller = new MainController();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/marketing.fxml"));
        Pane mainLayout = loader.load();
        MarketingController marketingController = loader.getController();
        marketingController.setController(controller);


        primaryStage.setTitle("Marketing");
        primaryStage.setScene(new Scene(mainLayout, 400, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
