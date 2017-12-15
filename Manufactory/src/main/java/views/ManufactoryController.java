package views;

import controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManufactoryController implements Initializable{

    @FXML private Tab tabDistributed;
    @FXML private Tab tabSaveManufacture;
    private TabDistributed tabDistributedView;
    private TabSaveManufacture tabSaveManufactureView;

    MainController controller;


    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initTabDistributed(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/TabDistributed.fxml"));
            Pane tab = loader.load();
            tabDistributed.setContent(tab);
            tabDistributedView = loader.getController();
            if (controller != null)
                tabDistributedView.setController(controller);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initTabSaveManufacture(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/TabSaveManufacture.fxml"));
            Pane tab = loader.load();
            tabSaveManufacture.setContent(tab);
            tabSaveManufactureView = loader.getController();
            if (controller != null)
                tabSaveManufactureView.setController(controller);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setController(MainController controller) {
        this.controller = controller;
        initTabDistributed();
        initTabSaveManufacture();
    }
}
