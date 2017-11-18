public class Seed {
    private int seed_ID, qualtity;
    private String name;
    private float weight_per_unit;

    public Seed(int seed_ID, int qualtity, String name, float weight_per_unit) {
        this.seed_ID = seed_ID;
        this.qualtity = qualtity;
        this.name = name;
        this.weight_per_unit = weight_per_unit;
    }

    public int getSeed_ID() {
        return seed_ID;
    }

    public void setSeed_ID(int seed_ID) {
        this.seed_ID = seed_ID;
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

    public float getWeight_per_unit() {
        return weight_per_unit;
    }

    public void setWeight_per_unit(float weight_per_unit) {
        this.weight_per_unit = weight_per_unit;
    }
}
