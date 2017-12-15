package models;

import java.io.Serializable;

public class WarehouseSeed extends Warehouse implements Serializable{
    private String seedId;

    public WarehouseSeed(int quantity, String shelf,
                         int docNo, String name,
                         String unit, String docDate,
                         String recorder, String recipient,
                         String form,int type,String seedId) {
        super(quantity,shelf,  docNo,  name,  unit, docDate, recorder, recipient, form,type);
        this.seedId = seedId;
    }

    public String getSeedId() {
        return seedId;
    }

    public void setSeedId(String seedId) {
        this.seedId = seedId;
    }
}
