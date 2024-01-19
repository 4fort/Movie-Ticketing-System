public class Movie {
    private final String title;
    private int ticketsAvailable;

    public Movie(String title, int ticketsAvailable) {
        this.title = title;
        this.ticketsAvailable = ticketsAvailable;
    }

    public String getTitle() {
        return this.title;
    }

    public float getPrice() {
        return 100.0f;
    }

    public int getTicketsAvailable() {
        return this.ticketsAvailable;
    }

    void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    void getMovieDetails() {
        System.out.println("Title: " + this.title);
        System.out.println("Tickets Available: " + this.ticketsAvailable);
    }

}