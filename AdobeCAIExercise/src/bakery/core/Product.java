package bakery.core;

import bakery.pricing.BulkPricing;

public class Product {
    private final Integer productId;
    private final String name;
    private final String imageURL;
    private final double unitPrice;
    private final BulkPricing bulkPricing;

    public Product(Integer productId,String name,String imageURL, double unitPrice,BulkPricing bulkPricing) {
        this.productId = productId;
        this.name = name;
        this.imageURL = imageURL;
        this.unitPrice = unitPrice;
        this.bulkPricing = bulkPricing;
    }

    public int getProducId(){
        return productId;
    }

    public String name() {
        return name;
    }

    public String getImageURL(){
        return imageURL;
    }

    public double unitPrice() {
        return unitPrice;
    }

    public BulkPricing getBulkPricing() {
        return bulkPricing;
    }

    public double priceForQuantity(int quantity) {
        return unitPrice * quantity;
    }

    public double priceForQuantity(int quantity, BulkPricing bulkPricing) {
        int bulkAmount = bulkPricing.getBulkAmount();
        double bulkTotalPrice = bulkPricing.getBulkTotalPrice();
        int groups = quantity/bulkAmount;
        int remainder = quantity%bulkAmount;
        return (groups*bulkTotalPrice)+(remainder*unitPrice());
    }
}
