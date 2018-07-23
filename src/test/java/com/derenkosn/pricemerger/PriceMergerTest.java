package com.derenkosn.pricemerger;

import com.derenkosn.pricemerger.model.Price;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PriceMergerTest {

    @org.junit.Test
    public void merge1() {

        // existing prices
        List<Price> currentPrices = new ArrayList<>();
        currentPrices.add(new Price("122856", 1, 1, new Date(2013,0,1,0,0,0),new Date(2013,0,31,23,59,59),11000));
        currentPrices.add(new Price("122856", 2, 1, new Date(2013,0,10,0,0,0),new Date(2013,0,20,23,59,59),99000));
        currentPrices.add(new Price("6654", 1, 2, new Date(2013,0,1,0,0,0),new Date(2013,0,31,0,0,0),5000));


        // new prices
        List<Price> newPrices = new ArrayList<>();
        newPrices.add(new Price("122856", 1, 1, new Date(2013,0,20,0,0,0),new Date(2013,1,20,23,59,59),11000));
        newPrices.add(new Price("122856", 2, 1, new Date(2013,0,15,0,0,0),new Date(2013,0,25,23,59,59),92000));
        newPrices.add(new Price("6654", 1, 2, new Date(2013,0,12,0,0,0),new Date(2013,0,13,0,0,0),4000));


        // expected merge result
        List<Price> expected = new ArrayList<>();
        expected.add(new Price("122856", 1, 1, new Date(2013,0,1,0,0,0),new Date(2013,1,20,23,59,59),11000));
        expected.add(new Price("122856", 2, 1, new Date(2013,0,10,0,0,0),new Date(2013,0,15,0,0,0),99000));
        expected.add(new Price("122856", 2, 1, new Date(2013,0,15,0,0,0),new Date(2013,0,25,23,59,59),92000));

        expected.add(new Price("6654", 1, 2, new Date(2013,0,1,0,0,0),new Date(2013,0,12,0,0,0),5000));
        expected.add(new Price("6654", 1, 2, new Date(2013,0,12,0,0,0),new Date(2013,0,13,0,0,0),4000));
        expected.add(new Price("6654", 1, 2, new Date(2013,0,13,0,0,0),new Date(2013,0,31,0,0,0),5000));


        // actual merge result
        List<Price> actual = PriceMerger.merge(currentPrices,newPrices);

        assertEquals(expected.size(),actual.size());
        for (Price price: expected) {
            assertTrue(actual.contains(price));
        }
    }


    @org.junit.Test
    public void merge2() {
        // existing prices
        List<Price> currentPrices = new ArrayList<>();
        currentPrices.add(new Price("111", 1, 1, new Date(113,0,1,0,0,0),new Date(113,0,31,0,0,0),50));



        // new prices
        List<Price> newPrices = new ArrayList<>();
        newPrices.add(new Price("111", 1, 1, new Date(113,0,12,0,0,0),new Date(113,0,17,0,0,0),60));



        // expected merge result
        List<Price> expected = new ArrayList<>();
        expected.add(new Price("111", 1, 1, new Date(113,0,1,0,0,0),new Date(113,0,12,0,0,0),50));
        expected.add(new Price("111", 1, 1, new Date(113,0,12,0,0,0),new Date(113,0,17,0,0,0),60));
        expected.add(new Price("111", 1, 1, new Date(113,0,17,0,0,0),new Date(113,0,31,0,0,0),50));



        // actual merge result
        List<Price> actual = PriceMerger.merge(currentPrices,newPrices);

        assertEquals(expected.size(),actual.size());
        for (Price price: expected) {
            assertTrue(actual.contains(price));
        }

    }

    @org.junit.Test
    public void merge3() {
        // existing prices
        List<Price> currentPrices = new ArrayList<>();
        currentPrices.add(new Price("111", 1, 1, new Date(113,0,1,0,0,0),new Date(113,0,22,0,0,0),100));
        currentPrices.add(new Price("111", 1, 1, new Date(113,0,22,0,0,0),new Date(113,0,31,0,0,0),120));



        // new prices
        List<Price> newPrices = new ArrayList<>();
        newPrices.add(new Price("111", 1, 1, new Date(113,0,15,0,0,0),new Date(113,0,25,0,0,0),110));



        // expected merge result
        List<Price> expected = new ArrayList<>();
        expected.add(new Price("111", 1, 1, new Date(113,0,1,0,0,0),new Date(113,0,15,0,0,0),100));
        expected.add(new Price("111", 1, 1, new Date(113,0,15,0,0,0),new Date(113,0,25,0,0,0),110));
        expected.add(new Price("111", 1, 1, new Date(113,0,25,0,0,0),new Date(113,0,31,0,0,0),120));



        // actual merge result
        List<Price> actual = PriceMerger.merge(currentPrices,newPrices);

        assertEquals(expected.size(),actual.size());
        for (Price price: expected) {
            assertTrue(actual.contains(price));
        }

    }

    @org.junit.Test
    public void merge4() {
        // existing prices
        List<Price> currentPrices = new ArrayList<>();
        currentPrices.add(new Price("111", 1, 1, new Date(113,0,1,0,0,0),new Date(113,0,10,0,0,0),80));
        currentPrices.add(new Price("111", 1, 1, new Date(113,0,10,0,0,0),new Date(113,0,20,0,0,0),87));
        currentPrices.add(new Price("111", 1, 1, new Date(113,0,20,0,0,0),new Date(113,0,31,0,0,0),90));



        // new prices
        List<Price> newPrices = new ArrayList<>();
        newPrices.add(new Price("111", 1, 1, new Date(113,0,5,0,0,0),new Date(113,0,14,0,0,0),80));
        newPrices.add(new Price("111", 1, 1, new Date(113,0,14,0,0,0),new Date(113,0,25,0,0,0),85));



        // expected merge result
        List<Price> expected = new ArrayList<>();
        expected.add(new Price("111", 1, 1, new Date(113,0,1,0,0,0),new Date(113,0,14,0,0,0),80));
        expected.add(new Price("111", 1, 1, new Date(113,0,14,0,0,0),new Date(113,0,25,0,0,0),85));
        expected.add(new Price("111", 1, 1, new Date(113,0,25,0,0,0),new Date(113,0,31,0,0,0),90));



        // actual merge result
        List<Price> actual = PriceMerger.merge(currentPrices,newPrices);

        assertEquals(expected.size(),actual.size());
        for (Price price: expected) {
            assertTrue(actual.contains(price));
        }

    }

    @org.junit.Test
    public void merge5() {
        // existing prices
        List<Price> currentPrices = new ArrayList<>();
        currentPrices.add(new Price("111", 1, 1, new Date(113,0,1,0,0,0),new Date(113,0,10,0,0,0),80));
        currentPrices.add(new Price("111", 1, 1, new Date(113,0,10,0,0,0),new Date(113,0,20,0,0,0),87));
        currentPrices.add(new Price("111", 1, 1, new Date(113,0,20,0,0,0),new Date(113,0,31,0,0,0),90));



        // new prices
        List<Price> newPrices = new ArrayList<>();
        newPrices.add(new Price("111", 1, 1, new Date(113,0,5,0,0,0),new Date(113,0,14,0,0,0),80));
        newPrices.add(new Price("111", 1, 1, new Date(113,0,14,0,0,0),new Date(113,0,25,0,0,0),85));
        newPrices.add(new Price("111", 1, 1, new Date(113,1,1,0,0,0),new Date(113,1,15,0,0,0),100));



        // expected merge result
        List<Price> expected = new ArrayList<>();
        expected.add(new Price("111", 1, 1, new Date(113,0,1,0,0,0),new Date(113,0,14,0,0,0),80));
        expected.add(new Price("111", 1, 1, new Date(113,0,14,0,0,0),new Date(113,0,25,0,0,0),85));
        expected.add(new Price("111", 1, 1, new Date(113,0,25,0,0,0),new Date(113,0,31,0,0,0),90));
        expected.add(new Price("111", 1, 1, new Date(113,1,1,0,0,0),new Date(113,1,15,0,0,0),100));



        // actual merge result
        List<Price> actual = PriceMerger.merge(currentPrices,newPrices);

        assertEquals(expected.size(),actual.size());
        for (Price price: expected) {
            assertTrue(actual.contains(price));
        }

    }

    @org.junit.Test
    public void merge6() {
        // existing prices
        List<Price> currentPrices = new ArrayList<>();
        currentPrices.add(new Price("111", 1, 1, new Date(113,0,1,0,0,0),new Date(113,0,10,0,0,0),80));
        currentPrices.add(new Price("111", 1, 1, new Date(113,0,10,0,0,0),new Date(113,0,20,0,0,0),87));
        currentPrices.add(new Price("111", 1, 1, new Date(113,0,20,0,0,0),new Date(113,0,31,0,0,0),90));
        currentPrices.add(new Price("111", 1, 1, new Date(113,1,1,0,0,0),new Date(113,1,31,0,0,0),100));



        // new prices
        List<Price> newPrices = new ArrayList<>();
        newPrices.add(new Price("111", 1, 1, new Date(113,0,5,0,0,0),new Date(113,0,14,0,0,0),80));
        newPrices.add(new Price("111", 1, 1, new Date(113,0,14,0,0,0),new Date(113,0,25,0,0,0),85));




        // expected merge result
        List<Price> expected = new ArrayList<>();
        expected.add(new Price("111", 1, 1, new Date(113,0,1,0,0,0),new Date(113,0,14,0,0,0),80));
        expected.add(new Price("111", 1, 1, new Date(113,0,14,0,0,0),new Date(113,0,25,0,0,0),85));
        expected.add(new Price("111", 1, 1, new Date(113,0,25,0,0,0),new Date(113,0,31,0,0,0),90));
        expected.add(new Price("111", 1, 1, new Date(113,1,1,0,0,0),new Date(113,1,31,0,0,0),100));




        // actual merge result
        List<Price> actual = PriceMerger.merge(currentPrices,newPrices);

        assertEquals(expected.size(),actual.size());
        for (Price price: expected) {
            assertTrue(actual.contains(price));
        }

    }

}