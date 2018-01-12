package views;

import controllers.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MarketingCenter implements Initializable {

    @FXML private TableColumn ordersCol;
    @FXML private TableColumn idCol;
    @FXML private TableColumn nameCol;
    @FXML private TableColumn unitCol;
    @FXML private TableColumn quantityCol;
    @FXML private TableColumn statusCol;
    @FXML private Button orderBtn;
    @FXML private Label localDate;
    private MainController controller;
    MarketingController marketingController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LocalDate localdate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = localdate.format(formatter);
        localDate.setText(formattedString);
    }

    public void showOrderPopup() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/order.fxml"));
            Pane orderPopup = loader.load();
            System.out.println("controller = " + controller);
            MarketingController marketingController = loader.getController();
            marketingController.setController(controller);
            Scene scene = new Scene(orderPopup);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("สั่งผลิต");
            stage.showAndWait();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void setController(MainController controller) {
        this.controller = controller;
        orderBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                showOrderPopup();
            }
        });
    }
}
