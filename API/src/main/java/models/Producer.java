package models;

import java.io.Serializable;

public class Producer implements Serializable {
    private int lot_id, farmer_id;
    private double qualtity;
    private String name;

    public Producer(int lot_id, int farmer_id, String name, double qualtity) {
        this.lot_id = lot_id;
        this.farmer_id = farmer_id;
        this.name = name;
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

    public double getQualtity() {
        return qualtity;
    }

    public void setQualtity(double qualtity) {
        this.qualtity = qualtity;
    }

    public String getName() {
        return name;
    }
}
