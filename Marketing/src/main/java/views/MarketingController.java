package views;

import controllers.MainController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MarketingController implements Initializable {
    private MainController controller;
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setController(MainController controller) {
        this.controller = controller;
    }
}
