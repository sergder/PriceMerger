package com.derenkosn.pricemerger;

import com.derenkosn.pricemerger.model.Price;

import java.util.*;

public class PriceMerger {

    public static List<Price> merge(List<Price> currentPrices, List<Price> newPrices) {

        List<Price> currentPricesTemp = new ArrayList<>(currentPrices);
        List<Price> newPricesTemp = new ArrayList<>(newPrices);

        List<Price> result = new ArrayList<>();

        while (currentPricesTemp.size() > 0 || newPricesTemp.size() > 0) {

            List<Price> currentSame = getSame(currentPricesTemp);
            List<Price> newSame = getSame(newPricesTemp);

            result.addAll(mergeSame(currentSame,newSame));
        }

        return result;
    }

    private static List<Price> getSame(List<Price> prices) {
        List<Price> same = new ArrayList<>();
        Iterator<Price> iteratorCurrent = prices.iterator();

        same.add(iteratorCurrent.next());
        iteratorCurrent.remove();

        while (iteratorCurrent.hasNext()) {
            Price p = iteratorCurrent.next();
            if (p.isSame(same.get(0))) {
                same.add(p);
                iteratorCurrent.remove();
            }
        }

        Collections.sort(same, new Comparator<Price>() {
            @Override
            public int compare(Price o1, Price o2) {
                return o1.getBegin().before(o2.getBegin())?-1:0;
            }
        });

        return same;
    }

    private static List<Price> mergeSame(List<Price> currentSame, List<Price> newSame) {
        List<Price> result = new ArrayList<>();

        ListIterator<Price> iteratorCurrent = currentSame.listIterator();
        ListIterator<Price> iteratorNew = newSame.listIterator();

        boolean changeCurrent = true, changeNew = true;
        Price currentPrice = null, newPrice = null;

        while (iteratorCurrent.hasNext() && iteratorNew.hasNext()) {

            if (changeCurrent) {
                currentPrice = iteratorCurrent.next();
            }
            if (changeNew) {
                newPrice = iteratorNew.next();
            }

            if (currentPrice.getEnd().before(newPrice.getBegin()) || currentPrice.getEnd().equals(newPrice.getBegin())) { //цены не пересеклись
                result.add(currentPrice);
                iteratorCurrent.remove();
                changeCurrent = true;
                changeNew = false;
            } else if (newPrice.getEnd().before(currentPrice.getBegin()) || newPrice.getEnd().equals(currentPrice.getBegin())) { //цены не пересеклись
                result.add(newPrice);
                iteratorNew.remove();
                changeCurrent = false;
                changeNew = true;
            } else if (currentPrice.getBegin().before(newPrice.getBegin()) && currentPrice.getEnd().after(newPrice.getEnd())) { //старая поглотила новую
                if (currentPrice.getValue() == newPrice.getValue()) { //цены равны
                    result.add(currentPrice);
                } else { //цены не равны
                    if (!currentPrice.getBegin().equals(newPrice.getBegin())) {
                        result.add(new Price(currentPrice.getProductCode(), currentPrice.getNumber(), currentPrice.getDepartment(), currentPrice.getBegin(), newPrice.getBegin(), currentPrice.getValue()));
                    }
                    result.add(newPrice);
                    if (!newPrice.getEnd().equals(currentPrice.getEnd())) {
                        result.add(new Price(currentPrice.getProductCode(), currentPrice.getNumber(), currentPrice.getDepartment(), newPrice.getEnd(), currentPrice.getEnd(), currentPrice.getValue()));
                    }
                }
                iteratorNew.remove();
                iteratorCurrent.remove();
                changeCurrent = true;
                changeNew = true;
            } else if ((newPrice.getBegin().before(currentPrice.getBegin()) || newPrice.getBegin().equals(currentPrice.getBegin())) && (newPrice.getEnd().after(currentPrice.getEnd()) || newPrice.getEnd().equals(currentPrice.getEnd()))) { //новая поглотила старую

                result.add(newPrice);

                iteratorCurrent.remove();
                iteratorNew.remove();

                if (iteratorCurrent.hasNext()) {
                    currentSame.get(0).setBegin(newPrice.getEnd());
                }

                changeCurrent = true;
                changeNew = true;
            } else if (currentPrice.getEnd().after(newPrice.getBegin())) { //цены пересеклись, старая раньше
                if (currentPrice.getValue() == newPrice.getValue()) {
                    result.add(new Price(currentPrice.getProductCode(),currentPrice.getNumber(), currentPrice.getDepartment(), currentPrice.getBegin(), newPrice.getEnd(), currentPrice.getValue()));
                } else {
                    result.add(new Price(currentPrice.getProductCode(),currentPrice.getNumber(), currentPrice.getDepartment(), currentPrice.getBegin(), newPrice.getBegin(), currentPrice.getValue()));
                    result.add(newPrice);
                }
                iteratorCurrent.remove();
                iteratorNew.remove();
                if (iteratorCurrent.hasNext()) {
                    currentSame.get(0).setBegin(newPrice.getEnd());
                }
                changeCurrent = true;
                changeNew = true;
            } else if (newPrice.getEnd().after(currentPrice.getBegin())) { //цены пересеклись, новая раньше
                if (currentPrice.getValue() == newPrice.getValue()) {
                    result.add(new Price(currentPrice.getProductCode(),currentPrice.getNumber(), currentPrice.getDepartment(), newPrice.getBegin(), currentPrice.getEnd(), currentPrice.getValue()));
                } else {
                    result.add(newPrice);
                    //result.add(currentPrice);
                }
                iteratorNew.remove();
                currentPrice.setBegin(newPrice.getEnd());
                changeCurrent = false;
                changeNew = true;
            }
        }

        if (newSame.size() == 0) {
            result.addAll(currentSame);
        }

        if (currentSame.size() == 0) {
            result.addAll(newSame);
        }


        return result;
    }

}
