package bakery.core;

import bakery.pricing.BulkPricing;
import bakery.pricing.PercentOffPricing;
import bakery.pricing.TwoForOnePricing;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> quantities;

    public Cart() {
        quantities = new LinkedHashMap<Product, Integer>();
    }

    /**
     * Function to calculate total value in cart
     *
     * @param now current date
     * @return total bill amount
     */
    public double total(Date now) {
        double result = 0;
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
        String currentDay = dayFormat.format(now);
        String currentDate = dateFormat.format(now);

        for (Product each : quantities.keySet()) {
            boolean salesApplied = false;
            if (currentDate.equals("10/01") && each.name().equals("Key Lime Cheesecake")) {
                System.out.println("Applying October 1 sale");
                salesApplied = true;
                PercentOffPricing percentOffPricing = new PercentOffPricing(each, 25);
                result += percentOffPricing.priceForQuantity(quantities.get(each));
            }
            if (currentDay.equals("Friday") && each.name().equals("Cookie")) {
                System.out.println("Applying Friday Sale");
                salesApplied = true;
                result += each.priceForQuantity(quantities.get(each), new BulkPricing(8, 6.00));
            } else if (currentDay.equals("Tuesday") && each.name().equals("Mini Gingerbread Donut")) {
                System.out.println("Applying Tuesday sale");
                salesApplied = true;
                TwoForOnePricing tfoPricing = new TwoForOnePricing(each, 2, 1);
                result += tfoPricing.priceForQuantity(quantities.get(each));
            } else if (!salesApplied) {
                //apply bulk pricing
                if (each.getBulkPricing() != null) {
                    result += each.priceForQuantity(quantities.get(each), each.getBulkPricing());
                } else {
                    result += each.priceForQuantity(quantities.get(each));
                }
            }
        }
        return result;
    }

    /**
     * Function to add product to cart
     * @param productToBuy
     */
    public void add(Product productToBuy) {
        add(productToBuy, 1);
    }

    /**
     * Function to add multiple units of a product to cart
     * @param productToBuy product to add to cart
     * @param quantity     product quantity
     */
    public void add(Product productToBuy, int quantity) {
        int previousQuantity = quantities.containsKey(productToBuy)
                ? quantities.get(productToBuy)
                : 0;
        quantities.put(productToBuy, previousQuantity + quantity);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Product each : quantities.keySet()) {
            sb.append(each.name() + ":" + each.unitPrice());
        }
        sb.append("\n");
        sb.append("TOTAL:" + total(new Date()));
        return sb.toString();
    }
}
