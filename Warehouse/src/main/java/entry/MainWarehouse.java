package entry;

import controllers.MainWarehouseController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import views.WarehouseController;

public class MainWarehouse extends Application{
    private MainWarehouseController controller;
    @Override
    public void start(Stage primaryStage) throws Exception{
        controller = new MainWarehouseController();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/warehouse.fxml"));
        AnchorPane mainLayout = loader.load();
        WarehouseController warehouseController = loader.getController();
        warehouseController.setController(controller);

        primaryStage.setTitle("ฝ่ายคลังสินค้า");
        primaryStage.setScene(new Scene(mainLayout, 774, 540));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
