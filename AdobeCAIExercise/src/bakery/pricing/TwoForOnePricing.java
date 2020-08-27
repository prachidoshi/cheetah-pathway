package bakery.pricing;
import bakery.core.Product;

public class TwoForOnePricing extends PricingPolicy {

    private final int bundleSize;
    private final int paidQuantity;

    public TwoForOnePricing(Product baseProduct, int bundleSize, int paidQuantity) {
        super(baseProduct);
        this.bundleSize = bundleSize;
        this.paidQuantity = paidQuantity;
    }

    @Override
    public double priceForQuantity(int quantity) {
        int bundles = quantity / bundleSize; //find two bundle groups
        int remainder = quantity % bundleSize;
        return (bundles * paidQuantity + remainder) * unitPrice();
    }

}
