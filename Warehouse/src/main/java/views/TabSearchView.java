package views;

import controllers.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import models.WarehouseProduct;

import java.net.URL;
import java.util.ResourceBundle;

public class TabSearchView extends AnchorPane implements Initializable {
    @FXML private TableColumn recorderColumn;
    @FXML private TableColumn unitColumn;
    @FXML private TableColumn idColumn;
    @FXML private TableColumn shelfColumn;
    @FXML private TableView dataTable;
    @FXML private TableColumn orderColumn;
    @FXML private TextField searchField;
    @FXML private TableColumn senderColumn;
    @FXML private TableColumn capacityColumn;
    @FXML private TableColumn docNoColumn;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn deliveryDateColumn;
    @FXML private TableColumn recipientColumn;
    @FXML private Button searchBtn;
    @FXML private Button printBtn;
    @FXML private ComboBox<String> typeSearchCombo;
    @FXML private ComboBox<String> typeCombo;
    @FXML private ComboBox Doccombo;

    WarehouseProduct warehouseProduct;

    private String search;

    MainController controller;
    public void setController(MainController controller) {
        this.controller = controller;
    }

    ObservableList<String> comBoBox1 = FXCollections.observableArrayList("เมล็ดพันธุ์","สินค้า");
    ObservableList<String> comBoBox2 = FXCollections.observableArrayList("ชื่อ","รหัส");
    ObservableList<String> comBoBox3 = FXCollections.observableArrayList("Lot ID");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeSearchCombo.setItems(comBoBox1);
        typeCombo.setItems(comBoBox2);
    }

    @FXML
    public void handlerBtnPrint(ActionEvent event){    }

    @FXML
    public void handlerBtnSearch(ActionEvent event){
        String typeSearh = typeSearchCombo.getValue().toString();
        String type = typeCombo.getValue().toString();

        if(typeSearh.equals("เมล็ดพันธุ์") && type.equals("ชื่อ")){
            search = searchField.getText();

        }
        System.out.println(search);
    }


    public void initColumn(){

    }
}
