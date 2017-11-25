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

public class WarehouseController implements Initializable{

    @FXML private ComboBox comboTypeSearch;
    @FXML private ComboBox comboType;
    @FXML private ComboBox comboDoc;
    @FXML private ComboBox tab2RecorderCombo;
    @FXML private ComboBox tab2RecipientCombo;
    @FXML private ComboBox tab3RecorderCombo;
    @FXML private ComboBox tab3RecipientCombo;
    @FXML private ComboBox tab2DepartmentCombo;
    @FXML private ComboBox tab3DepartmentCombo;
    @FXML private Button buttonSearch;
    @FXML private Button btnPrint;
    @FXML private Button tab2CancelBtn;
    @FXML private Button tab2SubmitBtn;
    @FXML private Button tab2RemoveBtn;
    @FXML private Button tab3CancelBtn;
    @FXML private Button tab3SubmitBtn;
    @FXML private Button tab3RemoveBtn;
    @FXML private Button tab2AddBtn;
    @FXML private Button tab3AddBtn;
    @FXML private TextField tab1SearchField;
    @FXML private TextField tab3Note;
    @FXML private TextField tab3DocNo;
    @FXML private TextField tab2Form;
    @FXML private TextField tab2DocNo;
    @FXML private TableView tab1DataTable;
    @FXML private TableView tab2DataTable;
    @FXML private TableView tab3DataTable;
    @FXML private TableColumn tab1OrderColumn;
    @FXML private TableColumn senderColumn;
    @FXML private TableColumn capacityColumn;
    @FXML private TableColumn docNoColumn;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn deliveryDateColumn;
    @FXML private TableColumn recipientColumn;
    @FXML private TableColumn tab1RecorderColumn;
    @FXML private TableColumn tab1UnitColumn;
    @FXML private TableColumn tab1IDColumn;
    @FXML private TableColumn tab1ShelfColumn;
    @FXML private TableColumn tab2OrderColumn;
    @FXML private TableColumn tab2IDColumn;
    @FXML private TableColumn tab2NameProductColumn;
    @FXML private TableColumn tab2UnitColumn;
    @FXML private TableColumn tab2AmountColumn;
    @FXML private TableColumn tab2StockColumn;
    @FXML private TableColumn tab2ShelfColumn;
    @FXML private TableColumn tab3OrderColumn;
    @FXML private TableColumn tab3IDColumn;
    @FXML private TableColumn tab3NameProductColumn;
    @FXML private TableColumn tab3UnitColumn;
    @FXML private TableColumn tab3AmountColumn;
    @FXML private TableColumn tab3StockColumn;
    @FXML private TableColumn tab3ShelfColumn;
    @FXML private DatePicker tab2DocDate;
    @FXML private DatePicker tab3DocDate;


    MainController controller;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setController(MainController controller) {
        this.controller = controller;
    }

    @FXML
    public void handlerBtnAdd(ActionEvent event) throws IOException {
        Stage secondaryStage = new Stage();
        controller = new MainController();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/popup.fxml"));
        AnchorPane mainLayout = loader.load();
        PopUpController popUpController = loader.getController();
        popUpController.setController(controller);

        secondaryStage.setTitle("Add");
        secondaryStage.setScene(new Scene(mainLayout, 400, 300));
        secondaryStage.show();
    }

    @FXML
    public void handlerBtnPrint(ActionEvent event){

    }

    @FXML
    public void handlerBtnSearch(ActionEvent event){

    }


}
