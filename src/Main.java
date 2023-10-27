import java.util.Scanner;

public class Main {
    public static final String ANSI_RED_BG = "\u001B[41m";
    public static final String ANSI_GREEN = "\u001B[42m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static final MovieType[] MOVIE_TYPES = {
            new MovieType("IMAX", 100),
            new MovieType("3D", 200),
            new MovieType("IMAX 3D", 250)
    };
    public static Scanner scanner = new Scanner(System.in);
    public static TransactionHandler transactions = new TransactionHandler();
    public static Movie[] movies = {
            new Movie("Interstellar", 200, 25),
            new Movie("The Matrix", 300, 30),
            new Movie("The Godfather", 380, 80),
            new Movie("The Joker", 250, 20),
            new Movie("If Only", 400, 15)
    };

    public static void main(String[] args) {
        boolean isContinue = true;
        int movieChoice;
        int movieTypeChoice = 0;
        int ticketCount = 0;
        double change = 0;

        while (isContinue) {
            movieChoice = movieSelector();
            if (movieChoice == 0) {
                System.out.println("Bye!");
                isContinue = false;
            } else if (movieChoice > movies.length && movieChoice < movies.length + 1) {
                System.out.println(ANSI_RED_BG + "Invalid option!" + ANSI_RESET);
            } else {
                if (movies[movieChoice - 1].getTicketsAvailable() <= 0) {
                    System.out.println(ANSI_RED_BG + "Tickets sold out! Please select another movie." + ANSI_RESET);
                    continue;
                }
                System.out.println(ANSI_GREEN + "Tickets available!" + ANSI_RESET);
                while (true) {
                    System.out.println("Please select a movie type:");
                    movieTypeChoice = movieTypeSelector();
                    if (movieTypeChoice > MOVIE_TYPES.length) continue;
                    break;
                }
                if (movieTypeChoice == 0) continue;

                System.out.println("How many tickets would you like to purchase?:");
                while (true) {
                    ticketCount = scanner.nextInt();
                    if (ticketCount > movies[movieChoice - 1].getTicketsAvailable()) {
                        System.out.println(ANSI_RED_BG + movies[movieChoice - 1].getTicketsAvailable() + " tickets are left!" + ANSI_RESET + " Please input the right number of tickets.");
                        ticketCount = 0;
                        continue;
                    }
                    break;
                }
                movies[movieChoice - 1].setTicketsAvailable(movies[movieChoice - 1].getTicketsAvailable() - ticketCount);
                transactions.setTotalAmount((movies[movieChoice - 1].getPrice() + MOVIE_TYPES[movieTypeChoice - 1].getAdditionalPrice()) * ticketCount);

                System.out.println("Total amount: Php" + transactions.getTotalAmount());
                System.out.println("Input amount to pay: Php");
                while (change <= 0) {
                    change = transactions.makePayment(scanner.nextDouble());
                }

                System.out.println("Change: Php" + change);
                System.out.println(ANSI_GREEN + "Thank you for your purchase!" + ANSI_RESET);
                change = 0;
                transactions.setTotalAmount(0);
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
        for (int i = 0; i < MOVIE_TYPES.length; i++) {
            System.out.println("[" + (i + 1) + "] " + MOVIE_TYPES[i].getType() + " (additional price: Php" + MOVIE_TYPES[i].getAdditionalPrice() + ")");
        }
    }
}