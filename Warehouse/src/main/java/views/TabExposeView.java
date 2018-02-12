package views;

import controllers.MainWarehouseController;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    @FXML private Button cancelBtn;
    @FXML private Button saveBtn;
    @FXML private TextField note;
    @FXML private TextField docNo;
    @FXML private Button addBtn;
    private List<Warehouse> wh;

    MainWarehouseController controller;
    public void setController(MainWarehouseController controller) {
        this.controller = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumn();
        docDate.setValue(LocalDate.now());
        wh = new ArrayList<Warehouse>();
        setDate();
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
        System.out.println("Add");
        Stage secondaryStage = new Stage();
        controller = new MainWarehouseController();
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
        System.out.println("Remove");
        Warehouse warehouse = (Warehouse) exposeTable.getSelectionModel().getSelectedItem();
        if (warehouse!=null){
            ButtonType foo = new ButtonType("ยืนยัน", ButtonBar.ButtonData.OK_DONE);
            ButtonType bar = new ButtonType("ยกเลิก", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "ลำดับที่ "+(wh.indexOf(warehouse)+1)+" : "+warehouse.getName(),
                    foo,
                    bar);
            alert.setTitle("ยืนยันการลบข้อมูล");
            alert.setHeaderText("คุณต้องการลบข้อมูล");
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == foo)) {
                wh.remove(warehouse);
                initData();
                System.out.println("Remove item");
            }
        }
    }

    @FXML
    public void handlerBtnCancel(ActionEvent event) throws IOException {
        System.out.println("Cancel");
        wh.clear();
        docDate.setValue(LocalDate.now());
        recorderField.clear();
        recipientField.clear();
        note.clear();
        docNo.clear();
        initData();
    }

    @FXML
    public void handlerBtnSave(ActionEvent event) throws IOException {
        for (Warehouse w : wh) {
            if(w.getType() == 1){
                //warehouse seed
                System.out.println("---expose seed---");

                String date = docDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy", Locale.ENGLISH));
                System.out.println(((WarehouseSeed) w).getSeedId());
                WarehouseSeed seed = new WarehouseSeed(w.getQuantity()*(-1),w.getShelf()
                        ,Integer.parseInt(docNo.getText()),w.getName(),w.getUnit()
                        ,date,recorderField.getText(),recipientField.getText()
                        ,"",1, ((WarehouseSeed) w).getSeedId());
                try {
                    controller.insertToWarehouseSeed(seed);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Doc number duplicates");

                    alert.showAndWait();
                }
            }else if(w.getType() == 2){
                //warehouse product
                System.out.println("---expose product---");
                String date = docDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy", Locale.ENGLISH));
                WarehouseProduct product = new WarehouseProduct(w.getQuantity()*(-1),w.getShelf()
                        ,Integer.parseInt(docNo.getText()),w.getName(),w.getUnit()
                        ,date,recorderField.getText(),recipientField.getText()
                        ,"",2, ((WarehouseProduct) w).getProductId(),0);

                controller.insertToWarehouseProduct(product);

                wh = new ArrayList<Warehouse>();
                docDate.setValue(LocalDate.now());
                recorderField.clear();
                recipientField.clear();
                note.clear();
                docNo.clear();
                initData();
            }
        }
        wh.clear();
        docDate.setValue(LocalDate.now());
        recorderField.clear();
        recipientField.clear();
        note.clear();
        docNo.clear();
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
    public void setDate(){
        docDate.setDayCellFactory(param -> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || item.isBefore(LocalDate.now()));
            }
        });
    }
}
