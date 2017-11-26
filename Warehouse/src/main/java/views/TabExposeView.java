package views;

import controllers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TabExposeView extends AnchorPane implements Initializable {
    @FXML private Button removeBtn;
    @FXML private TableColumn orderColumn;
    @FXML private TableColumn idColumn;
    @FXML private TableColumn nameProductColumn;
    @FXML private TableColumn unitColumn;
    @FXML private TableColumn amountColumn;
    @FXML private TableColumn stockColumn;
    @FXML private TableView exposeTable;
    @FXML private DatePicker docDate;
    @FXML private TextField recorderField;
    @FXML private TextField recipientField;
    @FXML private ComboBox departmentCombo;
    @FXML private Button cancelBtn;
    @FXML private Button saveBtn;
    @FXML private TextField note;
    @FXML private TextField docNo;
    @FXML private Button addBtn;

    MainController controller;
    public void setController(MainController controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void handlerBtnAddTab3(ActionEvent event) throws IOException {
        Stage secondaryStage = new Stage();
        controller = new MainController();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/popupPick.fxml"));
        AnchorPane mainLayout = loader.load();
        PopUpPickController popUpPickController = loader.getController();
        popUpPickController.setController(controller);

        secondaryStage.setTitle("เบิกสินค้าออก");
        secondaryStage.setScene(new Scene(mainLayout, 400, 300));
        secondaryStage.show();
    }
}
