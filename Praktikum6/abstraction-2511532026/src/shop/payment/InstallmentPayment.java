package shop.payment;

public interface InstallmentPayment extends PaymentMethod {
    int maxInstallmentMonths();
}
