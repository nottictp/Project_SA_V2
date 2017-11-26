package models;

import java.io.Serializable;

public class SeedRatio implements Serializable {
    private int seedId,fatherId,motherId;
    private String ratio;

    public SeedRatio(int seedId, int fatherId, int motherId, String ratio) {
        this.seedId = seedId;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.ratio = ratio;
    }

    public int getSeedId() {
        return seedId;
    }

    public void setSeedId(int seedId) {
        this.seedId = seedId;
    }

    public int getFatherId() {
        return fatherId;
    }

    public void setFatherId(int fatherId) {
        this.fatherId = fatherId;
    }

    public int getMotherId() {
        return motherId;
    }

    public void setMotherId(int motherId) {
        this.motherId = motherId;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }
}
