package views;

import controllers.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.Warehouse;

import java.io.Serializable;

public class TabSaveManufacture  implements Serializable{

    @FXML private ComboBox lotNoCombo;
    @FXML private ComboBox recorderCombo;
    @FXML private TableView dataTable;
    @FXML private TableColumn orderColumn;
    @FXML private TableColumn idColumn;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn purchaseColumn;
    @FXML private TableColumn unitColumn;
    @FXML private Button saveBtn;
    @FXML private Button cancelBtn;

    private MainController controller;


    public void setController(MainController mainController) {
        this.controller = mainController;
    }

    
}
