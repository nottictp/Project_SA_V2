package views;

import controllers.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    @FXML
    private Label errorMsg;
    private TabExposeView tabExposeView;

    MainController controller;

    ObservableList<String> shelfs = FXCollections.observableArrayList("1 : เมล็ดพันธุ์","2 : สินค้า");
    ObservableList<String> unitSeed = FXCollections.observableArrayList("เมล็ด");
    ObservableList<String> unitProduct = FXCollections.observableArrayList("ซอง", "กระป๋อง");
    ObservableList<String> seedId, productId;

    Set setA = new HashSet();
    Set setB = new HashSet();
    String stockNo, id, name, unit;
    String[] idName;
    int quantity;
    int order=1;

    public void initialize(URL location, ResourceBundle resources) {
        stockCombo.setItems(shelfs);
    }

    public void setController(MainController controller){
        this.controller = controller;
    }

    public void onClickStockNo(ActionEvent event){
        if(String.valueOf(stockCombo.getValue()).startsWith("1")){
            unitCombo.setItems(unitSeed);
            unitCombo.setValue("เมล็ด");
            for (Warehouse warehouseSeed: controller.getWarehouseSeed()) {
                String id = ((WarehouseSeed)warehouseSeed).getSeedId()+" : "+warehouseSeed.getName();
                setA.add(id);
            }
            seedId = FXCollections.observableArrayList(setA);
            idProductCombo.setItems(seedId);

        }else{
            unitCombo.setItems(unitProduct);
            unitCombo.setValue("ซอง");
            for (Warehouse warehouseProduct: controller.getWarehouseProduct()) {
                String id = ((WarehouseProduct)warehouseProduct).getProductId()+" : "+warehouseProduct.getName();
                setB.add(id);
            }
            productId = FXCollections.observableArrayList(setB);
            idProductCombo.setItems(productId);
        }
    }

    @FXML
    public void handlerBtnAdd(ActionEvent event){
        try{
            stockNo = stockCombo.getValue().toString().substring(0,1);
            quantity = Integer.parseInt(amountField.getText());
            idName = String.valueOf(idProductCombo.getValue()).split(" : ");
            id = idName[0];
            name = idName[1];
            unit = String.valueOf(unitCombo.getValue());

            if (stockNo.equals("1")){
                WarehouseSeed item = new WarehouseSeed(quantity,"",
                        0,name,
                        unit,"",
                        "","",
                        "",1,id);
                addTableView(item);
                System.out.println("Add item");
            }
            if (stockNo.equals("2")){
                WarehouseProduct item = new WarehouseProduct(quantity,"",
                        0,name,
                        unit,"",
                        "","",
                        "",2,id);
                addTableView(item);
                System.out.println("Add item");
            }
        }catch (NullPointerException e){
            errorMsg.setText("กรุณากรอกข้อมูลให้ครบถ้วน");
        }catch (NumberFormatException e){
            errorMsg.setText("ตรวจสอบข้อมูลอีกครั้ง");
        }finally {
            idProductCombo.getItems().clear();
            amountField.clear();
            unitCombo.getItems().clear();
        }

    }

    @FXML
    public void handlerBtnCancel(ActionEvent event){
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        System.out.println("Close popup");
    }



    public void addTableView(Warehouse warehouse){
        this.tabExposeView.addTableView(warehouse);
    }

    public void setTabExposeView(TabExposeView tabExposeView) {
        this.tabExposeView = tabExposeView;
    }
}
