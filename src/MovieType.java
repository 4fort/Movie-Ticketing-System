public class MovieType {
    private final String type;
    private final float additionalPrice;

    public MovieType(String type, float additionalPrice) {
        this.type = type;
        this.additionalPrice = additionalPrice;
    }

    public String getType() {
        return this.type;
    }

    public float getAdditionalPrice() {
        return this.additionalPrice;
    }
}
