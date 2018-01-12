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
        loader.setLocation(getClass().getResource("/marketing.fxml"));
        AnchorPane mainLayout = loader.load();
        MarketingCenter marketingCenter = loader.getController();
        marketingCenter.setController(controller);


        primaryStage.setTitle("Marketing");
        primaryStage.setScene(new Scene(mainLayout, 650, 312));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
