package controllers;

import java.net.URL;
import java.util.ResourceBundle;

public class MainHomeController {
    private MainHomeController controller;

    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setController(MainHomeController controller) {
        this.controller = controller;
    }

    public MainHomeController getController() {
        return controller;
    }
}
