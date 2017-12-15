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
import javafx.util.Callback;
import models.Producer;
import models.SeedLot;
import models.Warehouse;


import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColumn();
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
                    Double purchaseDouble = 0.0;
                    if (! purchase.getText().equals("")){
                        try {
                            purchaseDouble = Double.parseDouble(purchase.getText());
                            check = true;
                        }catch (NumberFormatException e){
                            check = false;
                        }
                    }

                    if (check){
                    producer.setQualtity(purchaseDouble);
                    dataTable.refresh();
                    }else{
                        System.out.println("ผิด");
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
        onDoubleClickDriver();
        lotNoCombo.getItems().addAll(controller.getLotIdNotQuantity());
    }

}
