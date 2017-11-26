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
import models.MarketingInfo;
import models.WarehouseProduct;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class MarketingController implements Initializable {
    @FXML private ComboBox typeCombo;
    @FXML private ComboBox unitCombo;
    @FXML private TextField amountField;
    @FXML private Button submitOrderBtn;

    Set set = new HashSet();

    ObservableList<String> productID;
    ObservableList<String> units = FXCollections.observableArrayList("ซอง", "กระป๋อง");


    private MainController controller;
    private WarehouseProduct warehouseProduct = MarketingInfo.getInstance().getWarehouseProduct();

    public void initialize(URL location, ResourceBundle resources) {
        typeCombo.setItems(comboBoxData());
        unitCombo.setItems(units);
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }

    public ObservableList<String> comboBoxData(){
        System.out.println("into");
        for (WarehouseProduct warehouseProduct: controller.getWarehouseProduct()) {
            String id = warehouseProduct.getProductId() + " : "+ warehouseProduct.getName();
            System.out.println("id+name = " + id);
            set.add(id);

            productID = FXCollections.observableArrayList(set);
        }return productID;
    }
}
