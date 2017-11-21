package Warehouse;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WarehouseController implements Initializable{

    public TextField tab1SearchField;
    public TextField tab2DocNo;
    public TextField tab2Form;
    public TextField tab3DocNo;
    public TextField tab3Note;

    public DatePicker tab2DocDate;
    public DatePicker tab3DocDate;

    public Button buttonSearch;
    public Button btnPrint;
    public Button tab2AddBtn;
    public Button tab2RemoveBtn;
    public Button tab2SubmitBtn;
    public Button tab2CancelBtn;
    public Button tab3AddBtn;
    public Button tab3RemoveBtn;
    public Button tab3SubmitBtn;
    public Button tab3CancelBtn;


    @FXML
    private ComboBox comboType, comboTypeSearch, comboDoc;
    @FXML
    private ComboBox tab2RecorderCombo, tab2RecipientCombo;
    private ObservableList<String> typeList = FXCollections.observableArrayList("ชื่อ", "รหัส");
    private ObservableList<String> typeSearchList = FXCollections.observableArrayList("เมล็ดพันธุ์", "ผลิตภัณฑ์");
    private ObservableList<String> DocList = FXCollections.observableArrayList();
    private ObservableList<String> RecorderList = FXCollections.observableArrayList();
    private ObservableList<String> RecipientList = FXCollections.observableArrayList();
    public Stage secondaryStage = new Stage();



    public void initialize(URL location, ResourceBundle resources) {
        comboType.setItems(typeList);
        comboTypeSearch.setItems(typeSearchList);
        comboDoc.setItems(DocList);
        tab2RecorderCombo.setItems(RecorderList);
        tab2RecipientCombo.setItems(RecipientList);
    }

    @FXML
    public void handlerBtnAdd(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/popup.fxml"));
        secondaryStage.setTitle("Popup");
        secondaryStage.setScene(new Scene(root, 400, 300));
        secondaryStage.show();
    }

    @FXML
    public void handlerBtnSearch(ActionEvent e){

    }

    @FXML
    public void handlerBtnPrint(ActionEvent e){

    }
}
