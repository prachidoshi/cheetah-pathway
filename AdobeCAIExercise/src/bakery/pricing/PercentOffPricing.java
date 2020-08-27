package bakery.pricing;


import bakery.core.Product;

public class PercentOffPricing extends PricingPolicy{
    private final double priceFactor;

    public PercentOffPricing(Product baseProduct, int percentOff) {
        super(baseProduct);
        if (percentOff < 0 || percentOff > 100 ) {
            throw new IllegalArgumentException("percent must be in [0,100]");
        }
        this.priceFactor = (100 - percentOff) / 100.0;
    }

    @Override
    public double priceForQuantity(int quantity) {
        return (double) (super.priceForQuantity(quantity) * priceFactor);
    }
}
