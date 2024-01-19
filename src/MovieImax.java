public class MovieImax extends Movie {

    public MovieImax(String title, int ticketsAvailable) {
        super(title, ticketsAvailable);
    }

    public float getPrice() {
        return 200.0f;
    }
}
