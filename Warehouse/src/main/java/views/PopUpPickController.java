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
    @FXML
    private Label successMsg;
    private TabExposeView tabExposeView;

    MainWarehouseController controller;

    ObservableList<String> shelfs = FXCollections.observableArrayList("1 : เมล็ดพันธุ์","2 : สินค้า");
    ObservableList<String> unitSeed = FXCollections.observableArrayList("เมล็ด");
    ObservableList<String> unitProduct = FXCollections.observableArrayList("ซอง");
    ObservableList<String> unitProduct2 = FXCollections.observableArrayList("กระป๋อง");
    ObservableList<String> seedId, productId;

    Set setA = new HashSet();
    Set setB = new HashSet();
    String stockNo, id, name, unit;
    String[] idName, checkProduct, qualtityString;
    private int quantity;
    private int quatityInStore;
    int quantityProductInStore;
    int order=1;

    public void initialize(URL location, ResourceBundle resources) {
        stockCombo.setItems(shelfs);
    }

    public void setController(MainWarehouseController controller){
        this.controller = controller;
    }

    public void onClickStockNo(ActionEvent event){
        if(String.valueOf(stockCombo.getValue()).startsWith("1")){

            for (Warehouse warehouseSeed: controller.getWarehouseSeed()) {
                String id = ((WarehouseSeed)warehouseSeed).getSeedId()
                        +" : "+warehouseSeed.getName()
                        +" : "+warehouseSeed.getQuantity()
                        +" กิโลกรัม";
                quatityInStore = warehouseSeed.getQuantity();
                setA.add(id);
            }
            seedId = FXCollections.observableArrayList(setA);
            idProductCombo.setItems(seedId);

        }else{
            for (Warehouse warehouseProduct: controller.getWarehouseProduct()) {
                String id = ((WarehouseProduct)warehouseProduct).getProductId()
                        +" : "+warehouseProduct.getName()
                        +" : "+warehouseProduct.getQuantity()
                        +" "+warehouseProduct.getUnit();
                quatityInStore = warehouseProduct.getQuantity();
                setB.add(id);
            }
            productId = FXCollections.observableArrayList(setB);
            idProductCombo.setItems(productId);

        }
    }

    @FXML
    public void handlerBtnAdd(ActionEvent event){
        try{

            System.out.println("quatityInStore = " + quatityInStore);
            System.out.println("quantity = " + quantity);
                if(Integer.parseInt(amountField.getText()) > 0){
                    stockNo = stockCombo.getValue().toString().substring(0,1);
                    quantity = Integer.parseInt(amountField.getText());
                    idName = String.valueOf(idProductCombo.getValue()).split(" : ");
                    id = idName[0];
                    name = idName[1];
                    qualtityString = idName[2].split(" ");
                    quatityInStore=Integer.parseInt(qualtityString[0]);
                    System.out.println("q = " + quatityInStore);
                    unit = "กิโลกรัม";
                    if(quatityInStore>=quantity){
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
                                    "",2,id,0);
                            addTableView(item);
                            System.out.println("Add item");
                        }
                        successMsg.setText("เพิ่มข้อมูลเรียบร้อย");
                        errorMsg.setText("");
                    }else{
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Information Error");
//                        alert.setHeaderText(null);
//                        alert.setContentText("จำนวนที่ต้องการเบิกไม่เพียงพอ");
//
//                        alert.showAndWait();
                        successMsg.setText("");
                        errorMsg.setText("จำนวนที่ต้องการเบิกไม่เพียงพอ");
                    }

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
            System.out.println("idProductCombo = " + idProductCombo.getItems());
            amountField.clear();
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuatityInStore() {
        return quatityInStore;
    }

    public void setQuatityInStore(int quatityInStore) {
        this.quatityInStore = quatityInStore;
    }
}
