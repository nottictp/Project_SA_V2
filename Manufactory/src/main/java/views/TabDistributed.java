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
        List<Farmer> farmers = controller.getGroupFarmer(group);
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
        Seed seed = (Seed) typeCombo.getValue();
        String unit = unitCombo.getValue().toString();
        double amount = Double.parseDouble(amountField.getText());
        if(unit.equals("เมล็ด")){
            area = amount/seed.getUnitPerArea();
        }else if (unit.equals("กรัม")){
            area = amount/seed.getWeightPerUnit()/seed.getUnitPerArea();
        }else if (unit.equals("กิโลกรัม")){
            area = amount*1000/seed.getWeightPerUnit()/seed.getUnitPerArea();
        }else if (unit.equals("ตัน")){
            area = amount*1000*1000/seed.getWeightPerUnit()/seed.getUnitPerArea();
        }
        initData(search(area));
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
