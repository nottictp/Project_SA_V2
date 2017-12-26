package views;

import controllers.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.DataToMarketing;
import models.MarketingInfo;
import models.Warehouse;
import models.WarehouseProduct;

import java.net.URL;
import java.util.*;

public class MarketingController implements Initializable {
    @FXML private ComboBox typeCombo;
    @FXML private ComboBox unitCombo;
    @FXML private TextField amountField;
    @FXML private Button submitOrderBtn;

    Set set = new HashSet();

    ObservableList<String> productID;
    ObservableList<String> units = FXCollections.observableArrayList("เมล็ด");
    String id,name,unit;
    private int quantity;//quantity in program
    private int fatherAmount;
    private int motherAmount;
    private int childAmount;
    private Map<String, String> product;
    private Map<String, Integer> product2;
    private Map<String, String> product3;
    private List<String> sortName;
    private List<String> nameIdQuan;
    private MainController controller;
    private WarehouseProduct warehouseProduct = MarketingInfo.getInstance().getWarehouseProduct();
    private PrintController printController;

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("initialize");
        if (controller != null){
            typeCombo.setItems(comboBoxData());
            unitCombo.setItems(units);
            printController = PrintController.getInstant();
        }
        sortName = new ArrayList<>();
        product = new HashMap<>();
        product2 = new HashMap<>();
        product3 = new HashMap<>();
        nameIdQuan = new ArrayList<>();
    }

    public void setController(MainController controller) {
        this.controller = controller;
        if (typeCombo != null){
            typeCombo.setItems(comboBoxData());
            unitCombo.setItems(units);
            printController = PrintController.getInstant();
            printController.setMainController(controller);
        }
    }

    public ObservableList<String> comboBoxData(){
        System.out.println("get data into comboBox");
        for (Warehouse warehouse: controller.getWarehouseProduct()) {
            String id = ((WarehouseProduct) warehouse).getProductId();
            String name = ((WarehouseProduct) warehouse).getName();
            int quantity = ((WarehouseProduct) warehouse).getQuantity();
            String unit = warehouse.getUnit();
            product.put(name, id);
            product3.put(name, unit);
            if(!sortName.contains(name)){
                sortName.add(name);
                product2.put(name, quantity);
            }else{
                product2.put(name, product2.get(name)+quantity);
            }
            System.out.println("id+name = " + id+name);
        }
        Collections.sort(sortName);
        for (String s: sortName) {
            nameIdQuan.add(product.get(s)+" : "+s+" คงเหลือ: "+product2.get(s)+" "+product3.get(s));
        }
        productID = FXCollections.observableArrayList(nameIdQuan);
        return productID;
    }

    @FXML
    public void handlerBtnManufacture(){
        try {
            if(amountField.getText() != null && unitCombo.getValue() != null && typeCombo.getValue() != null){
                quantity = Integer.parseInt(amountField.getText());
                unit = String.valueOf(unitCombo.getValue());
                String[] idName = String.valueOf(typeCombo.getValue()).split(" : ");
                id = idName[0];
                name = idName[1];
                if(quantity > 0){
                    checkAmountOfSeed(id);
                }else{
                    System.out.println("error");
                }
            }else{
                System.out.println("please select");
            }

        }catch (NumberFormatException e){
            System.out.println("error");
        }
    }

    public void checkAmountOfSeed(String id){
        System.out.println("con "+controller);
        DataToMarketing seedRatio = controller.getSeedRatio(id);
        String ratio = seedRatio.getRatio();
        int totalFather = seedRatio.getFatherQuantity();
        int totalMother = seedRatio.getMotherQuantity();
        String nameFather = seedRatio.getFatherName();
        String nameMother = seedRatio.getMotherName();
        String[] ratios = ratio.split(":");
        childAmount = Integer.parseInt(ratios[2]);
        fatherAmount = Integer.parseInt(ratios[0]);//ratio to want
        motherAmount = Integer.parseInt(ratios[1]);//ratio to want
        int count = quantity / childAmount;
        int checkFather = fatherAmount * count;
        int checkMother = motherAmount * count;

        if(totalFather >= checkFather && totalMother >= checkMother){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("แสดงผล");
            alert.setHeaderText(null);
            alert.setContentText("วัตถุดิบเพียงพอในการผลิต");
            alert.showAndWait();
            System.out.println("id = " + id);
            System.out.println("name = " + name);
            System.out.println("unit = " + unit);
            System.out.println("quantity = " + quantity);
            System.out.println(printController);
            printController.setNumber(printController.getNumber() + 1);
            printController.printManufactureScript(id,name,unit,quantity);
            System.out.println("OK!!");
        }else if(totalFather < checkFather && totalMother >= checkMother){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("แสดงผล");
            alert.setHeaderText(null);
            alert.setContentText(nameFather+" ไม่พอ ขาด: "+(totalFather-checkFather)*(-1));
            alert.showAndWait();
            System.out.println("Father not enough.");
            System.out.println("It need "+(totalFather-checkFather)*(-1));
        }else if(totalFather >= checkFather && totalMother < checkMother){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("แสดงผล");
            alert.setHeaderText(null);
            alert.setContentText(nameMother+" ไม่พอ ขาด: "+(totalMother-checkMother)*(-1));
            alert.showAndWait();
            System.out.println("Mother not enough.");
            System.out.println("It need "+(totalMother-checkMother)*(-1));
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("แสดงผล");
            alert.setHeaderText(null);
            alert.setContentText(
                    nameFather+" ไม่พอ ขาด: "+(totalFather-checkFather)*(-1)+"\n"+
                    nameMother+" ไม่พอ ขาด: "+(totalMother-checkMother)*(-1));
            alert.showAndWait();
            System.out.println("Father and Mother not enough");
            System.out.println("Father need "+(totalFather-checkFather)*(-1));
            System.out.println("It need "+(totalMother-checkMother)*(-1));
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
