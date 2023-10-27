import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    //  !!!  CONSTANT DECLARATIONS   !!!

    //  ANSI ESCAPE CODES FOR TERMINAL COLORS
    public static final String ANSI_RED_BG = "\u001B[41m";
    public static final String ANSI_GREEN = "\u001B[42m \u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    //  MOVIE TYPES OBJECT ARRAY
    public static final MovieType[] MOVIE_TYPES = {
            new MovieType("IMAX", 100),
            new MovieType("3D", 200),
            new MovieType("IMAX 3D", 250)
    };

    //  UTILITY FUNCTIONS
    public static Scanner scanner = new Scanner(System.in);

    public static TransactionHandler transactions = new TransactionHandler();   // handles transactions made

    //  MOVIES OBJECT ARRAY
    public static Movie[] movies = {
            new Movie("Interstellar", 200, 25),
            new Movie("The Matrix", 300, 30),
            new Movie("The Godfather", 380, 80),
            new Movie("The Joker", 250, 20),
            new Movie("If Only", 400, 15)
    };

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
                        System.out.println(ANSI_RED_BG + "Tickets sold out! Please select another movie." + ANSI_RESET);
                        continue;
                    }
                    // if above condition is false then program will continue
                    System.out.println(ANSI_GREEN + "Tickets available!" + ANSI_RESET);
                    while (true) {  // while loop for user input validation that must be between 1-3
                        System.out.println("Please select a movie type:");
                        try {   // try catch block for user input validation
                            movieTypeChoice = movieTypeSelector();
                        } catch (InputMismatchException e) {
                            printInvalidInput();
                            scanner.nextLine(); // clears scanner input
                            continue;
                        }
                        if (movieTypeChoice > MOVIE_TYPES.length) { // if user input is not between 1-3, program will repeat loop
                            printInvalidInput();
                            continue;
                        }
                        break;  // if user input is between 1-3, program will continue
                    }
                    if (movieTypeChoice == 0)
                        continue; // if user input is 0, program go back to main menu (previous loop)

                    System.out.println("How many tickets would you like to purchase?:");
                    while (true) {  // while loop for user input validation
                        try {
                            ticketCount = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            printInvalidInput();
                            scanner.nextLine();
                            continue;
                        }
                        // if user input is more than available tickets, program will repeat loop
                        if (ticketCount > movies[movieChoice - 1].getTicketsAvailable()) {
                            System.out.println(ANSI_RED_BG + movies[movieChoice - 1].getTicketsAvailable() + " tickets are left!" + ANSI_RESET + " Please input the right number of tickets.");
                            continue;
                        }
                        break;
                    }
                    // deducts available tickets of selected movie from user purchase. ($movie.getTicketsAvailable() - $ticketCount)
                    movies[movieChoice - 1].setTicketsAvailable(movies[movieChoice - 1].getTicketsAvailable() - ticketCount);
                    // calculates total amount of selected movie and adds additional price
                    transactions.setTotalAmount((movies[movieChoice - 1].getPrice() + MOVIE_TYPES[movieTypeChoice - 1].additionalPrice()) * ticketCount);

                    System.out.println("Total amount: Php" + transactions.getTotalAmount());
                    System.out.println("Input amount to pay: Php");
                    while (true) {   // while loop for user input validation.
                        try {   // try catch block for user input validation
                            payment = scanner.nextDouble();
                            // if $payment is not 0 and $payment is greater than the $totalAmount, program continues with payment and sets change
                            if (payment > 0 && payment >= transactions.getTotalAmount()) {
                                change = transactions.makePayment(payment);
                                break;
                            }
                            // if $payment is less than $totalAmount, program will repeat loop
                            else if (payment < transactions.getTotalAmount()) {
                                System.out.println(ANSI_RED_BG + "Payment unsuccessful! Please input a proper amount." + ANSI_RESET);
                            }
                        } catch (InputMismatchException e) {
                            printInvalidInput();
                            scanner.nextLine();
                        }
                    }

                    // prints change
                    System.out.println("Change: Php" + change);
                    System.out.println(ANSI_GREEN + "Thank you for your purchase!" + ANSI_RESET);
                    transactions.setTotalAmount(0);
                } else {
                    System.out.println(ANSI_RED_BG + "Select from the following only!" + ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                printInvalidInput();
                scanner.nextLine();
            }
        }
    }

    // PRINTS A LIST OF MOVIES AND RETURNS USER INPUT
    public static int movieSelector() {
        printMovieList();
        System.out.println("[0] Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    // PRINTS A LIST OF MOVIES
    public static void printMovieList() {
        System.out.println("What would you like to watch?");
        for (int i = 0; i < movies.length; i++) {
            System.out.println("[" + (i + 1) + "] " + movies[i].getTitle() + " - Php" + movies[i].getPrice() + " (" + movies[i].getTicketsAvailable() + " available)");
        }
    }

    // PRINTS A LIST OF MOVIE TYPES AND RETURNS USER INPUT
    public static int movieTypeSelector() {
        printMovieTypes();
        System.out.println("[0] Return");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    // PRINTS A LIST OF MOVIE TYPES
    public static void printMovieTypes() {
        for (int i = 0; i < MOVIE_TYPES.length; i++) {
            System.out.println("[" + (i + 1) + "] " + MOVIE_TYPES[i].type() + " (additional price: Php" + MOVIE_TYPES[i].additionalPrice() + ")");
        }
    }

    // PRINTS INVALID INPUT
    public static void printInvalidInput() {
        System.out.println(ANSI_RED_BG + "Invalid input! Please try again." + ANSI_RESET);
    }
}

//TODO:    What must be found in you code?
//*        - Variable Declaration
//*        - Comments
//*        - Java Operators (Arithmetic, Assigment, Comparison and Logical)
//?        - String Functions
//*        - If..else if..else Statement
//?        - Switch Statement
//*        - Arrays
//*        - Loops
//*        - Methods or Functions