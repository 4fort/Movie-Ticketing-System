import java.util.Scanner;

public class Main {
    static final MovieType[] movieTypes = {
            new MovieType("IMAX", 100),
            new MovieType("3D", 200),
            new MovieType("IMAX 3D", 250)
    };
    static Scanner scanner = new Scanner(System.in);
    static TransactionHandler transactions;
    static Movie[] movies = {
            new Movie("Interstellar", 200, 25),
            new Movie("The Matrix", 300, 30),
            new Movie("The Godfather", 380, 80),
            new Movie("The Joker", 250, 20),
            new Movie("If Only", 400, 15)
    };

    public static void main(String[] args) {
        boolean isContinue = true;
        int movieChoice;
        int movieTypeChoice;

        while (isContinue) {
            movieChoice = movieSelector();
            switch (movieChoice) {
                case 0:
                    System.out.println("Bye!");
                    isContinue = false;
                    break;
                case 1:
                    if (movies[movieChoice - 1].getTicketsAvailable() > 0) {
                        System.out.println("Tickets sold out! Please select another movie.");
                        break;
                    }
                    System.out.println("Tickets available!");
                    System.out.println("Please select a movie type:");
                    movieTypeChoice = movieTypeSelector();
                    transactions.setTotalAmount(movies[movieChoice - 1].getPrice() + movieTypes[movieTypeChoice - 1].getAdditionalPrice());

                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    public static int movieSelector() {
        printMovieList();
        System.out.println("[0] Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public static void printMovieList() {
        System.out.println("What would you like to watch?");
        for (int i = 0; i < movies.length; i++) {
            System.out.println("[" + (i + 1) + "] " + movies[i].getTitle() + " - Php" + movies[i].getPrice() + " (" + movies[i].getTicketsAvailable() + " available)");
        }
    }

    public static int movieTypeSelector() {
        printMovieTypes();
        System.out.println("[0] Return");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public static void printMovieTypes() {
        for (int i = 0; i < movieTypes.length; i++) {
            System.out.println("[" + (i + 1) + "] " + movieTypes[i] + " (additional price: Php" + movieTypes[i].getAdditionalPrice() + ")");
        }
    }
}