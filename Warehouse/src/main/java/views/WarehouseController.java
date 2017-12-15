package views;

import controllers.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.WarehouseProduct;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WarehouseController implements Initializable{

    @FXML private Tab searchTab;
    @FXML private Tab importTab;
    @FXML private Tab exposeTab;
    private TabExposeView tabExposeView;
    private TabImportView tabImportView;
    private TabSearchView tabSearchView;
    private MainController controller;


    public void setController(MainController controller) {
        this.controller = controller;
        initTabExposeView();
        initTabImportView();
        initTabSearchView();
    }

    public void initialize(URL location, ResourceBundle resources) {

    }


    public void initTabExposeView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/TabExpose.fxml"));
            Pane tab = loader.load();
            exposeTab.setContent(tab);
            tabExposeView = loader.getController();
            if (controller != null)
                tabExposeView.setController(controller);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initTabImportView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/TabImport.fxml"));
            Pane tab = loader.load();
            importTab.setContent(tab);
            tabImportView = loader.getController();
            if (controller != null)
                tabImportView.setController(controller);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initTabSearchView(){
        try {
            System.out.println("on initTsb");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Tabsearch.fxml"));
            Pane tab = loader.load();
            searchTab.setContent(tab);
            tabSearchView = loader.getController();
            System.out.println("controller = " + controller);
            if (controller != null)
                tabSearchView.setController(controller);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

