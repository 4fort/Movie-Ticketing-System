public class TransactionHandler {
    // STATIC VARIABLES
    private static double totalAmount;  // TOTAL AMOUNT OF PAYMENT (static, so it can be accessed from all instances)

    double getTotalAmount() {
        return TransactionHandler.totalAmount;
    }   // returns $totalAmount of all instances

    void setTotalAmount(double totalAmount) {   // sets $totalAmount of all instances
        TransactionHandler.totalAmount = totalAmount;
    }

    double makePayment(double payment) {    // makes payment
        if (payment < TransactionHandler.totalAmount) { // if payment is less than $totalAmount, program will return -1
            System.out.println(Main.ANSI_RED_BG + "Payment unsuccessful! Please input a proper amount." + Main.ANSI_RESET);
            return -1;
        } else {    // else if payment is more than $totalAmount, program will return payment - $totalAmount
            System.out.println(Main.ANSI_GREEN + "Payment successful!" + Main.ANSI_RESET);
            return payment - TransactionHandler.totalAmount;
        }
    }
}
