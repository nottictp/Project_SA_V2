import controllers.MainHomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.HomeView;

import java.time.LocalDate;

public class MainHome extends Application {
    private MainHomeController controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new MainHomeController();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/MainMenu.fxml"));
        AnchorPane mainLayout = loader.load();
        HomeView homeView = loader.getController();
        homeView.setController(controller);
        homeView.getDate().setText("วันที่ "+LocalDate.now()+"");
        homeView.getNameCompany().setText("บริษัท ทีเอสเอ จำกัด");
        homeView.getAddressCompany().setText("1/1 พหลโยธิน 40 แขวงเสนานิคม เขตจตุจักร กรุงเทพมหานคร 10900");

        primaryStage.setTitle("หน้าหลัก");
        primaryStage.setScene(new Scene(mainLayout, 600, 307));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
