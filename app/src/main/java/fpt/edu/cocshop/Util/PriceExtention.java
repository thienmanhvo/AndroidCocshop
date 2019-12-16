package fpt.edu.cocshop.Util;

import java.text.DecimalFormat;
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

    private static final String[] suffix = new String[]{"", "k", "m", "b", "t"};
    private static final int MAX_LENGTH = 4;

    public static String doubleToPriceWithK(Double price) {
        String r = new DecimalFormat("##0E0").format(price);
        r = r.replaceAll("E[0-9]", suffix[Character.getNumericValue(r.charAt(r.length() - 1)) / 3]);
        while (r.length() > MAX_LENGTH || r.matches("[0-9]+\\.[a-z]")) {
            r = r.substring(0, r.length() - 2) + r.substring(r.length() - 1);
        }
        return r;
    }
}
