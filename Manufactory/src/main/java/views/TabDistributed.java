package views;

import controllers.MainManufactoryController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class TabDistributed extends AnchorPane implements Initializable {

    @FXML private DatePicker datePicker;
    @FXML private TextField amountField;
    @FXML private TextField tab1RecorderTextField;
    @FXML private ComboBox unitCombo;
    @FXML private ComboBox typeCombo;
    @FXML private Button farmerSearchBtn;
    @FXML private Button submitBtn;
    @FXML private TableView dataTable;
    @FXML private TableColumn groupColumn;
    @FXML private TableColumn tab1IDColumn;
    @FXML private TableColumn tab1nameColumn;
    @FXML private TableColumn capacityColumn;

    private MainManufactoryController controller;
    private ObservableList<String> units = FXCollections.observableArrayList("เมล็ด",
            "กรัม" , "กิโลกรัม", "ตัน");
    private double area;

    public void initialize(URL location, ResourceBundle resources) {
        initColumn();
        unitCombo.setItems(units);
        unitCombo.setValue("เมล็ด");
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

    public void initData(){
        List<Farmer> farmers = search(Integer.parseInt(amountField.getText()));
        ObservableList<Farmer> data = FXCollections.observableList(farmers);
        dataTable.setItems(data);

    }
    public List<Farmer> search(int amount){
        Double area = 0.0;//คำนวณพื้นที่
        Map<String, Double> groupArea = controller.getGroupArea();
        String group = ""; // กลุ่มที่มีพื้นที่ใกล้เคียงสุด
        Double diff1 = 100000000000.0;
        for(Map.Entry<String,Double> entry: groupArea.entrySet()){
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
        List<Farmer> farmers = controller.getGroupFarmer(group);

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

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
