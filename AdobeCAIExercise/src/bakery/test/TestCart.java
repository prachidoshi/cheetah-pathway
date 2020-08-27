package bakery.test;

import org.junit.Before;
import org.junit.Test;
import bakery.pricing.BulkPricing;
import bakery.core.Cart;
import bakery.core.Product;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class TestCart {
    private Cart cart;
    private Product cookie, brownie, cheesecake,donut;
    private BulkPricing bulkPricingCookie, bulkPricingBrownie , bulkPricingCheesecake,bulkPricingDonut;
    @Before
    public void setUp() {
        cart = new Cart();
        bulkPricingCookie = new BulkPricing(6,6.00);
        bulkPricingBrownie = new BulkPricing(4,7.00);
        bulkPricingCheesecake = null;
        bulkPricingDonut = null;

        brownie = new Product(1, "Brownie", "", 2.00,bulkPricingBrownie);
        cheesecake = new Product(2, "Key Lime Cheesecake", "", 8.00,null);
        cookie = new Product(3, "Cookie", "", 1.25,bulkPricingCookie);
        donut = new Product(4,"Mini Gingerbread Donut","",0.50,null);

    }

    @Test
    public void testPrintEmptyBill() {
        assertEquals("\nTOTAL:0.0", cart.toString());
    }

    @Test
    public void testPrintOneOfEachBill() {
        cart.add(cookie);
        assertEquals("Cookie:1.25\nTOTAL:1.25", cart.toString());
    }

    @Test
    public void testEmptyCartCostsZero() {
        assertEquals(0, cart.total(new Date()),0.01);
    }

    @Test
    public void testSingleProductCostsItsUnitPrice() {
        cart.add(cookie);
        assertEquals(cookie.unitPrice(), cart.total(new Date()),0.01);
    }

    @Test
    public void testMultipleProductsCost() {
        int quantity = 3;
        cart.add(cookie, quantity);
        assertEquals(quantity * cookie.unitPrice(), cart.total(new Date()),0.01);
    }

    @Test
    public void testCartAddSeparately() {
        int howMany = 3;
        for (int i = howMany; i > 0; i--) {
            cart.add(cookie);
        }
        assertEquals(howMany * cookie.unitPrice(), cart.total(new Date()),0.01);
    }

    @Test
    public void testDifferentProductsWithoutSaleCase1(){
        cart.add(cookie,1);
        cart.add(brownie,4);
        cart.add(cheesecake,1);
        assertEquals(cart.total(new Date()),16.25,0.01);
    }

    @Test
    public void differentItemsWithoutSaleCase2(){
        cart.add(cookie,8);
        assertEquals(cart.total(new Date()),8.50,0.01);
    }

    @Test
    public void differentItemsWithoutSaleCase3(){
        cart.add(cookie,1);
        cart.add(brownie,1);
        cart.add(cheesecake,1);
        cart.add(donut,2);
        assertEquals(cart.total(new Date()),12.25,0.01);
    }

    @Test
    public void differentItemsWithSaleOct12021(){
        //mock clock
        Calendar myCalendar = new GregorianCalendar(2021, 9, 1);
        Date myDate = myCalendar.getTime();
        cart.add(cookie,8);
        cart.add(cheesecake,4);
        assertEquals(cart.total(myDate),30.00,0.01);
    }
}
