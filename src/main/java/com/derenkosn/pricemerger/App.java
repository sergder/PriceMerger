package com.derenkosn.pricemerger;

import com.derenkosn.pricemerger.model.Price;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App {

    public static void main(String[] args) {

        // existing prices
        List<Price> currentPrices = new ArrayList<>();
        currentPrices.add(new Price("122856", 1, 1, new Date(113,0,1,0,0,0),new Date(113,0,31,23,59,59),11000));
        currentPrices.add(new Price("122856", 2, 1, new Date(113,0,10,0,0,0),new Date(113,0,20,23,59,59),99000));
        currentPrices.add(new Price("6654", 1, 2, new Date(113,0,1,0,0,0),new Date(113,0,31,0,0,0),5000));


        // new prices
        List<Price> newPrices = new ArrayList<>();
        newPrices.add(new Price("122856", 1, 1, new Date(113,0,20,0,0,0),new Date(113,1,20,23,59,59),11000));
        newPrices.add(new Price("122856", 2, 1, new Date(113,0,15,0,0,0),new Date(113,1,25,23,59,59),92000));
        newPrices.add(new Price("6654", 1, 2, new Date(113,0,12,0,0,0),new Date(113,0,13,0,0,0),4000));

        List<Price> mergedPrices = PriceMerger.merge(currentPrices,newPrices);

        for (Price price: mergedPrices) {
            System.out.println(price);
        }

    }
}
