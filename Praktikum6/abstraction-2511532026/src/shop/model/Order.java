package shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String orderId;
    private final Address shippingAddress;
    private final List<Line> lines = new ArrayList<>();

    public Order(String orderId, Address shippingAddress) {
        if (orderId == null || orderId.isBlank() || shippingAddress == null) throw new IllegalArgumentException("Invalid order");
        this.orderId = orderId;
        this.shippingAddress = shippingAddress;
    }
    public void addLine(Product product, int quantity) { lines.add(new Line(product, quantity)); }
    public List<Line> getLines() { return List.copyOf(lines); }
    public long getItemsTotal() {
        long total = 0;
        for (Line line : lines) total += line.subtotal();
        return total;
    }
    public Receipt createReceipt() { return new Receipt(); }

    public static class Line {
        private final Product product;
        private final int quantity;
        public Line(Product product, int quantity) {
            if (product == null || quantity <= 0) throw new IllegalArgumentException("Invalid order line");
            this.product = product;
            this.quantity = quantity;
        }
        public Product getProduct() { return product; }
        public int getQuantity() { return quantity; }
        public long subtotal() { return product.getFinalPrice() * quantity; }
    }

    public class Receipt {
        public String toText() {
            StringBuilder text = new StringBuilder("STRUK PESANAN ").append(Order.this.orderId).append('\n');
            for (Line line : lines) {
                text.append("- ").append(line.getProduct().getName()).append(" x").append(line.getQuantity())
                    .append(" = Rp").append(line.subtotal()).append('\n');
            }
            return text.append("Total pesanan: Rp").append(Order.this.getItemsTotal()).append('\n')
                .append("Kirim ke: ").append(Order.this.shippingAddress).toString();
        }
    }
}
