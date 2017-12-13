package models;

import java.io.Serializable;

public class DataToMarketing implements Serializable{
    private String ratio;
    private int fatherQuantity;
    private int motherQuantity;

    public DataToMarketing(String ratio, int fatherQuantity, int motherQuantity) {
        this.ratio = ratio;
        this.fatherQuantity = fatherQuantity;
        this.motherQuantity = motherQuantity;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public int getFatherQuantity() {
        return fatherQuantity;
    }

    public void setFatherQuantity(int fatherQuantity) {
        this.fatherQuantity = fatherQuantity;
    }

    public int getMotherQuantity() {
        return motherQuantity;
    }

    public void setMotherQuantity(int motherQuantity) {
        this.motherQuantity = motherQuantity;
    }
}
