package models;

import java.io.Serializable;

public class Producer implements Serializable {
    private int lot_id, farmer_id;
    private float qualtity;

    public Producer(int lot_id, int farmer_id, float qualtity) {
        this.lot_id = lot_id;
        this.farmer_id = farmer_id;
        this.qualtity = qualtity;
    }

    public int getLot_id() {
        return lot_id;
    }

    public void setLot_id(int lot_id) {
        this.lot_id = lot_id;
    }

    public int getFarmer_id() {
        return farmer_id;
    }

    public void setFarmer_id(int farmer_id) {
        this.farmer_id = farmer_id;
    }

    public float getQualtity() {
        return qualtity;
    }

    public void setQualtity(float qualtity) {
        this.qualtity = qualtity;
    }
}
