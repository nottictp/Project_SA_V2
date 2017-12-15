package views;

import controllers.MainManufactoryController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.Farmer;

import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TabDistributed extends AnchorPane implements Initializable {

    @FXML private DatePicker datePicker;
    @FXML private TextField amountField;
    @FXML private TextField tab1RecorderTextField;
    @FXML private ComboBox unitCombo;
    @FXML private ComboBox typeCombo;
    @FXML private Button farmerSearchBtn;
    @FXML private Button submitBtn;
    @FXML private TableView dataTable;
    @FXML private TableColumn groupColumn;
    @FXML private TableColumn tab1IDColumn;
    @FXML private TableColumn tab1nameColumn;
    @FXML private TableColumn capacityColumn;

    private MainManufactoryController controller;


    public void initialize(URL location, ResourceBundle resources) {
        initColumn();
    }

    public void initColumn(){
        groupColumn.setCellValueFactory(new PropertyValueFactory<Farmer,String>("group"));
        tab1IDColumn.setCellValueFactory(new PropertyValueFactory<Farmer,String>("farmer_id"));
        tab1nameColumn.setCellValueFactory(new PropertyValueFactory<Farmer,String>("name"));

        capacityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Farmer,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<Farmer,String> farmer) {
                    return new SimpleStringProperty(String.format("%,.2f",farmer.getValue().getCapacity_area()));
            }
        });
    }

    public void initData(){
        List<Farmer> farmers = search(Integer.parseInt(amountField.getText()));
        ObservableList<Farmer> data = FXCollections.observableList(farmers);
        dataTable.setItems(data);

    }
    public List<Farmer> search(int amount){
        // TODO Get All Data

        //TODO Search
        return null;
    }

    public void setController(MainManufactoryController controller){
        this.controller = controller;
    }
}
