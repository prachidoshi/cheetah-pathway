package bakery.pricing;

public class BulkPricing {
    private final int bulkAmount;
    private final double bulkTotalPrice;

    public BulkPricing(int bulkAmount, double bulkTotalPrice){
        this.bulkAmount = bulkAmount;
        this.bulkTotalPrice = bulkTotalPrice;
    }

    public int getBulkAmount(){
        return bulkAmount;
    }

    public double getBulkTotalPrice(){
        return bulkTotalPrice;
    }

}
