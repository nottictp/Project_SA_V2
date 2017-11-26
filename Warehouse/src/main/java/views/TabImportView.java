package views;

import controllers.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Warehouse;
import models.WarehouseProduct;
import models.WarehouseSeed;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TabImportView extends AnchorPane implements Initializable {
    @FXML private TableColumn orderColumn;
    @FXML private TableColumn idColumn;
    @FXML private TableColumn nameProductColumn;
    @FXML private TableColumn unitColumn;
    @FXML private TableColumn amountColumn;
    @FXML private TableColumn stockColumn;
    @FXML private TableColumn shelfColumn;
    @FXML private TextField recorderField;
    @FXML private TextField recipientField;
    @FXML private ComboBox departmentCombo;
    @FXML private Button cancelBtn;
    @FXML private Button saveBtn;
    @FXML private Button removeBtn;
    @FXML private TextField form;
    @FXML private TextField docNo;
    @FXML private Button addBtn;
    @FXML private TableView importTable;
    @FXML private DatePicker docDate;

    private MainController controller;
    private int orderNo = -1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumn();
    }

    @FXML
    public void handlerBtnAddTab2(ActionEvent event) throws IOException {
        Stage secondaryStage = new Stage();
        controller = new MainController();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/popupAdd.fxml"));
        AnchorPane mainLayout = loader.load();
        PopUpAddController popUpAddController = loader.getController();
        popUpAddController.setController(controller);

        secondaryStage.setTitle("รับสินค้าเข้า");
        secondaryStage.setScene(new Scene(mainLayout, 400, 300));
        secondaryStage.show();
    }

    public void initColumn(){
//        orderColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("order"));
        orderColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Integer,String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Integer, String> param) {
                orderNo+=1;
                return new SimpleStringProperty(orderNo+"");
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
        nameProductColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("name"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("quantity"));
        unitColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("unit"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("type"));
        shelfColumn.setCellValueFactory(new PropertyValueFactory<Warehouse,String>("shelf"));
    }

    public void initData(){
        System.out.println("initData ");
        System.out.println("controller = " + controller);
        List<Warehouse> wh = controller.getWarehouse();
        ObservableList<Warehouse> data = FXCollections.observableList(wh);
        importTable.setItems(data);
    }

    public void setController(MainController controller) {
        this.controller = controller;
        initData();

    }


}
