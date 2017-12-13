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
import models.DataToMarketing;
import models.MarketingInfo;
import models.Warehouse;
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
    String id;
    private int quantity;//quantity in program
    private int fatherAmount;
    private int motherAmount;
    private int childAmount;

    private MainController controller;
    private WarehouseProduct warehouseProduct = MarketingInfo.getInstance().getWarehouseProduct();

    public void initialize(URL location, ResourceBundle resources) {
        if (controller != null){
            typeCombo.setItems(comboBoxData());
            unitCombo.setItems(units);
        }
    }

    public void setController(MainController controller) {
        this.controller = controller;
        if (typeCombo != null){
            typeCombo.setItems(comboBoxData());
            unitCombo.setItems(units);
        }
    }

    public ObservableList<String> comboBoxData(){
        System.out.println("get data into comboBox");
        for (Warehouse warehouse: controller.getWarehouseProduct()) {
            String id = ((WarehouseProduct) warehouse).getProductId()
                    + " : "+ ((WarehouseProduct) warehouse).getName();
            System.out.println("id+name = " + id);
            set.add(id);

            productID = FXCollections.observableArrayList(set);
        }return productID;
    }

    @FXML
    public void handlerBtnManufacture(){
        quantity = Integer.parseInt(amountField.getText());
        String unit = String.valueOf(unitCombo.getValue());
        String[] idName = String.valueOf(typeCombo.getValue()).split(" : ");
        id = idName[0];
        String name = idName[1];
        checkAmountOfSeed(id);
    }

    public void checkAmountOfSeed(String id){
        System.out.println("con"+controller);
        DataToMarketing seedRatio = controller.getSeedRatio(id);
        String ratio = seedRatio.getRatio();
        int totalFather = seedRatio.getFatherQuantity();
        int totalMother = seedRatio.getMotherQuantity();
        String[] ratios = ratio.split(":");
        childAmount = Integer.parseInt(ratios[2]);
        fatherAmount = Integer.parseInt(ratios[0]);//ratio to want
        motherAmount = Integer.parseInt(ratios[1]);//ratio to want
        int count = quantity / childAmount;
        int checkFather = fatherAmount * count;
        int checkMother = motherAmount * count;



        if(totalFather >= checkFather && totalMother >= checkMother){
            System.out.println("OK!!");
        }if(totalFather < checkFather && totalMother >= checkMother){
            System.out.println("Father not enough.");
            System.out.println("It need "+(totalFather-checkFather));
        }if(totalFather >= checkFather && totalMother < checkMother){
            System.out.println("Mother not enough.");
            System.out.println("It need "+(totalMother-checkMother));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
