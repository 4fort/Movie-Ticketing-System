import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    //  !!!  CONSTANT DECLARATIONS   !!!

    //  ANSI ESCAPE CODES FOR TERMINAL COLORS
    public static final String ANSI_RED_BG = "\u001B[41m";
    public static final String ANSI_GREEN_BG = "\u001B[42m";
    public static final String ANSI_BLUE_BG = "\u001B[44m";
    public static final String ANSI_GREY_DARK_BG = "\u001b[48;5;237m\u001b[38;5;255m";
    public static final String ANSI_GREY_LIGHT_BG = "\u001b[48;5;250m\u001b[38;5;237m";
    public static final String ANSI_BOLD = "\033[0;1m";
    public static final String ANSI_RESET = " \u001B[0m";
    // ANSI COMBINATIONS
    public static final String ANSI_ALERT_RED = ANSI_RED_BG + " " + ANSI_BOLD + ANSI_GREY_DARK_BG + " ";
    public static final String ANSI_ALERT_GREEN = ANSI_GREEN_BG + " " + ANSI_BOLD + ANSI_GREY_DARK_BG + " ";
    public static final String ANSI_ALERT_BLUE_OPEN = ANSI_BLUE_BG + " " + ANSI_BOLD + ANSI_GREY_DARK_BG + " ";
    public static final String ANSI_ALERT_BLUE_CLOSE = " " + ANSI_BLUE_BG + ANSI_RESET;
    public static final String ANSI_LIST = ANSI_BOLD + ANSI_GREY_LIGHT_BG;
    public static final String ANSI_RECEIPT = ANSI_BOLD + ANSI_GREY_LIGHT_BG;

    //  MOVIE TYPES OBJECT ARRAY
    private static final MovieType[] MOVIE_TYPES = {
            new MovieType("regular", 0),
            new MovieType("imax", 100),
            new MovieType("3d", 200),
            new MovieType("imax 3d", 250)
    };
    private static final TransactionHandler transactions = new TransactionHandler();   // handles transactions made
    private static final ServiceRater serviceRater = new ServiceRater();   // handles service rating
    //  MOVIES OBJECT ARRAY
    private static final Movie[] movies = {
            new Movie("Interstellar", 200, 25),
            new Movie("The Matrix", 300, 30),
            new Movie("The Godfather", 380, 80),
            new Movie("The Joker", 250, 20),
            new Movie("If Only", 400, 15)
    };
    //  UTILITY FUNCTIONS
    static Scanner scanner = new Scanner(System.in);
    static NumberFormat nf = NumberFormat.getInstance();

    public static void main(String[] args) {
        //  DECLARATIONS OF VARIABLES
        boolean isContinue = true;  // USED FOR WHILE LOOP CONDITION
        int movieChoice;    // USED FOR $movies[] (1-5) CHOICE USER INPUT
        int movieTypeChoice; // USED FOR $MOVIE_TYPE[] (1-3) CHOICE USER INPUT
        int ticketCount;    // USED FOR AMOUNT OF TICKETS USER WILL BUY
        double payment;   // USED FOR USER PAYMENT
        double change;  // USED FOR PAYMENT CHANGE

        // while loop for the whole program
        while (isContinue) {
            // MAIN MENU
            // try catch block for user input validation. Must be between 0-5 otherwise program will throw error and repeat loop
            try {
                movieChoice = movieSelector();  // returns user input (0-5)
                if (movieChoice == 0) { // if user input is 0 program will exit
                    System.out.println("Bye!");
                    isContinue = false;
                } else if (movieChoice <= movies.length) {    // else if user input is 1-5, program will continue
                    // checks if movie tickets is less than or equal to 0 then program will repeat loop
                    if (movies[movieChoice - 1].getTicketsAvailable() <= 0) {
                        System.out.println(ANSI_ALERT_RED + "Tickets sold out! Please select another movie." + ANSI_RESET);
                        continue;
                    }
                    // if above condition is false then program will continue
                    System.out.println(ANSI_ALERT_GREEN + "Tickets available!" + ANSI_RESET);
                    while (true) {  // while loop for user input validation that must be between 1-3
                        try {   // try catch block for user input validation
                            movieTypeChoice = movieTypeSelector();
                        } catch (InputMismatchException e) {
                            printInvalidInput();
                            scanner.nextLine(); // clears scanner input
                            continue;
                        }
                        if (movieTypeChoice > MOVIE_TYPES.length) { // if user input is not between 1-3, program will repeat loop
                            System.out.println(ANSI_ALERT_RED + "Select from the following only!" + ANSI_RESET);

                            continue;
                        }
                        break;  // if user input is between 1-3, program will continue
                    }
                    if (movieTypeChoice == 0)
                        continue; // if user input is 0, program go back to main menu (previous loop)

                    while (true) {  // while loop for user input validation
                        System.out.println("\n\n");
                        printReturnToMenu();
                        try {
                            System.out.print(ANSI_ALERT_BLUE_OPEN + "How many tickets would you like to purchase? (" + movies[movieChoice - 1].getTicketsAvailable() + " tickets available):" + ANSI_RESET);

                            ticketCount = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            printInvalidInput();
                            scanner.nextLine();
                            continue;
                        }
                        // if user input is more than the available tickets, program will repeat current loop
                        if (ticketCount > movies[movieChoice - 1].getTicketsAvailable()) {
                            System.out.println(ANSI_ALERT_RED + movies[movieChoice - 1].getTicketsAvailable() + " tickets are left!" + " Please input the right number of tickets." + ANSI_RESET);
                            continue;
                        }
                        break;
                    }
                    if (ticketCount == 0) continue; // if user inputs 0, it will return to menu

                    // calculates overall amount of selected movie plus the movie type additional price
                    transactions.setTotalAmount((movies[movieChoice - 1].getPrice() + MOVIE_TYPES[movieTypeChoice - 1].additionalPrice()) * ticketCount);

                    while (true) {   // while loop for user input validation.
                        System.out.println("\n\n");
                        printReturnToMenu();
                        try {   // try catch block for user input validation
                            System.out.println(ANSI_ALERT_BLUE_OPEN + "Total amount: " + nf.format(transactions.getTotalAmount()) + ANSI_ALERT_BLUE_CLOSE);
                            System.out.print(ANSI_ALERT_BLUE_OPEN + "Input amount to pay: " + ANSI_RESET + "Php");
                            payment = scanner.nextDouble();
                            // if $payment is not 0 and $payment is greater than the $totalAmount, program continues with payment and sets change
//                            if (payment >= transactions.getTotalAmount()) {
//                                change = transactions.makePayment(payment);
//                                break;
//                            }
                            // if $payment is less than $totalAmount, program will repeat loop
//                            else if (payment < transactions.getTotalAmount()) {
//                                System.out.println(ANSI_ALERT_RED + "Payment unsuccessful! Please input a proper amount." + ANSI_RESET);
//                            }
                            change = transactions.makePayment(payment);
                            if (transactions.isSuccess()) break;
                            else if (payment == 0) break;
                        } catch (InputMismatchException e) {
                            printInvalidInput();
                            scanner.nextLine();
                        }
                    }

                    if (transactions.isSuccess()) {
                        // deducts available tickets of selected movie from user purchase. ($movie.getTicketsAvailable() - $ticketCount)
                        movies[movieChoice - 1].setTicketsAvailable(movies[movieChoice - 1].getTicketsAvailable() - ticketCount);

                        // prints receipt
                        printReceipt(ticketCount, movieChoice, movieTypeChoice, transactions.getTotalAmount(), payment, change);
                        transactions.setTotalAmount(0);
                    }
                } else {
                    System.out.println(ANSI_ALERT_RED + "Select from the following only!" + ANSI_RESET);
                }
                serviceRater.printRateService();
            } catch (InputMismatchException e) {
                printInvalidInput();
                scanner.nextLine();
            }
        }
    }

    // PRINTS A LIST OF MOVIES AND RETURNS USER INPUT
    static int movieSelector() {
        System.out.println("\n\nWhat would you like to watch?");
        if (serviceRater.getServiceRatings() != 0) {
            serviceRater.printServiceRatings();
        }
        printMovieList();
        System.out.println(ANSI_BOLD + ANSI_RED_BG + "[0]" + ANSI_GREY_DARK_BG + " Exit" + ANSI_RESET);
        System.out.print(ANSI_ALERT_BLUE_OPEN + "Enter your choice:" + ANSI_RESET);
        return scanner.nextInt();
    }

    // PRINTS A LIST OF MOVIES
    static void printMovieList() {
        for (int i = 0; i < movies.length; i++) {
            System.out.println(ANSI_LIST + "[" + (i + 1) + "]" + ANSI_GREY_DARK_BG + " " + movies[i].getTitle() + " - Php" + movies[i].getPrice() + " (" + movies[i].getTicketsAvailable() + " available)" + ANSI_RESET);
        }
    }

    // PRINTS A LIST OF MOVIE TYPES AND RETURNS USER INPUT
    static int movieTypeSelector() {
        System.out.println("\n\nPlease select a movie type:");
        printMovieTypes();
        printReturnToMenu();
        System.out.print(ANSI_ALERT_BLUE_OPEN + "Enter your choice:" + ANSI_RESET);
        return scanner.nextInt();
    }

    // PRINTS A LIST OF MOVIE TYPES
    static void printMovieTypes() {
        for (int i = 0; i < MOVIE_TYPES.length; i++) {
            System.out.println(ANSI_LIST + "[" + (i + 1) + "]" + ANSI_GREY_DARK_BG + " " + MOVIE_TYPES[i].type().toUpperCase() + " (additional price: Php" + MOVIE_TYPES[i].additionalPrice() + ")" + ANSI_RESET);
        }
    }

    // PRINTS INVALID INPUT
    static void printInvalidInput() {
        System.out.println(ANSI_ALERT_RED + "Invalid input! Please try again." + ANSI_RESET);
    }

    static void printReturnToMenu() {
        System.out.println(ANSI_BOLD + ANSI_RED_BG + "[0]" + ANSI_GREY_DARK_BG + " Return to movie selection" + ANSI_RESET);
    }

    static void printReceipt(int tickets, int movie, int movieType, double totalAmount, double payment, double change) {
//        System.out.println("\n-------------------------------");
//        System.out.println("Thank you for your purchase!");
//        System.out.println("Tickets purchased: " + ANSI_BOLD + tickets + ANSI_RESET);
//        System.out.println("Movie selected: " + ANSI_BOLD + movies[movie - 1].getTitle() + ANSI_RESET);
//        System.out.println("Movie type selected: " + ANSI_BOLD + MOVIE_TYPES[movieType - 1].type() + ANSI_RESET);
//        System.out.println("Total amount: " + ANSI_BOLD + "Php" + nf.format(totalAmount) + ANSI_RESET);
//        System.out.println("Payment: " + ANSI_BOLD + "Php" + nf.format(payment) + ANSI_RESET);
//        System.out.println("Change: " + ANSI_BOLD + "Php" + nf.format(change) + ANSI_RESET);
//        System.out.println("-------------------------------\n");

        System.out.printf(ANSI_GREY_LIGHT_BG + " --------------------------------------------" + ANSI_RESET + "\n");
        System.out.printf(ANSI_RECEIPT + " |              FORT MOVIE THEATRE          |" + ANSI_RESET + "\n");
        System.out.printf(ANSI_GREY_LIGHT_BG + " |                                          |" + ANSI_RESET + "\n");

        System.out.printf("%s | %-20s%20s |%s%n", ANSI_RECEIPT, "Tickets purchased", tickets, ANSI_RESET);
        System.out.printf("%s | %-20s%20s |%s%n", ANSI_RECEIPT, "Movie selected", movies[movie - 1].getTitle(), ANSI_RESET);
        System.out.printf("%s | %-20s%20s |%s%n", ANSI_RECEIPT, "Movie Type Selected", MOVIE_TYPES[movieType - 1].type().toUpperCase(), ANSI_RESET);
        System.out.printf("%s | %-20s%,20.2f |%s%n", ANSI_RECEIPT, "Total Amount (Php)", totalAmount, ANSI_RESET);
        System.out.printf("%s | %-20s%,20.2f |%s%n", ANSI_RECEIPT, "Payment (Php)", payment, ANSI_RESET);
        System.out.printf("%s | %-20s%,20.2f |%s%n", ANSI_RECEIPT, "Change (Php)", change, ANSI_RESET);

        System.out.printf(ANSI_GREY_LIGHT_BG + " --------------------------------------------" + ANSI_RESET + "\n");
    }
}

//TODO:    Receipt must display
//*        - Ticket Purchased
//*        - Quantity
//*        - Total Amount
//*        - Amount Paid
//*        - Change

//TODO:    What must be found in you code?
//*        - Variable Declaration
//*        - Comments
//*        - Java Operators (Arithmetic, Assigment, Comparison and Logical)
//*        - String Functions
//*        - If..else if..else Statement
//?        - Switch Statement
//*        - Arrays
//*        - Loops
//*        - Methods or Functions