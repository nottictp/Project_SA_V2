package views;

import controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ManufactoryController implements Initializable{

    @FXML private DatePicker datePicker;
    @FXML private TextField tab1RecorderTextField;
    @FXML private ComboBox lotNoCombo;
    @FXML private ComboBox typeCombo;
    @FXML private ComboBox unitCombo;
    @FXML private ComboBox tab2RecorderCombo;
    @FXML private TextField amountField;
    @FXML private Button farmerSearchBtn;
    @FXML private Button submitBtn;
    @FXML private Button saveBtn;
    @FXML private Button cancelBtn;
    @FXML private TableView tab1DataTable;
    @FXML private TableView tab2DataTable;
    @FXML private TableColumn groupColumn;
    @FXML private TableColumn orderColumn;
    @FXML private TableColumn tab1IDColumn;
    @FXML private TableColumn tab2IDColumn;
    @FXML private TableColumn tab1nameColumn;
    @FXML private TableColumn tab2nameColumn;
    @FXML private TableColumn purchaseColumn;
    @FXML private TableColumn unitColumn;
    @FXML private TableColumn areaColumn;

    MainController controller;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setController(MainController controller) {
        this.controller = controller;
    }
}
