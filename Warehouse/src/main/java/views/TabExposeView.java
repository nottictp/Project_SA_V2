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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TabExposeView extends AnchorPane implements Initializable {
    @FXML private Button removeBtn;
    @FXML private TableColumn orderColumn;
    @FXML private TableColumn idColumn;
    @FXML private TableColumn nameProductColumn;
    @FXML private TableColumn unitColumn;
    @FXML private TableColumn amountColumn;
    @FXML private TableColumn stockColumn;
    @FXML private TableView exposeTable;
    @FXML private DatePicker docDate;
    @FXML private TextField recorderField;
    @FXML private TextField recipientField;
    @FXML private ComboBox departmentCombo;
    @FXML private Button cancelBtn;
    @FXML private Button saveBtn;
    @FXML private TextField note;
    @FXML private TextField docNo;
    @FXML private Button addBtn;
    private List<Warehouse> wh;

    MainController controller;
    public void setController(MainController controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumn();
        wh = new ArrayList<Warehouse>();
    }

    public void initColumn(){
        orderColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Warehouse,String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Warehouse, String> param) {
                return new SimpleStringProperty((wh.indexOf(param.getValue())+1)+"");
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
        stockColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Warehouse,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<Warehouse,String> param) {
                if (param.getValue() instanceof WarehouseSeed){
                    return new SimpleStringProperty("1");
                }else{
                    return new SimpleStringProperty("2");
                }
            }
        });
    }

    @FXML
    public void handlerBtnAdd(ActionEvent event) throws IOException {
        Stage secondaryStage = new Stage();
        controller = new MainController();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/popupPick.fxml"));
        AnchorPane mainLayout = loader.load();
        PopUpPickController popUpPickController = loader.getController();
        popUpPickController.setController(controller);
        popUpPickController.setTabExposeView(TabExposeView.this);

        secondaryStage.setTitle("เบิกสินค้าออก");
        secondaryStage.setScene(new Scene(mainLayout, 400, 300));
        secondaryStage.show();
    }

    @FXML
    public void handlerBtnRemove(ActionEvent event) throws IOException {
        Warehouse warehouse = (Warehouse) exposeTable.getSelectionModel().getSelectedItem();
        if (warehouse!=null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("ยืนยันการลบข้อมูล");
            alert.setHeaderText("ยืนยันการลบข้อมูล");
            String show = "ลำดับที่ "+(wh.indexOf(warehouse)+1)+" "+warehouse.getName();
            alert.setContentText(show);
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                wh.remove(warehouse);
                initData();
                System.out.println("Remove item");
            }
        }
    }

    @FXML
    public void handlerBtnCancel(ActionEvent event) throws IOException {
        wh.clear();
        initData();
    }

    public void initData(){
        ObservableList<Warehouse> data = FXCollections.observableList(wh);
        exposeTable.setItems(data);
    }
    public void addTableView(Warehouse warehouse){
        wh.add(warehouse);
        initData();
    }
}
