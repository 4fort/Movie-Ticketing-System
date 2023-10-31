public class TransactionHandler {
    // STATIC VARIABLES
    private static double totalAmount;  // TOTAL AMOUNT OF PAYMENT (static, so it can be accessed from all instances)
    private static boolean isSuccess;    // SUCCESSFUL PAYMENT (static, so it can be accessed from all instances)

    double getTotalAmount() {
        return TransactionHandler.totalAmount;
    }   // returns $totalAmount of all instances

    void setTotalAmount(double totalAmount) {   // sets $totalAmount of all instances
        TransactionHandler.totalAmount = totalAmount;
    }

    double makePayment(double payment) {    // makes payment
        if (payment >= TransactionHandler.totalAmount) {    // else if payment is more than $totalAmount, program will return payment - $totalAmount
            TransactionHandler.isSuccess = true;
            System.out.println(Main.ANSI_ALERT_GREEN + "Payment successful!" + Main.ANSI_RESET);
            return payment - TransactionHandler.totalAmount;
        }
        TransactionHandler.isSuccess = false;
        Main.printInvalidInput();
//        System.out.println(Main.ANSI_RED_BG + "Payment unsuccessful! An internal error occurred. Please try again in a while." + Main.ANSI_RESET);
        return -1;
    }

    boolean isSuccess() {
        return TransactionHandler.isSuccess;
    }
}
