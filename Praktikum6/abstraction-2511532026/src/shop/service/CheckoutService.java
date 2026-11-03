package shop.service;

import java.util.List;
import shop.model.Product;
import shop.shipping.Shippable;
import shop.shipping.ShippingService;
import shop.payment.PaymentMethod;
import shop.payment.RefundablePayment;

public class CheckoutService {
    public long subtotal(List<Product> items) {
        long total = 0;
        for (Product item : items) total += item.getFinalPrice();
        return total;
    }
    public int totalWeight(List<Product> items) {
        int weight = 0;
        for (Product item : items) if (item instanceof Shippable s) weight += s.getWeightGrams();
        return weight;
    }
    public long total(List<Product> items, ShippingService shipping) {
        return subtotal(items) + shipping.calculateCost(totalWeight(items));
    }
    public boolean checkout(List<Product> items, ShippingService shipping, PaymentMethod payment) {
        return items != null && !items.isEmpty() && payment.pay(total(items, shipping));
    }
    public boolean refund(RefundablePayment payment, long amount) { return payment.refund(amount); }
}
