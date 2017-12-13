package managers;

import models.DataToMarketing;
import models.Seed;
import models.Warehouse;
import models.WarehouseProduct;

import java.util.List;
import java.util.Map;

public interface MarketingManagerDB {



    List<Warehouse> getWarehouseProduct();

    DataToMarketing getSeedRatio(String productId);
}
