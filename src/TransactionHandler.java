public class TransactionHandler {
    private static double totalAmount;

    double getTotalAmount() {
        return TransactionHandler.totalAmount;
    }

    void setTotalAmount(double totalAmount) {
        TransactionHandler.totalAmount = totalAmount;
    }

    double makePayment(double payment) {
        if (payment < TransactionHandler.totalAmount) {
            System.out.println(Main.ANSI_RED_BG + "Payment unsuccessful! Please input a proper amount." + Main.ANSI_RESET);
            return -1;
        } else {
            System.out.println(Main.ANSI_GREEN + "Payment successful!" + Main.ANSI_RESET);
            return payment - TransactionHandler.totalAmount;
        }
    }
}
