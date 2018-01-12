package views;

import controllers.MainManufactoryController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ManufactoryController implements Initializable{
    @FXML public Button distributedBtn;
    @FXML public Button saveBtn;
    @FXML public Label localDate;

    TabDistributed tabDistributed;
    TabSaveManufacture tabSaveManufacture;

    private MainManufactoryController controller;

    public void initialize(URL location, ResourceBundle resources) {
        LocalDate localdate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = localdate.format(formatter);

        localDate.setText(formattedString);

    }

    public void showDistributed() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TabDistributed.fxml"));
            Pane distributed = loader.load();
            tabDistributed = loader.getController();
            System.out.println("controller = " + controller);
            tabDistributed.setController(controller);
            Scene scene = new Scene(distributed);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("การกระจายการผลิต");
            stage.showAndWait();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void showSaveManufacture(){
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/TabSaveManufacture.fxml"));
            Pane saveManu = loader.load();
            tabSaveManufacture = loader.getController();
            tabSaveManufacture.setController(controller);
            Scene scene = new Scene(saveManu);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("บันทึกผลการผลิต");
            stage.showAndWait();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void setController(MainManufactoryController controller) {
        this.controller = controller;
        System.out.println("controller = " + controller);

        distributedBtn.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                showDistributed();
            }
        });

        saveBtn.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                showSaveManufacture();
            }
        });
    }
}
