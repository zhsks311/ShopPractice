package com.shop.display;

import java.text.NumberFormat;

public class ProductUtils {
    public static String priceToStringWithComma(long price) {
        return NumberFormat.getIntegerInstance()
                           .format(price);
    }
}
