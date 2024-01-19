public class MovieImax3d extends MovieImax {

    public MovieImax3d(String title, int ticketsAvailable) {
        super(title, ticketsAvailable);
    }

    public float getPrice() {
        return 250.0f;
    }
}
