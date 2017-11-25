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
import java.util.ResourceBundle;

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

    MainController controller;
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
        int quantity = Integer.parseInt(amountField.getText());
        String unit = String.valueOf(unitCombo.getValue());
        String shelf = shelfField.getText();
        System.out.println(quantity);
        System.out.println(unit);
        System.out.println(shelf);
    }

    @FXML
    public void onClickStockNo(ActionEvent event){
        if(String.valueOf(stockCombo.getValue()).startsWith("1")){
            unitCombo.setItems(unitSeed);
            System.out.println(warehouseSeed.getSeedId());
        }else{
            unitCombo.setItems(unitProduct);
            System.out.println(warehouseProduct.getProductId());
        }
    }

    @FXML
    public void handlerBtnCancel(ActionEvent event){

    }

    public void showTable(){


    }
}
