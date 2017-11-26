package models;

import java.io.Serializable;

public class WarehouseSeed extends Warehouse implements Serializable{
    private int seedId;

    public WarehouseSeed(int quantity, int shelf, int docNo, String name, String unit, String docDate, String recorder, String recipient, String form,int seedId) {
        super(quantity,shelf,  docNo,  name,  unit, docDate, recorder, recipient, form);
        this.seedId = seedId;
    }

    public int getSeedId() {
        return seedId;
    }

    public void setSeedId(int seedId) {
        this.seedId = seedId;
    }
}
