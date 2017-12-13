package models;

import java.io.Serializable;

public class DataToMarketing implements Serializable{
    private String ratio;
    private int fatherQuantity;
    private int motherQuantity;
    private String fatherName;
    private String motherName;

    public DataToMarketing(String ratio, int fatherQuantity, int motherQuantity, String fatherName, String motherName) {
        this.ratio = ratio;
        this.fatherQuantity = fatherQuantity;
        this.motherQuantity = motherQuantity;
        this.fatherName = fatherName;
        this.motherName = motherName;
    }

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

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }
}
