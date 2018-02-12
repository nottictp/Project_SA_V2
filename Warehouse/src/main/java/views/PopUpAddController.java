package views;

import controllers.MainWarehouseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Warehouse;
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
    @FXML
    private Label errorMsg;
    @FXML
    private Label successMsg;

    @FXML ComboBox shelfCombo;
    private TabImportView tabImportView;

    ObservableList<String> shelfs = FXCollections.observableArrayList("1: เมล็ดพันธุ์","2: สินค้า");
    ObservableList<String> unitProduct = FXCollections.observableArrayList("ซอง", "กระป๋อง");
    ObservableList<String> unitSeed = FXCollections.observableArrayList("เมล็ด");
    ObservableList<String> shelfs2 = FXCollections.observableArrayList("L1","L2","L3","R1","R2","R3");
    ObservableList<String> seedId, productId;

    Set setA = new HashSet();
    Set setB = new HashSet();

    String stockNo, id, name, unit, shelf;
    String[] idName;
    int quantity;
    int order=1;


    MainWarehouseController controller;
    WarehouseController warehouse;
    WarehouseSeed warehouseSeed = WarehouseInfo.getInstance().getWarehouseSeed();
    WarehouseProduct warehouseProduct = WarehouseInfo.getInstance().getWarehouseProduct();

    public void initialize(URL location, ResourceBundle resources) {
        stockCombo.setItems(shelfs);
        shelfCombo.setItems(shelfs2);
    }

    public void setController(MainWarehouseController controller){
        this.controller = controller;
    }

    @FXML
    public void handlerBtnAdd(ActionEvent event){
        try {
            if(Integer.parseInt(amountField.getText()) > 0){
                System.out.println(stockNo);
                stockNo = stockCombo.getValue().toString().substring(0,1);
                System.out.println(stockNo);
                quantity = Integer.parseInt(amountField.getText());
                idName = String.valueOf(idProductCombo.getValue()).split(" : ");
                id = idName[0];
                name = idName[1];
                //unit = String.valueOf(unitCombo.getValue());
                unit = "กิโลกรัม";
                shelf = shelfCombo.getValue().toString();

                if (stockNo.equals("1")){
                    WarehouseSeed item = new WarehouseSeed(quantity,shelf,
                            0,name,unit,
                            "","",
                            "","",
                            1,id);
                    addTableView(item);
                    System.out.println("Add item");
                }
                if (stockNo.equals("2")){
                    WarehouseProduct item = new WarehouseProduct(quantity,shelf,
                            0,name,
                            unit,"",
                            "","",
                            "",2,id,0);
                    addTableView(item);
                    System.out.println("Add item");
                }
                successMsg.setText("เพิ่มข้อมูลเรียบร้อย");
                errorMsg.setText("");

            }else{
                successMsg.setText("");
                errorMsg.setText("ตรวจสอบข้อมูลอีกครั้ง");
            }
        }catch (NullPointerException e){
            successMsg.setText("");
            errorMsg.setText("กรุณากรอกข้อมูลให้ครบถ้วน");
        }catch (NumberFormatException e){
            successMsg.setText("");
            errorMsg.setText("ตรวจสอบข้อมูลอีกครั้ง");
        }finally {
            amountField.clear();
        }

    }

    @FXML
    public void onClickStockNo(ActionEvent event){
        if(String.valueOf(stockCombo.getValue()).startsWith("1")){
            for (Warehouse warehouseSeed: controller.getWarehouseSeed()) {

                String id = ((WarehouseSeed)warehouseSeed).getSeedId()+" : "+warehouseSeed.getName();
                setA.add(id);
            }
            seedId = FXCollections.observableArrayList(setA);
            idProductCombo.setItems(seedId);

        }else{
            for (Warehouse warehouseProduct: controller.getWarehouseProduct()) {
                String id = ((WarehouseProduct) warehouseProduct).getProductId()+" : "+warehouseProduct.getName();
                setB.add(id);
            }
            productId = FXCollections.observableArrayList(setB);
            idProductCombo.setItems(productId);
        }
    }

    @FXML
    public void handlerBtnCancel(ActionEvent event){
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        System.out.println("Close popup");
    }

    public void showTable(){
    }

    public void addTableView(Warehouse warehouse){
        this.tabImportView.addTableView(warehouse);
    }

    public void setTabImportView(TabImportView tabImportView){
        this.tabImportView = tabImportView;
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
