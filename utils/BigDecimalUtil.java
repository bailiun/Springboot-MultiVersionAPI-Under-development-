package org.bailiun.multipleversionscoexist.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtil {
    public static BigDecimal toBigDecimal(Object obj) {
        if (obj == null) return BigDecimal.ZERO;
        String str = String.valueOf(obj).trim();

        if (str.isEmpty() || "null".equalsIgnoreCase(str)) {
            return BigDecimal.ZERO;
        }

        try {
            return new BigDecimal(str);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;         }
    }

    public static BigDecimal safeDivide(Object numerator, Object denominator, int scale) {
        BigDecimal num = toBigDecimal(numerator);
        BigDecimal den = toBigDecimal(denominator);

        if (den.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;        }

        return num.divide(den, scale, RoundingMode.HALF_UP);
    }
}

