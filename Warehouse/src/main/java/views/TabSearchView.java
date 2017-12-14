package views;

import controllers.MainController;
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
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn deliveryDateColumn;
    @FXML private TableColumn recipientColumn;
    @FXML private Button searchBtn;
    @FXML private Button printBtn;
    @FXML private ComboBox<String> typeSearchCombo;
    @FXML private ComboBox<String> typeCombo;
    @FXML private ComboBox docCombo;

    WarehouseProduct warehouseProduct;
    private List<Warehouse> wh;

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
        search = searchField.getText();
        if(typeSearh.equals("เมล็ดพันธุ์") && type.equals("ชื่อ")){
            //method getWarehouseSeedName(search)
        }else if(typeSearh.equals("เมล็ดพันธุ์") && type.equals("รหัส")){
            //method getWarehouseSeedId(search)
        }else if(typeSearh.equals("สินค้า") && type.equals("ชื่อ")){
            //method getWarehouseProductName(search)
        }else if(typeSearh.equals("สินค้า") && type.equals("รหัส")){
            //method getWarehouseProductId(search)
        }
        System.out.println(search);
    }


    public void initColumn(){
//        orderColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("order"));
        orderColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Warehouse,String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Warehouse, String> param) {
                return new SimpleStringProperty((wh.indexOf(param.getValue())+1)+"");
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
//        stockColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Warehouse,String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue call(TableColumn.CellDataFeatures<Warehouse,String> param) {
//                if (param.getValue() instanceof WarehouseSeed){
//                    return new SimpleStringProperty("1");
//                }else{
//                    return new SimpleStringProperty("2");
//                }
//            }
//        });
        recipientColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("recipient"));
        recorderColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("recorder"));
        shelfColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("shelf"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("name"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("name"));
    }

    public void initData(){
        ObservableList<Warehouse> data = FXCollections.observableList(wh);
        dataTable.setItems(data);
    }
    public void addTableView(Warehouse warehouse){
        wh.add(warehouse);
        initData();
    }
}
