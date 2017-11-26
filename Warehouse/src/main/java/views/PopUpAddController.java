package views;

import controllers.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.WarehouseInfo;
import models.WarehouseProduct;
import models.WarehouseSeed;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class PopUpAddController implements Initializable {

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

    ObservableList<String> shelfs = FXCollections.observableArrayList("1: เมล็ดพันธุ์","2: สินค้า");
    ObservableList<String> unitProduct = FXCollections.observableArrayList("ซอง", "กระป๋อง");
    ObservableList<String> unitSeed = FXCollections.observableArrayList("เมล็ด");
    ObservableList<String> seedId, productId;

    Set setA = new HashSet();
    Set setB = new HashSet();

    String stockNo, id, unit, shelf;
    int quantity;
    int order=1;


    MainController controller;
    WarehouseController warehouse;
    WarehouseSeed warehouseSeed = WarehouseInfo.getInstance().getWarehouseSeed();
    WarehouseProduct warehouseProduct = WarehouseInfo.getInstance().getWarehouseProduct();

    public void initialize(URL location, ResourceBundle resources) {
        stockCombo.setItems(shelfs);
    }

    public void setController(MainController controller){
        this.controller = controller;
    }

    @FXML
    public void handlerBtnAdd(ActionEvent event){
        stockNo = String.valueOf(stockCombo.getValue()).substring(0,1);
        quantity = Integer.parseInt(amountField.getText());
        id = String.valueOf(idProductCombo.getValue());
        unit = String.valueOf(unitCombo.getValue());
        shelf = shelfField.getText();

    }

    @FXML
    public void onClickStockNo(ActionEvent event){
        if(String.valueOf(stockCombo.getValue()).startsWith("1")){
            unitCombo.setItems(unitSeed);
            //System.out.println(controller.getWarehouseSeed().get());
            for (WarehouseSeed warehouseSeed: controller.getWarehouseSeed()) {
                String id = warehouseSeed.getSeedId()+": "+warehouseSeed.getName();
                setA.add(id);
            }
            seedId = FXCollections.observableArrayList(setA);
            idProductCombo.setItems(seedId);

        }else{
            unitCombo.setItems(unitProduct);
            for (WarehouseProduct warehouseProduct: controller.getWarehouseProduct()) {
                String id = warehouseProduct.getProductId()+": "+warehouseProduct.getName();
                setB.add(id);
            }
            productId = FXCollections.observableArrayList(setB);
            idProductCombo.setItems(productId);
        }
    }

    @FXML
    public void handlerBtnCancel(ActionEvent event){
    }

    public void showTable(){


    }

    public String getStockNo() {
        return stockNo;
    }

    public String getId() {
        return id;
    }

    public String getUnit() {
        return unit;
    }

    public String getShelf() {
        return shelf;
    }

    public int getQuantity() {
        return quantity;
    }
}
