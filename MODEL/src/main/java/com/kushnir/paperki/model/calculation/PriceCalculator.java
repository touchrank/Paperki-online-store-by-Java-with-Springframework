package com.kushnir.paperki.model.calculation;

import com.kushnir.paperki.model.Discount;
import com.kushnir.paperki.model.DiscountType;
import com.kushnir.paperki.model.Price;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class PriceCalculator {

    public static double getDouble(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static double getVatValue(double price, int VAT) {
        return getDouble(price * VAT/100d);
    }

    public static double getPriceWithVAT(double price, int VAT) {
        return getDouble(price + getVatValue(price, VAT));
    }

    public static double calculateTotalWithVAT(double total, int quantity, int VAT) {
        double totalVAT = getVatValue(total, VAT);
        return getDouble(total + totalVAT);
    }

    public static double calculateDiscountedPrice(Discount discount, double basePrice) {
        if (discount != null) {
            DiscountType discountType = discount.getDiscountType();
            double dValue = discount.getValueDouble();
            int iValue = discount.getValueInt();

            if (discountType != null) {
                switch (discountType) {
                    case PROCENT:
                        double val = basePrice * getDouble(iValue / 100.0);
                        return getDouble(basePrice - val);
                    case OVERRIDE:
                        return getDouble(dValue);
                    case SUBTRACT:
                        return getDouble(basePrice - dValue);
                    default:
                        return basePrice;
                }
            } else return basePrice;
        } else return basePrice;
    }

    public static double calculateQuantityPrice (HashMap<Integer, Price> prices, int quantity, double currentPrice) {
        double finalPrice = currentPrice;
        for (Map.Entry<Integer, Price> entry : prices.entrySet()) {
            Price price = entry.getValue();

            int quantityStart = price.getQuantityStart();

            if (quantity >= quantityStart && finalPrice >= price.getBasePrice()) {
                finalPrice = price.getBasePrice();
            }

        }
        return finalPrice;
    }

    public static double calculateCartDiscount(double amount) {

        if (amount >= 2000d) {
            return getDouble(amount - (amount * (8/100)));
        } else if (amount >= 1650d) {
            return getDouble(amount - (amount * (7/100)));
        } else if (amount >= 1300d) {
            return getDouble(amount - (amount * (6/100)));
        } else if (amount >= 950d) {
            return getDouble(amount - (amount * (5/100)));
        } else if (amount >= 650d) {
            return getDouble(amount - (amount * (4/100)));
        } else if (amount >= 400d) {
            return getDouble(amount - (amount * (3/100)));
        } else if (amount >= 200d) {
            return getDouble(amount - (amount * (2/100)));
        } else if (amount >= 100d) {
            return getDouble(amount - (amount * (1/100)));
        } else {
            return amount;
        }
    }
}
