package managers;

import models.Farmer;

import java.util.List;
import java.util.Map;

public interface ManufactorManagerDB {

    Map<String, Double> getGroupArea();

    List<Farmer> getGroupFarmer(String group);
}
