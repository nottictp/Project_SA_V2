package views;

import controllers.MainManufactoryController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ManufactoryController implements Initializable{


    @FXML private ComboBox lotNoCombo;
    @FXML private ComboBox tab2RecorderCombo;
    @FXML private Button saveBtn;
    @FXML private Button cancelBtn;
    @FXML private TableView tab1DataTable;
    @FXML private TableView tab2DataTable;
    @FXML private TableColumn orderColumn;
    @FXML private TableColumn tab2IDColumn;
    @FXML private TableColumn tab2nameColumn;
    @FXML private TableColumn purchaseColumn;
    @FXML private TableColumn unitColumn;

    MainManufactoryController controller;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setController(MainManufactoryController controller) {
        this.controller = controller;
    }
}
