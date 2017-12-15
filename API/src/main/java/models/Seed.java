package models;

import java.io.Serializable;

public class Seed implements Serializable{
    private int seedID, qualtity;
    private String name;
    private double weightPerUnit;
    private double unitPerArea;

    public Seed(int seedID, int qualtity, String name, double weightPerUnit, double unitPerArea) {
        this.seedID = seedID;
        this.qualtity = qualtity;
        this.name = name;
        this.weightPerUnit = weightPerUnit;
        this.unitPerArea = unitPerArea;
    }

    public int getSeedID() {
        return seedID;
    }

    public void setSeedID(int seedID) {
        this.seedID = seedID;
    }

    public double getWeightPerUnit() {
        return weightPerUnit;
    }

    public void setWeightPerUnit(double weightPerUnit) {
        this.weightPerUnit = weightPerUnit;
    }

    public double getUnitPerArea() {
        return unitPerArea;
    }

    public void setUnitPerArea(double unitPerArea) {
        this.unitPerArea = unitPerArea;
    }

    public int getSeed_ID() {
        return seedID;
    }

    public void setSeed_ID(int seed_ID) {
        this.seedID = seed_ID;
    }

    public int getQualtity() {
        return qualtity;
    }

    public void setQualtity(int qualtity) {
        this.qualtity = qualtity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight_per_unit() {
        return weightPerUnit;
    }

    public void setWeight_per_unit(float weight_per_unit) {
        this.weightPerUnit = weightPerUnit;
    }
}
