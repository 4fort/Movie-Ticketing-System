import java.util.InputMismatchException;

public class ServiceRater {
    private static int sumOfRatings;
    private static int numOfRatings;
    private final String[] stars = {"★", "☆"};

    public void rateService(int rating) {
        numOfRatings++;
        ServiceRater.sumOfRatings += rating;
    }

    public double getServiceRatings() {
        return (double) sumOfRatings / numOfRatings;
    }

    public void printRateService() {
        int rating;
        try {
            while (true) {
                String[][] rateScale = {
                        {"1", "2", "3", "4", "5"},
                        {"Terrible", "Poor", "Average", "Good", "Excellent"}
                };
                System.out.println(Main.ANSI_ALERT_BLUE_OPEN + "How would you like to rate the service?" + Main.ANSI_ALERT_BLUE_CLOSE);
                for (int i = 0; i < rateScale[0].length; i++) {
                    System.out.println(Main.ANSI_LIST + "[" + rateScale[0][i] + "]" + Main.ANSI_GREY_DARK_BG + " " + rateScale[1][i] + Main.ANSI_RESET);
                }
                System.out.println(Main.ANSI_RED_BG + "[0]" + Main.ANSI_GREY_DARK_BG + " Skip" + Main.ANSI_RESET);

                System.out.print("Rate: ");
                rating = Main.scanner.nextInt();
                if (rating < 0 || rating > 5) {
                    Main.printInvalidInput();
                    continue;
                }
                break;
            }
            if (rating == 0) {
                return;
            }
            rateService(rating);
        } catch (InputMismatchException e) {
            Main.printInvalidInput();
            Main.scanner.nextLine();
        }
        System.out.println(Main.ANSI_ALERT_GREEN + "Thank you for taking your time to rate the service!" + Main.ANSI_RESET);
    }

    public void printServiceRatings() {
        System.out.print("Service rating: ");
        switch ((int) getServiceRatings()) {
            case 1:
                for (int i = 0; i < 1; i++) {
                    System.out.print(stars[0]);
                }
                for (int i = 0; i < 4; i++) {
                    System.out.print(stars[1]);
                }
                System.out.println(" Terrible");
                break;
            case 2:
                for (int i = 0; i < 2; i++) {
                    System.out.print(stars[0]);
                }
                for (int i = 0; i < 3; i++) {
                    System.out.print(stars[1]);
                }
                System.out.println(" Poor");
                break;
            case 3:
                for (int i = 0; i < 3; i++) {
                    System.out.print(stars[0]);
                }
                for (int i = 0; i < 2; i++) {
                    System.out.print(stars[1]);
                }
                System.out.println(" Average");
                break;
            case 4:
                for (int i = 0; i < 4; i++) {
                    System.out.print(stars[0]);
                }
                for (int i = 0; i < 1; i++) {
                    System.out.print(stars[1]);
                }
                System.out.println(" Good");
                break;
            case 5:
                for (int i = 0; i < 5; i++) {
                    System.out.print(stars[0]);
                }
                System.out.println(" Excellent");
                break;
            default:
                System.out.println("No ratings yet");
                break;
        }
    }
}
