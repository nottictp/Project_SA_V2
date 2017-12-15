package managers;

import models.Farmer;
import models.Producer;
import models.Seed;

import java.util.List;
import java.util.Map;

public interface ManufactorManagerDB {

    Map<String, Double> getGroupArea();

    List<Farmer> getGroupFarmer(String group);

    List<Seed> getSeed();

    void insertIdFarmer(List<Farmer> farmers, Seed seed,int quantity);

    List<Integer> getLotIdNotQuantity();

    void insertSeedLot(List<Producer> producers, String expire, String plantDate, String harvestDate, String testDate);

    List<Producer> getProducer(int lotId);


}
