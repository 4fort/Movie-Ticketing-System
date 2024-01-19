public class MovieType {
    private final String type;
    private final float totalPrice;

    public MovieType(String type, float totalPrice) {
        this.type = type;
        this.totalPrice = totalPrice;
    }

    public String getType() {
        return type;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}
