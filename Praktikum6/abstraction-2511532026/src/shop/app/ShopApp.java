package shop.app;

import java.util.List;
import shop.model.Address;
import shop.model.DigitalProduct;
import shop.model.Order;
import shop.model.PhysicalProduct;
import shop.model.Product;
import shop.payment.PaymentMethod;
import shop.service.CheckoutService;
import shop.shipping.ExpressShipping;
import shop.shipping.RegularShipping;

public class ShopApp {
    public static void main(String[] args) {
        Product coffee = new PhysicalProduct("KOPI-001", "Kopi Arabika Gayo 250g", 85_000, 300);
        Product rendang = new PhysicalProduct("RDG-002", "Rendang Kemasan 250g", 120_000, 400);
        Product ebook = new DigitalProduct("EBK-003", "E-book Resep Kue Nusantara", 50_000,
                "https://toko-umkm.example/unduh/ebk-003");
        List<Product> cart = List.of(coffee, rendang, ebook);
        CheckoutService checkout = new CheckoutService();

        System.out.println("== Katalog ==");
        cart.forEach(product -> System.out.println(product.getInfo()));
        System.out.println("\n== Ringkasan ==");
        System.out.println("Subtotal      : Rp" + checkout.subtotal(cart));
        System.out.println("Berat kiriman : " + checkout.totalWeight(cart) + " g");
        System.out.println("Total Reguler : Rp" + checkout.total(cart, new RegularShipping()));
        System.out.println("Total Ekspres : Rp" + checkout.total(cart, new ExpressShipping()));

        Address address = new Address("Jl. Sudirman 10", "Padang", "25112");
        Order order = new Order("ORD-2026-001", address);
        order.addLine(coffee, 2);
        order.addLine(ebook, 1);
        System.out.println("\n" + order.createReceipt().toText());

        PaymentMethod voucher = new PaymentMethod() {
            private long balance = 50_000;
            public String getName() { return "Voucher Promo"; }
            public boolean pay(long amount) {
                if (amount <= 0 || amount > balance) return false;
                balance -= amount;
                return true;
            }
        };
        System.out.println("\nVoucher untuk e-book berhasil? " + voucher.pay(ebook.getFinalPrice()));
    }
}
