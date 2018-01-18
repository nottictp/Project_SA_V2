package views;

import controllers.MainWarehouseController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.Warehouse;
import models.WarehouseProduct;
import models.WarehouseSeed;

import java.net.URL;
import java.util.List;
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
    @FXML private TableColumn amountColumn;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn deliveryDateColumn;
    @FXML private TableColumn recipientColumn;
    @FXML private Button searchBtn;
    @FXML private Button printBtn;
    @FXML private ComboBox<String> typeSearchCombo;
    @FXML private ComboBox<String> typeCombo;
    @FXML private ComboBox docCombo;

    private List<Warehouse> warehouses;
    private PrintPDFController printPDFController;
    private String search;

    private MainWarehouseController controller;

    ObservableList<String> comBoBox1 = FXCollections.observableArrayList("เมล็ดพันธุ์","สินค้า");
    ObservableList<String> comBoBox2 = FXCollections.observableArrayList("ชื่อ","รหัส");
    ObservableList<String> comBoBox3 = FXCollections.observableArrayList("Lot ID");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumn();
        typeSearchCombo.setItems(comBoBox1);
        typeSearchCombo.setValue("เมล็ดพันธุ์");
        typeCombo.setItems(comBoBox2);
        typeCombo.setValue("ชื่อ");
        printPDFController = PrintPDFController.getInstant();
    }

    @FXML
    public void handlerBtnPrint(ActionEvent event){
        System.out.println("in Method Print");
        printPDFController.setNumber(printPDFController.getNumber() + 1);
        printPDFController.printPDF(warehouses);
    }

    @FXML
    public void handlerBtnSearch(ActionEvent event){
        String typeSearch = typeSearchCombo.getValue();
        System.out.println("typeSearch = " + typeSearch);
        String type = typeCombo.getValue();
        search = searchField.getText();
        System.out.println("search = " + search);
        if(typeSearch.equals("เมล็ดพันธุ์") && type.equals("ชื่อ")){
            System.out.println("controller = " + controller);
            warehouses = controller.getWarehouseSeedName(search);
        }else if(typeSearch.equals("เมล็ดพันธุ์") && type.equals("รหัส")){
            warehouses = controller.getWarehouseSeedId(search);
        }else if(typeSearch.equals("สินค้า") && type.equals("ชื่อ")){
            warehouses = controller.getWarehouseProductName(search);
        }else if(typeSearch.equals("สินค้า") && type.equals("รหัส")){
            warehouses = controller.getWarehouseProductId(search);
        }
        initData();
        System.out.println(search);
    }


    public void initColumn(){
        orderColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Warehouse,String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Warehouse, String> param) {
                return new SimpleStringProperty((warehouses.indexOf(param.getValue())+1)+"");
            }
        });
        idColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Warehouse,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<Warehouse,String> param) {
                if (param.getValue() instanceof WarehouseSeed){
                    return new SimpleStringProperty(((WarehouseSeed) param.getValue()).getSeedId()+"");
                }else{
                    return new SimpleStringProperty(((WarehouseProduct) param.getValue()).getProductId()+"");
                }
            }
        });
        deliveryDateColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("docDate"));
        docNoColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("docNo"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("unit"));
        senderColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("form"));
        recipientColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("recipient"));
        recorderColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("recorder"));
        shelfColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("shelf"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("quantity"));
    }

    public void initData(){
        ObservableList<Warehouse> data = FXCollections.observableList(warehouses);
        dataTable.setItems(data);
    }
    public void setController(MainWarehouseController controller) {
        this.controller = controller;
    }

}
