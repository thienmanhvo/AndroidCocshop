package fpt.edu.cocshop.Util;

import java.text.DecimalFormat;

public class DoubleHandler {

    public static String doubleDisplayDecimalPlaces(double number, int place) {
        String strPlace = "";
        for (int i = 0; i < place; i++) {
            strPlace += "#";
        }
        if (place < 1) {
            strPlace = "#";
        }
        DecimalFormat df2 = new DecimalFormat("#." + strPlace);
        return df2.format(number);
    }
}
