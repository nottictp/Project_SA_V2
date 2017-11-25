package views;

import controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PopUpController implements Initializable {

    @FXML
    private ComboBox idProductCombo;
    @FXML
    private TextField amountField;
    @FXML
    private ComboBox unitCombo;
    @FXML
    private ComboBox stockCombo;
    @FXML
    private TextField shelfField;
    @FXML
    private Button addBtn;
    @FXML
    private Button cancelBtn;

    MainController controller;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setController(MainController controller){
        this.controller = controller;
    }
}
