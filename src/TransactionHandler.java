public class TransactionHandler {
    private static double totalAmount;

    void setTotalAmount(double totalAmount) {
        TransactionHandler.totalAmount = totalAmount;
    }
    double getTotalAmount() {
        return totalAmount;
    }
}
