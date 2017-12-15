package views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class TabDistributed extends AnchorPane implements Serializable {

    @FXML private DatePicker datePicker;
    @FXML private TextField amountField;
    @FXML private TextField tab1RecorderTextField;
    @FXML private ComboBox unitCombo;
    @FXML private ComboBox typeCombo;
    @FXML private Button farmerSearchBtn;
    @FXML private Button submitBtn;

    @FXML private TableColumn groupColumn;
    @FXML private TableColumn tab1IDColumn;
    @FXML private TableColumn tab1nameColumn;
    @FXML private TableColumn capacityColumn;


}
