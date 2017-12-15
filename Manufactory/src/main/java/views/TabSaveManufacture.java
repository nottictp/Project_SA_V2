package views;

import controllers.MainManufactoryController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.SeedLot;
import models.Warehouse;

import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TabSaveManufacture  implements Initializable {

    @FXML
    private ComboBox lotNoCombo;
    @FXML
    private ComboBox recorderCombo;
    @FXML
    private TableView dataTable;
    @FXML
    private TableColumn orderColumn;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn purchaseColumn;
    @FXML
    private TableColumn unitColumn;
    @FXML
    private TableColumn expireColumn;
    @FXML
    private TableColumn plantDateColumn;
    @FXML
    private TableColumn harvestDateColumn;
    @FXML
    private TableColumn testDateColumn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;

    private MainManufactoryController controller;
    private List<SeedLot> seedLots;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
//    public void initColumn(){
//        orderColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SeedLot,String>, ObservableValue<String>>() {
//
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<SeedLot, String> seedLot) {
//                return new SimpleStringProperty((seedLots.indexOf(seedLot.getValue())+1)+"");
//            }
//        });
//        tab1IDColumn.setCellValueFactory(new PropertyValueFactory<Farmer,String>("farmer_id"));
//        tab1nameColumn.setCellValueFactory(new PropertyValueFactory<Farmer,String>("name"));
//
//        capacityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Farmer,String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue call(TableColumn.CellDataFeatures<Farmer,String> farmer) {
//                return new SimpleStringProperty(String.format("%,.2f",farmer.getValue().getCapacity_area()));
//            }
//        });
//    }

    public void setController(MainManufactoryController mainController) {
        this.controller = mainController;
    }
}
