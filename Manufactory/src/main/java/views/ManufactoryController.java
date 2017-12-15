package views;

import controllers.MainManufactoryController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManufactoryController implements Initializable{
    public Tab distributedTab;
    public Tab saveManufactureTab;
    public TabDistributed tabDistributed;
    public TabSaveManufacture tabSaveManufacture;

    private MainManufactoryController controller;

    public void initialize(URL location, ResourceBundle resources) {

    }
    public void TabDistributed(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/TabDistributed.fxml"));
            Pane tab = loader.load();
            distributedTab.setContent(tab);
            tabDistributed = loader.getController();
            if (controller != null)
                tabDistributed.setController(controller);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void TabSaveManufacture(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/TabSaveManufacture.fxml"));
            Pane tab = loader.load();
            saveManufactureTab.setContent(tab);
            tabSaveManufacture = loader.getController();
            if (controller != null)
                tabSaveManufacture.setController(controller);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setController(MainManufactoryController controller) {
        this.controller = controller;
        TabDistributed();
        TabSaveManufacture();
    }
}
