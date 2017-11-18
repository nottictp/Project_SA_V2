public class SeedLot {
    private int lotId, seedId;
    private String produceDate, expire,testDate,plateDate,havestDate;
    private double quality;

    public SeedLot(int lotId, int seedId, String produceDate, String expire, String testDate, String plateDate, String havestDate, double quality) {
        this.lotId = lotId;
        this.seedId = seedId;
        this.produceDate = produceDate;
        this.expire = expire;
        this.testDate = testDate;
        this.plateDate = plateDate;
        this.havestDate = havestDate;
        this.quality = quality;
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public int getSeedId() {
        return seedId;
    }

    public void setSeedId(int seedId) {
        this.seedId = seedId;
    }

    public String getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(String produceDate) {
        this.produceDate = produceDate;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public String getPlateDate() {
        return plateDate;
    }

    public void setPlateDate(String plateDate) {
        this.plateDate = plateDate;
    }

    public String getHavestDate() {
        return havestDate;
    }

    public void setHavestDate(String havestDate) {
        this.havestDate = havestDate;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }
}
