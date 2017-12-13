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
import models.Warehouse;
import models.WarehouseProduct;
import models.WarehouseSeed;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class PopUpPickController implements Initializable{
    @FXML
    private ComboBox idProductCombo;
    @FXML
    private TextField amountField;
    @FXML
    private ComboBox unitCombo;
    @FXML
    private ComboBox stockCombo;
    @FXML
    private Button addBtn;
    @FXML
    private Button cancelBtn;

    MainController controller;

    ObservableList<String> shelfs = FXCollections.observableArrayList("1: เมล็ดพันธุ์","2: สินค้า");
    ObservableList<String> unitSeed = FXCollections.observableArrayList("เมล็ด");
    ObservableList<String> unitProduct = FXCollections.observableArrayList("ซอง", "กระป๋อง");
    ObservableList<String> seedId, productId;

    Set setA = new HashSet();
    Set setB = new HashSet();

    public void initialize(URL location, ResourceBundle resources) {
        stockCombo.setItems(shelfs);
    }

    public void setController(MainController controller){
        this.controller = controller;
    }

    public void onClickStockNo(ActionEvent event){
        if(String.valueOf(stockCombo.getValue()).startsWith("1")){
            unitCombo.setItems(unitSeed);
            //System.out.println(controller.getWarehouseSeed().get());
            for (Warehouse warehouseSeed: controller.getWarehouseSeed()) {
                String id = ((WarehouseSeed)warehouseSeed).getSeedId()+": "+warehouseSeed.getName();
                setA.add(id);
            }
            seedId = FXCollections.observableArrayList(setA);
            idProductCombo.setItems(seedId);

        }else{
            unitCombo.setItems(unitProduct);
            for (Warehouse warehouseProduct: controller.getWarehouseProduct()) {
                String id = ((WarehouseProduct)warehouseProduct).getProductId()+": "+warehouseProduct.getName();
                setB.add(id);
            }
            productId = FXCollections.observableArrayList(setB);
            idProductCombo.setItems(productId);
        }
    }

    @FXML
    public void handlerBtnAdd(ActionEvent event){

    }
}