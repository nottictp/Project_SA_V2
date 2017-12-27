package main.java.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HomeView implements Initializable {
    @FXML private Button marketBtn;
    @FXML private Button warehouseBtn;
    @FXML private Button manuBtn;
    @FXML private Button reportBtn;
    @FXML private Label date;
    @FXML private Label nameCompany;
    @FXML private Label addressCompany;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.setText(LocalDate.now()+"");
        nameCompany.setText("Name Company");
        addressCompany.setText(".....................");
    }

    @FXML
    public void onClickMarketBtn(){
        System.out.println("Marketing Click");
    }

    @FXML
    public void onClickWarehouseBtn(){
        System.out.println("Warehouse Click");
    }

    @FXML
    public void onClickManufactureBtn(){
        System.out.println("Manufacture Click");
    }
}
