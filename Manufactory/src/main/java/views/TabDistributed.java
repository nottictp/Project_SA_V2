package views;

import controllers.MainManufactoryController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import models.Farmer;
import models.Seed;


import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class TabDistributed extends AnchorPane implements Initializable {

    @FXML private DatePicker datePicker;
    @FXML private TextField amountField;
    @FXML private TextField tab1RecorderTextField;
    @FXML private Label unitLabel;
    @FXML private ComboBox typeCombo;
    @FXML private Button farmerSearchBtn;
    @FXML private Button submitBtn;
    @FXML private TableView dataTable;
    @FXML private TableColumn groupColumn;
    @FXML private TableColumn tab1IDColumn;
    @FXML private TableColumn tab1nameColumn;
    @FXML private TableColumn capacityColumn;


    private ComboBox lotId;
    private MainManufactoryController controller;
    private PrintDistributedController printDistributedController;
    private ObservableList<String> units = FXCollections.observableArrayList("เมล็ด",
            "กรัม" , "กิโลกรัม", "ตัน");
    private double area;
    private List<Farmer> farmers;
    private List<Integer> lotIds;
    private Seed seed;
    private double amount;
    private int quantity;
    private TabSaveManufacture tabSave;
    private String unit;

    public void initialize(URL location, ResourceBundle resources) {
        initColumn();
        datePicker.setValue(LocalDate.now());
        lotIds = new ArrayList<>();
        printDistributedController = PrintDistributedController.getInstant();
        setDatePickerNow();
    }

    public void initColumn(){
        groupColumn.setCellValueFactory(new PropertyValueFactory<Farmer,String>("group"));
        tab1IDColumn.setCellValueFactory(new PropertyValueFactory<Farmer,String>("farmer_id"));
        tab1nameColumn.setCellValueFactory(new PropertyValueFactory<Farmer,String>("name"));

        capacityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Farmer,String>, ObservableValue<String>>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<Farmer,String> farmer) {
                    return new SimpleStringProperty(String.format("%,.2f",farmer.getValue().getCapacity_area()));
            }
        });
    }

    public void initData(List<Farmer> farmers){
        ObservableList<Farmer> data = FXCollections.observableList(farmers);
        dataTable.setItems(data);
    }
    public List<Farmer> search(Double area){
        Map<String, Double> groupArea = controller.getGroupArea();
        String group = ""; // กลุ่มที่มีพื้นที่ใกล้เคียงสุด
        Double diff1 = 100000000000.0;
        for(Map.Entry<String,Double> entry: groupArea.entrySet()){
            System.out.println("Notttt");
            System.out.println(entry.getValue()+" "+area);
            if (entry.getValue()>area){
                Double diff2 = entry.getValue()-area;
                if (diff2 < diff1){
                    group = entry.getKey();
                }
            }
            else if (entry.getValue()==area){
                group=entry.getKey();
                break;
            }
        }
        farmers = controller.getGroupFarmer(group);
        System.out.println("farmers = " + farmers);
        return farmers;
    }

    public void setController(MainManufactoryController controller){
        this.controller = controller;
        List<Seed> seeds = controller.getSeed();
        System.out.println("seeds = " + seeds);
        typeCombo.getItems().addAll(seeds);
        typeCombo.setValue(seeds.get(0));

        typeCombo.setConverter(new StringConverter() {
            @Override
            public String toString(Object object) {
                Seed seed = (Seed) object;
                return seed.getName();
            }

            @Override
            public Object fromString(String string) {
                return null;
            }
        });
    }

    @FXML
    public void searchFarmer(ActionEvent event){
        try {
            seed = (Seed) typeCombo.getValue();
            unit = "กิโลกรัม";
            amount = Double.parseDouble(amountField.getText());
            area = amount * 1000 / seed.getWeightPerUnit() / seed.getUnitPerArea();
            quantity = (int) (amount * 1000 / seed.getWeightPerUnit());
            if(amount>=0) {
                initData(search(area));
            }else{
                System.out.println("ตรวจสอบข้อมูลอีกครั้ง");
            }
        }catch (NumberFormatException e){
            System.out.println("ตรวจสอบข้อมูลอีกครั้ง");
        }
    }

    @FXML
    public void onClickSaveBtn(ActionEvent event){
        unit = "กิโลกรัม";
        amount = Double.parseDouble(amountField.getText());
        printDistributedController.setNumber(printDistributedController.getNumber() +1);
        printDistributedController.printPDF(farmers,seed.getName(),amount,unit);
        controller.insertIdFarmer(farmers,seed,quantity);
        amountField.clear();
        datePicker.setValue(LocalDate.now());
        tab1RecorderTextField.clear();
        farmers.clear();
        initData(farmers);

       // tabSave.initCombo();
    }

    public void setDatePickerNow(){
        datePicker.setDayCellFactory(param -> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || item.isBefore(LocalDate.now()));
            }
        });
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public List<Farmer> getFarmers() {
        return farmers;
    }

    public void setFarmers(List<Farmer> farmers) {
        this.farmers = farmers;
    }

    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTabSaveManufacture(TabSaveManufacture tabSaveManufacture){
        this.tabSave = tabSaveManufacture;
    }
}
