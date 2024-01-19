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
    public static final MovieType[] MOVIE_TYPES = {
            new MovieType("REGULAR", 100),
            new MovieType("IMAX", 200),
            new MovieType("3D", 150),
            new MovieType("IMAX 3D", 250)
    };

    //  UTILITY FUNCTIONS
    public static Scanner scanner = new Scanner(System.in);

    public static NumberFormat nf = NumberFormat.getInstance();
    public static TransactionHandler transactions = new TransactionHandler();   // handles transactions made

    //  MOVIES OBJECT ARRAY
//    public static Movie[] movies = {
//            new Movie("Interstellar", 200, 25),
//            new Movie("The Matrix", 300, 30),
//            new Movie("The Godfather", 380, 80),
//            new Movie("The Joker", 250, 20),
//            new Movie("If Only", 400, 15)
//    };

    public static Movie[] regularMovies = {
            new Movie("Interstellar", 200),
            new Movie("The Matrix", 300),
            new Movie("The Godfather", 380),
            new Movie("The Joker", 250),
            new Movie("If Only", 400)
    };

    public static MovieImax[] imaxMovies = {
            new MovieImax("Interstellar", 200),
            new MovieImax("The Matrix", 300),
            new MovieImax("The Godfather", 380),
            new MovieImax("The Joker", 250),
            new MovieImax("If Only", 400)
    };

    public static Movie3d[] threeDMovies = {
            new Movie3d("Interstellar", 200),
            new Movie3d("The Matrix", 300),
            new Movie3d("The Godfather", 380),
            new Movie3d("The Joker", 250),
            new Movie3d("If Only", 400)
    };

    public static MovieImax3d[] imax3dMovies = {
            new MovieImax3d("Interstellar", 200),
            new MovieImax3d("The Matrix", 300),
            new MovieImax3d("The Godfather", 380),
            new MovieImax3d("The Joker", 250),
            new MovieImax3d("If Only", 400)
    };

    public static Movie[][] movies = {
            regularMovies, imaxMovies, threeDMovies, imax3dMovies
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
//            try {
//                movieChoice = movieSelector();  // returns user input (0-5)
//                if (movieChoice == 0) { // if user input is 0 program will exit
//                    System.out.println("Bye!");
//                    isContinue = false;
//                } else if (movieChoice <= movies.length) {    // else if user input is 1-5, program will continue
//                    // checks if movie tickets is less than or equal to 0 then program will repeat loop
//                    if (movies[movieChoice - 1].getTicketsAvailable() <= 0) {
//                        System.out.println(ANSI_RED_BG + "Tickets sold out! Please select another movie." + ANSI_RESET);
//                        continue;
//                    }
//                    // if above condition is false then program will continue
//                    System.out.println(ANSI_GREEN + "Tickets available!" + ANSI_RESET);
//                    while (true) {  // while loop for user input validation that must be between 1-3
//                        System.out.println("Please select a movie type:");
//                        try {   // try catch block for user input validation
//                            movieTypeChoice = movieTypeSelector();
//                        } catch (InputMismatchException e) {
//                            printInvalidInput();
//                            scanner.nextLine(); // clears scanner input
//                            continue;
//                        }
//                        if (movieTypeChoice > MOVIE_TYPES.length) { // if user input is not between 1-3, program will repeat loop
//                            printInvalidInput();
//                            continue;
//                        }
//                        break;  // if user input is between 1-3, program will continue
//                    }
//                    if (movieTypeChoice == 0)
//                        continue; // if user input is 0, program go back to main menu (previous loop)
//
//                    System.out.println("How many tickets would you like to purchase?:");
//                    while (true) {  // while loop for user input validation
//                        try {
//                            ticketCount = scanner.nextInt();
//                        } catch (InputMismatchException e) {
//                            printInvalidInput();
//                            scanner.nextLine();
//                            continue;
//                        }
//                        // if user input is more than available tickets, program will repeat loop
//                        if (ticketCount > movies[movieChoice - 1].getTicketsAvailable()) {
//                            System.out.println(ANSI_RED_BG + movies[movieChoice - 1].getTicketsAvailable() + " tickets are left!" + ANSI_RESET + " Please input the right number of tickets.");
//                            ticketCount = 0;    // resets ticketCount to 0 so error will not occur on next loop
//                            continue;
//                        }
//                        break;
//                    }
//                    // deducts available tickets of selected movie from user purchase. ($movie.getTicketsAvailable() - $ticketCount)
//                    movies[movieChoice - 1].setTicketsAvailable(movies[movieChoice - 1].getTicketsAvailable() - ticketCount);
//                    // calculates total amount of selected movie and adds additional price
//                    transactions.setTotalAmount((movies[movieChoice - 1].getPrice() + MOVIE_TYPES[movieTypeChoice - 1].additionalPrice()) * ticketCount);
//
//                    System.out.println("Total amount: Php" + transactions.getTotalAmount());
//                    System.out.println("Input amount to pay: Php");
//                    while (true) {   // while loop for user input validation.
//                        try {   // try catch block for user input validation
//                            payment = scanner.nextDouble();
//                            // if $payment is not 0 and $payment is greater than the $totalAmount, program continues with payment and sets change
//                            if (payment > 0 && payment >= transactions.getTotalAmount()) {
//                                change = transactions.makePayment(payment);
//                                break;
//                            }
//                            // if $payment is less than $totalAmount, program will repeat loop
//                            else if (payment < transactions.getTotalAmount()) {
//                                System.out.println(ANSI_RED_BG + "Payment unsuccessful! Please input a proper amount." + ANSI_RESET);
//                            }
//                        } catch (InputMismatchException e) {
//                            printInvalidInput();
//                            scanner.nextLine();
//                        }
//                    }
//
//                    // prints change
//                    System.out.println("Change: Php" + change);
//                    System.out.println(ANSI_GREEN + "Thank you for your purchase!" + ANSI_RESET);
//                    payment = 0; // resets payment to 0
//                    change = 0; // resets change to 0
//                    transactions.setTotalAmount(0);
//                } else {
//                    System.out.println(ANSI_RED_BG + "Select from the following only!" + ANSI_RESET);
//                }
//            } catch (InputMismatchException e) {
//                printInvalidInput();
//                scanner.nextLine();
//            }

            try {
                movieTypeChoice = movieTypeSelector();
                if (movieTypeChoice == 0) {
                    System.out.println("Thank you!");
                    isContinue = false;
                } else if (movieTypeChoice <= MOVIE_TYPES.length) {
                    while (true) {  // while loop for movie selection
                        try {
                            movieChoice = movieSelector(movieTypeChoice);
                            if (movieChoice == 0) {
                                break;
                            } else if (movieChoice > 0 && movieChoice <= movies[movieTypeChoice - 1].length) {
                                if (movies[movieTypeChoice - 1][movieChoice - 1].getTicketsAvailable() <= 0) {
                                    System.out.println(ANSI_ALERT_RED + "Tickets sold out! Please select another movie." + ANSI_RESET);
                                } else {
                                    System.out.println(ANSI_ALERT_GREEN + "Tickets available!" + ANSI_RESET);
                                    break;
                                }
                            } else {
                                printSelectFromListWarning();
                            }
                        } catch (InputMismatchException e) {
                            printInvalidInput();
                            scanner.nextLine();
                        }
                    }
                    if (movieChoice == 0) {
                        continue;
                    }
                    while (true) {   // while loop for ticket purchase
                        try {
                            System.out.print("\n\n" + ANSI_ALERT_BLUE_OPEN + "How many tickets would you like to purchase? (" + movies[movieTypeChoice - 1][movieChoice - 1].getTicketsAvailable() + " tickets available):" + ANSI_RESET);
                            ticketCount = scanner.nextInt();
                            if (ticketCount > movies[movieTypeChoice - 1][movieChoice - 1].getTicketsAvailable()) {
                                System.out.println(ANSI_ALERT_RED + movies[movieTypeChoice - 1][movieChoice - 1].getTicketsAvailable() + " tickets are left!" + " Please input the right number of tickets." + ANSI_RESET);
                            } else {
                                movies[movieTypeChoice - 1][movieChoice - 1].setTicketsAvailable(movies[movieTypeChoice - 1][movieChoice - 1].getTicketsAvailable() - ticketCount);
                                transactions.setTotalAmount((movies[movieTypeChoice - 1][movieChoice - 1].getPrice() + MOVIE_TYPES[movieTypeChoice - 1].getTotalPrice()) * ticketCount);
                                break;
                            }
                        } catch (InputMismatchException e) {
                            printInvalidInput();
                            scanner.nextLine();
                        }
                    }
                    while (true) {   // while loop for payment
                        try {   // try catch block for user input validation
                            System.out.println("\n\n" + ANSI_ALERT_BLUE_OPEN + "Total amount: " + nf.format(transactions.getTotalAmount()) + ANSI_ALERT_BLUE_CLOSE);
                            System.out.print(ANSI_ALERT_BLUE_OPEN + "Input amount to pay: " + ANSI_RESET + "Php");
                            payment = scanner.nextDouble();
                            // if $payment is not 0 and $payment is greater than the $totalAmount, program continues with payment and sets change
                            if (payment > 0 && payment >= transactions.getTotalAmount()) {
                                change = transactions.makePayment(payment);
                                break;
                            }
                            // if $payment is less than $totalAmount, program will repeat loop
                            else if (payment < transactions.getTotalAmount()) {
                                System.out.println(ANSI_ALERT_RED + "Payment unsuccessful! Please input a proper amount." + ANSI_RESET);
                            }
                        } catch (InputMismatchException e) {
                            printInvalidInput();
                            scanner.nextLine();
                        }
                    }
                    printReceipt(ticketCount, movieChoice, movieTypeChoice, transactions.getTotalAmount(), payment, change);
                    transactions.setTotalAmount(0);
                } else {
                    printSelectFromListWarning();
                }
            } catch (InputMismatchException e) {
                printInvalidInput();
                scanner.nextLine();
            }


        }
    }

    // PRINTS A LIST OF MOVIES AND RETURNS USER INPUT
    public static int movieSelector(int movieTypeChoice) {
        System.out.println("\n\nWhat would you like to watch?");
        printMovieList(movieTypeChoice);
        System.out.println(ANSI_BOLD + ANSI_RED_BG + "[0]" + ANSI_GREY_DARK_BG + " Exit" + ANSI_RESET);
        System.out.print(ANSI_ALERT_BLUE_OPEN + "Enter your choice:" + ANSI_RESET);
        return scanner.nextInt();
    }

    // PRINTS A LIST OF MOVIES
    public static void printMovieList(int movieTypeChoice) {
        for (int i = 0; i < movies[movieTypeChoice - 1].length; i++) {
            System.out.println(ANSI_LIST + "[" + (i + 1) + "]" + ANSI_GREY_DARK_BG + " " + movies[movieTypeChoice - 1][i].getTitle() + " - " + movies[movieTypeChoice - 1][i].getPrice() + " (" + movies[movieTypeChoice - 1][i].getTicketsAvailable() + " available)" + ANSI_RESET);
        }
    }

    // PRINTS A LIST OF MOVIE TYPES AND RETURNS USER INPUT
    public static int movieTypeSelector() {
        System.out.println("\n\nPlease select a movie type:");
        printMovieTypes();
        System.out.println(ANSI_BOLD + ANSI_RED_BG + "[0]" + ANSI_GREY_DARK_BG + " Return to movie selection" + ANSI_RESET);
        System.out.print(ANSI_ALERT_BLUE_OPEN + "Enter your choice:" + ANSI_RESET);
        return scanner.nextInt();
    }

    // PRINTS A LIST OF MOVIE TYPES
    public static void printMovieTypes() {
        for (int i = 0; i < MOVIE_TYPES.length; i++) {
            System.out.println(ANSI_LIST + "[" + (i + 1) + "]" + ANSI_GREY_DARK_BG + " " + MOVIE_TYPES[i].getType() + " (price: Php" + MOVIE_TYPES[i].getTotalPrice() + ")" + ANSI_RESET);
        }
    }

    // PRINTS INVALID INPUT
    public static void printInvalidInput() {
        System.out.println(ANSI_ALERT_RED + "Invalid input! Please try again." + ANSI_RESET);
    }

    public static void printSelectFromListWarning() {

        System.out.println(ANSI_ALERT_RED + "Select from the following only!" + ANSI_RESET);
    }

    public static void printReceipt(int tickets, int movie, int movieType, double totalAmount, double payment, double change) {
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
        System.out.printf(ANSI_GREY_LIGHT_BG + " |        Thank you for your purchase!      |" + ANSI_RESET + "\n");
        System.out.printf(ANSI_GREY_LIGHT_BG + " |                                          |" + ANSI_RESET + "\n");

        System.out.printf("%s | %-20s%20s |%s%n", ANSI_RECEIPT, "Tickets purchased", tickets, ANSI_RESET);
        System.out.printf("%s | %-20s%20s |%s%n", ANSI_RECEIPT, "Movie selected", movies[movieType - 1][movie - 1].getTitle(), ANSI_RESET);
        System.out.printf("%s | %-20s%20s |%s%n", ANSI_RECEIPT, "Movie Type Selected", MOVIE_TYPES[movieType - 1].getType(), ANSI_RESET);
        System.out.printf("%s | %-20s%,20.2f |%s%n", ANSI_RECEIPT, "Total Amount (Php)", totalAmount, ANSI_RESET);
        System.out.printf("%s | %-20s%,20.2f |%s%n", ANSI_RECEIPT, "Payment (Php)", payment, ANSI_RESET);
        System.out.printf("%s | %-20s%,20.2f |%s%n", ANSI_RECEIPT, "Change (Php)", change, ANSI_RESET);

        System.out.printf(ANSI_GREY_LIGHT_BG + " --------------------------------------------" + ANSI_RESET + "\n");
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

//TODO:    Pillars of OOP
//*        - Encapsulation
//*        - Inheritance
//*        - Polymorphism
//*        - Abstraction
