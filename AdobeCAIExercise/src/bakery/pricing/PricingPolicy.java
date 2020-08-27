package bakery.pricing;


import bakery.core.Product;

public class PricingPolicy {
    private final Product baseProduct;

    public PricingPolicy(Product baseProduct) {
        this.baseProduct = baseProduct;
    }

    public double unitPrice() { return baseProduct.unitPrice(); }

    public String name() { return baseProduct.name(); }

    public double priceForQuantity(int quantity) {
        return baseProduct.priceForQuantity(quantity);
    }
}
