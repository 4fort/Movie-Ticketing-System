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
            System.out.println("Payment unsuccessful! Please input a proper amount.");
            return -1;
        } else {
            System.out.println("Payment successful!");
            return payment - TransactionHandler.totalAmount;
        }
    }
}
