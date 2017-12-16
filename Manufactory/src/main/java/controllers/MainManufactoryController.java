package controllers;

import javafx.scene.control.ComboBox;
import managers.ManufactorManagerDB;
import managers.WarehouseManagerDB;
import models.Farmer;
import models.Producer;
import models.Seed;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class MainManufactoryController {

    private List<Integer> lotIds;
    private ManufactorManagerDB executor;

    public MainManufactoryController() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("manufactor_config.xml");
        executor = (ManufactorManagerDB) bf.getBean("ManufactoryDbManager");
    }

    public Map<String, Double> getGroupArea(){
        return executor.getGroupArea();
    }

    public List<Farmer> getGroupFarmer(String group){
        return executor.getGroupFarmer(group);
    }

    public List<Seed> getSeed(){
        return executor.getSeed();
    }

    public void insertIdFarmer(List<Farmer> farmers, Seed seed,int quantity){
        executor.insertIdFarmer(farmers,seed,quantity);
    }

    public List<Integer> getLotIdNotQuantity(){
        return executor.getLotIdNotQuantity();
    }

    public void insertSeedLot(List<Producer> producers, String expire, String plantDate, String harvestDate, String testDate){
        executor.insertSeedLot(producers,expire,plantDate,harvestDate,testDate);
    }

    public List<Producer> getProducer(int lotId){
        return executor.getProducer(lotId);
    }

    public List<Integer> getLotIds() {
        return lotIds;
    }

    public void setLotIds(List<Integer> lotIds) {
        this.lotIds = lotIds;
    }
}
