package controllers;

import managers.ManufactorManagerDB;
import managers.WarehouseManagerDB;
import models.Farmer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class MainController {
    private ManufactorManagerDB executor;
    public MainController() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("manufactor_config.xml");
        executor = (ManufactorManagerDB) bf.getBean("ManufactoryDbManager");
    }

    public Map<String, Double> getGroupArea(){
        return executor.getGroupArea();
    }

    public List<Farmer> getGroupFarmer(String group){
        return executor.getGroupFarmer(group);
    }
}
