package views;

import entry.MainManufactory;
import entry.MainMarketing;
import entry.MainWarehouse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import controllers.MainHomeController;

import java.io.IOException;
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
    private MainHomeController controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.setText(LocalDate.now()+"");
        nameCompany.setText("Name Company");
        addressCompany.setText(".....................");
    }

    @FXML
    public void onClickMarketBtn() throws IOException {
        System.out.println("Marketing Click");
        Stage stage = new Stage();
        MainMarketing marketing = new MainMarketing();
        try {
            marketing.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClickWarehouseBtn(){
        System.out.println("Warehouse Click");
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("warehouse.fxml"));
//            Parent root1 = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root1));
//            stage.show();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
        Stage stage = new Stage();
        MainWarehouse warehouse = new MainWarehouse();
        try {
            warehouse.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @
       FXML
    public void onClickManufactureBtn(){

   System.out.println("Manufacture Click");

   Stage stage = new Stage();
   MainManufactory manufactory = new MainManufactory();
        try {
            manufactory.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onClickReport(){
        System.out.println("Report Click");
    }

    public MainHomeController getController() {
        return controller;
    }

    public void setController(MainHomeController controller) {
        this.controller = controller;
    }

    public Button getMarketBtn() {
        return marketBtn;
    }

    public void setMarketBtn(Button marketBtn) {
        this.marketBtn = marketBtn;
    }

    public Button getWarehouseBtn() {
        return warehouseBtn;
    }

    public void setWarehouseBtn(Button warehouseBtn) {
        this.warehouseBtn = warehouseBtn;
    }

    public Button getManuBtn() {
        return manuBtn;
    }

    public void setManuBtn(Button manuBtn) {
        this.manuBtn = manuBtn;
    }

    public Button getReportBtn() {
        return reportBtn;
    }

    public void setReportBtn(Button reportBtn) {
        this.reportBtn = reportBtn;
    }

    public Label getDate() {
        return date;
    }

    public void setDate(Label date) {
        this.date = date;
    }

    public Label getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(Label nameCompany) {
        this.nameCompany = nameCompany;
    }

    public Label getAddressCompany() {
        return addressCompany;
    }

    public void setAddressCompany(Label addressCompany) {
        this.addressCompany = addressCompany;
    }
}
