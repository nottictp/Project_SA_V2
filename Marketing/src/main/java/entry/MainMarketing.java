package entry;

import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.MarketingCenter;
import views.MarketingController;

public class MainMarketing extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        MainController controller = new MainController();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/order.fxml"));
        AnchorPane mainLayout = loader.load();
        MarketingController marketingController = loader.getController();
        marketingController.setController(controller);


        primaryStage.setTitle("ฝ่ายการตลาด");
        primaryStage.setScene(new Scene(mainLayout, 400, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
