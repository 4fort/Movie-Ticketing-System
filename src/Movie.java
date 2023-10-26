public class Movie {
    private final String title;
    private final float price;
    private int ticketsAvailable;

    public Movie(String title, float price, int ticketsAvailable) {

        this.title = title;
        this.price = price;
        this.ticketsAvailable = ticketsAvailable;
    }

    public String getTitle() {
        return this.title;
    }

    public float getPrice() {
        return this.price;
    }

    public int getTicketsAvailable() {
        return this.ticketsAvailable;
    }
    void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

}