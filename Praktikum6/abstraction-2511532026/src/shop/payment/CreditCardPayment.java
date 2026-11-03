package shop.payment;

public class CreditCardPayment implements RefundablePayment, InstallmentPayment {
    private final String cardHolder;
    private final long creditLimit;
    private long usedCredit;
    public CreditCardPayment(String cardHolder, long creditLimit) {
        if (cardHolder == null || cardHolder.isBlank() || creditLimit < 0) throw new IllegalArgumentException("Invalid card");
        this.cardHolder = cardHolder;
        this.creditLimit = creditLimit;
    }
    public String getName() { return "Kartu Kredit (" + cardHolder + ")"; }
    public long getUsedCredit() { return usedCredit; }
    public boolean pay(long amount) {
        if (amount <= 0 || amount > creditLimit - usedCredit) return false;
        usedCredit += amount;
        return true;
    }
    public boolean refund(long amount) {
        if (amount <= 0 || amount > usedCredit) return false;
        usedCredit -= amount;
        return true;
    }
    public int maxInstallmentMonths() { return 12; }
    public long monthlyInstallment(long amount, int months) {
        if (amount <= 0 || months <= 0 || months > maxInstallmentMonths()) throw new IllegalArgumentException("Invalid installment");
        return (amount + months - 1L) / months;
    }
}
