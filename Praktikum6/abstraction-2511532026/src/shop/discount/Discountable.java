package shop.discount;

public interface Discountable {
    int discountPercent();

    default long applyDiscount(long price) {
        return price - percentOf(price, discountPercent());
    }

    static long percentOf(long amount, int percent) {
        if (amount < 0 || percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Invalid amount or discount percent");
        }
        return calculatePercent(amount, percent);
    }

    private static long calculatePercent(long amount, int percent) {
        return amount * percent / 100;
    }
}
