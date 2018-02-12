package views;

import controllers.MainManufactoryController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Producer;
import models.SeedLot;
import models.Warehouse;


import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class TabSaveManufacture  implements Initializable {

    @FXML
    private ComboBox lotNoCombo;
    @FXML
    private ComboBox recorderCombo;
    @FXML
    private TableView dataTable;
    @FXML
    private TableColumn orderColumn;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn purchaseColumn;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;

    @FXML private DatePicker expireDate;
    @FXML private DatePicker plantDate;
    @FXML private DatePicker harvestDate;
    @FXML private DatePicker testDate;


    private MainManufactoryController controller;
    private List<Producer> producers;
    private LocalDate plantDay = LocalDate.now();
    private LocalDate harvestDay = plantDay.plus(2, ChronoUnit.WEEKS);
    private LocalDate expireDay = harvestDay.plus(6,ChronoUnit.MONTHS);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumn();
        expireDate.setValue(null);
        plantDate.setValue(null);
        harvestDate.setValue(null);
        testDate.setValue(null);
        setDatePicker();
    }

    public void onDoubleClickDriver() {
        dataTable.setOnMouseClicked((MouseEvent even) -> {
            Producer producer = (Producer) dataTable.getSelectionModel().getSelectedItem();
            if (even.getClickCount() == 2 && (producer != null)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("กรอกจำนวนที่รับซื้อ");
                Label text1 = new Label("ชื่อ: ");
                Label text2 = new Label(producer.getName());
                Label text3 = new Label("จำนวนที่รับซื้อ: ");
                text1.setPrefWidth(120);
                TextField purchase = new TextField();
                purchase.setText(producer.getQualtity()+"");

                GridPane grid = new GridPane();

                grid.add(text1, 1, 1);
                grid.add(text2, 2, 1);
                grid.add(text3, 1, 2);
                grid.add(purchase, 2, 2);
                grid.setVgap(10);
                alert.getDialogPane().setContent(grid);
                Optional<ButtonType> result = alert.showAndWait();
                if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                    boolean check = false;
                    int purchaseDouble = 0;
                    if (! purchase.getText().equals("")){
                        try {
                            purchaseDouble = Integer.parseInt(purchase.getText());
                            check = true;
                        }catch (NumberFormatException e){
                            check = false;
                        }
                    }

                    if (check && purchaseDouble>=0){
                    producer.setQualtity(purchaseDouble);
                    dataTable.refresh();
                    }else{
                        System.out.println("ผิด");
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("แสดงผล");
                        alert2.setHeaderText(null);
                        alert2.setContentText("ข้อมูลไม่ถูกต้อง");
                        alert2.showAndWait();
                    }
                }
            }
        });
    }

    @FXML
    public void onActionLotNo(ActionEvent event){
        producers = controller.getProducer(Integer.parseInt(lotNoCombo.getValue().toString()));
        System.out.println("producers = " + producers);
        initData();
    }

    public void initColumn(){
        orderColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Producer,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Producer, String> produce) {
                return new SimpleStringProperty((producers.indexOf(produce.getValue())+1)+"");
            }
        });
        idColumn.setCellValueFactory(new PropertyValueFactory<Producer,String>("farmer_id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Producer,String>("name"));

        purchaseColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Producer,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<Producer,String> producer) {
                return new SimpleStringProperty(String.format("%,.2f",producer.getValue().getQualtity()));
            }
        });
    }



    public void initData(){
        ObservableList<Producer> data = FXCollections.observableList(producers);
        dataTable.setItems(data);
    }

    public void setController(MainManufactoryController mainController) {
        this.controller = mainController;
        initCombo();
        onDoubleClickDriver();

    }

    @FXML
    public void onClickSaveBtn(ActionEvent event){
        System.out.println("expireDate = " + expireDate.getValue());
        System.out.println("testDate = " + testDate.getValue());
        String eDate = expireDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy", Locale.ENGLISH));
        String pDate = plantDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy", Locale.ENGLISH));
        String hDate = harvestDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy", Locale.ENGLISH));
        String tDate = testDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yy", Locale.ENGLISH));

        try{
            int count = 0;
            for (Producer pro: producers) {
                if (pro.getQualtity() < 0) {
                    count++;
                }
            }if(count==0){
                controller.insertSeedLot(producers, eDate, pDate, hDate, tDate);
                //reset
                dataTable.getItems().clear();
                expireDate.setValue(null); //วันหมดอายุ1ปี++
                plantDate.setValue(LocalDate.now()); //วันปลูก
                harvestDate.setValue(null); //วันเก็บเกี่ยว>15วัน
                testDate.setValue(null);///วันทดสอบ<วันเก็บเกี่ยว+15
            }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("แสดงผล");
                    alert.setHeaderText(null);
                    alert.setContentText("ข้อมูลไม่ถูกต้อง");
                    alert.showAndWait();
                }

        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("แสดงผล");
            alert.setHeaderText(null);
            alert.setContentText("ข้อมูลไม่ถูกต้อง");
            alert.showAndWait();
        }


    }

    public void setDatePicker(){
        expireDate.setDayCellFactory(param -> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || item.isBefore(expireDay));
            }
        });
        plantDate.setDayCellFactory(param -> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || item.isBefore(LocalDate.now()));
            }
        });
        harvestDate.setDayCellFactory(param -> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || item.isBefore(harvestDay));
            }
        });
        testDate.setDayCellFactory(param -> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || item.isBefore(harvestDay));
            }
        });
    }

    @FXML
    public void onClickPlantDate(){
        System.out.println("click");
        System.out.println("getPlantDay() = " + getPlantDay());
        LocalDate plantDay = plantDate.getValue();
        harvestDate.setValue(plantDay.plus(2,ChronoUnit.WEEKS));
        harvestDate.setDayCellFactory(param -> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || item.isBefore(plantDay.plus(2,ChronoUnit.WEEKS)));

            }
        }
        );
    }

    @FXML
    public void onClickHarvestDate(){
        harvestDay = harvestDate.getValue();

        System.out.println("expireDate = " + expireDate);
        System.out.println("testDate = " + testDate);
        testDate.setDayCellFactory(param -> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || item.isBefore(harvestDay) || item.isAfter(harvestDay.plus(1,ChronoUnit.WEEKS)));
            }
        });
        expireDate.setDayCellFactory(param -> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || item.isBefore(harvestDay.plus(1,ChronoUnit.YEARS)));

            }
        });
    }

    @FXML
    public void onClickTestDate(){
        testDate.setDayCellFactory(param -> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || item.isBefore(harvestDay));
            }
        });
    }

    @FXML
    public void onClickExpireDate(){
        expireDate.setDayCellFactory(param -> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || item.isBefore(harvestDay));
            }
        });
    }

    @FXML
    public void onClickCancelBtn(){
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void setLotNoCombo(ComboBox lotNoCombo) {
        this.lotNoCombo = lotNoCombo;
    }

    public ComboBox getLotNoCombo() {
        return lotNoCombo;
    }

    public void initCombo(){
        lotNoCombo.getItems().addAll(controller.getLotIdNotQuantity());
    }

    public LocalDate getPlantDay() {
        return plantDay;
    }

    public void setPlantDay(LocalDate plantDay) {
        this.plantDay = plantDay;
    }

    public LocalDate getHarvestDay() {
        return harvestDay;
    }

    public void setHarvestDay(LocalDate harvestDay) {
        this.harvestDay = harvestDay;
    }

    public LocalDate getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(LocalDate expireDay) {
        this.expireDay = expireDay;
    }
}
