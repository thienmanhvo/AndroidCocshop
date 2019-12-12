package fpt.edu.cocshop.Util;

import java.text.NumberFormat;


public class PriceExtention {

    public static String priceToString(String price, String commaType) {
        return price.replace(commaType, "");
    }

    public static String longToPrice(long price, String commaType) {
        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true); // this will also round numbers, 3
        return myFormat.format(price);
    }
}
