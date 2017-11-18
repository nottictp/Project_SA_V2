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

    public TextField textFieldSearch;
    public TextField tab2DocNo;
    public TextField tab2From;
    public TextField tab3DocNo;
    public TextField tab3Note;

    public DatePicker tab2DocDate;
    public DatePicker tab3DocDate;

    public Button buttonSearch;
    public Button btnPrint;
    public Button tab2BtnAdd;
    public Button tab2BtnDelete;
    public Button tab2BtnSubmit;
    public Button tab2BtnClear;


    @FXML
    private ComboBox comboType, comboTypeSearch, comboDoc;
    @FXML
    private ComboBox tab2Recorder, tab2Recipient;
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
        tab2Recorder.setItems(RecorderList);
        tab2Recipient.setItems(RecipientList);
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
