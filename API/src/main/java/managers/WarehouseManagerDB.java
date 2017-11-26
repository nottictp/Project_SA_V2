package managers;

import models.WarehouseProduct;
import models.WarehouseSeed;

import java.util.List;

public interface WarehouseManagerDB {

    List<WarehouseSeed> getWarehouseSeed();

    List<WarehouseProduct> getWarehouseProduct();


}
