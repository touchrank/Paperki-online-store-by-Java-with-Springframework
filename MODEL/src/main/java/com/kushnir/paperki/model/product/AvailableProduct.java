package com.kushnir.paperki.model.product;

import com.kushnir.paperki.model.Discount;
import com.kushnir.paperki.model.Price;
import com.kushnir.paperki.model.Stock;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class AvailableProduct {
    private int id;
    private int pnt;
    private String fullName;
    private String shortName;
    private String link;
    private double basePrice;
    private double basePriceWithVAT;
    private double finalPrice;
    private double finalPriceWithVAT;
    private int VAT;
    private int quantityAvailable;
    private int availableDay;
    private Discount discount;
    private HashMap<Integer, Price> prices = new LinkedHashMap<>();

    public AvailableProduct() {
    }

    public AvailableProduct(int pnt,
                            int VAT,
                            int quantityAvailable,
                            Discount discount) {
        this.pnt = pnt;
        this.VAT = VAT;
        this.quantityAvailable = quantityAvailable;
        this.discount = discount;
    }

    public AvailableProduct(int pnt,
                            int VAT,
                            int quantityAvailable,
                            Discount discount,
                            HashMap<Integer, Price> prices) {
        this.pnt = pnt;
        this.VAT = VAT;
        this.quantityAvailable = quantityAvailable;
        this.discount = discount;
        this.prices = prices;
    }

    public AvailableProduct(int id,
                            int pnt,
                            String fullName,
                            String shortName,
                            String link,
                            double basePrice,
                            double basePriceWithVAT,
                            double finalPrice,
                            double finalPriceWithVAT,
                            int VAT,
                            int quantityAvailable,
                            Discount discount) {
        this.id = id;
        this.pnt = pnt;
        this.fullName = fullName;
        this.shortName = shortName;
        this.link = link;
        this.basePrice = basePrice;
        this.basePriceWithVAT = basePriceWithVAT;
        this.finalPrice = finalPrice;
        this.finalPriceWithVAT = finalPriceWithVAT;
        this.VAT = VAT;
        this.quantityAvailable = quantityAvailable;
        this.discount = discount;
    }

    public AvailableProduct(int pnt,
                            String fullName,
                            String shortName,
                            int VAT,
                            int quantityAvailable,
                            Discount discount,
                            HashMap<Integer, Price> prices) {
        this.pnt = pnt;
        this.fullName = fullName;
        this.shortName = shortName;
        this.VAT = VAT;
        this.quantityAvailable = quantityAvailable;
        this.discount = discount;
        this.prices = prices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPnt() {
        return pnt;
    }

    public void setPnt(int pnt) {
        this.pnt = pnt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getBasePriceWithVAT() {
        return basePriceWithVAT;
    }

    public void setBasePriceWithVAT(double basePriceWithVAT) {
        this.basePriceWithVAT = basePriceWithVAT;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getFinalPriceWithVAT() {
        return finalPriceWithVAT;
    }

    public void setFinalPriceWithVAT(double finalPriceWithVAT) {
        this.finalPriceWithVAT = finalPriceWithVAT;
    }

    public int getVAT() {
        return VAT;
    }

    public void setVAT(int VAT) {
        this.VAT = VAT;
    }

    public int getAvailableDay() {
        return availableDay;
    }

    public void setAvailableDay(int availableDay) {
        this.availableDay = availableDay;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public HashMap<Integer, Price> getPrices() {
        return prices;
    }

    public void setPrices(HashMap<Integer, Price> prices) {
        this.prices = prices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvailableProduct that = (AvailableProduct) o;

        if (id != that.id) return false;
        if (pnt != that.pnt) return false;
        if (Double.compare(that.basePrice, basePrice) != 0) return false;
        if (Double.compare(that.basePriceWithVAT, basePriceWithVAT) != 0) return false;
        if (Double.compare(that.finalPrice, finalPrice) != 0) return false;
        if (Double.compare(that.finalPriceWithVAT, finalPriceWithVAT) != 0) return false;
        if (VAT != that.VAT) return false;
        if (quantityAvailable != that.quantityAvailable) return false;
        if (availableDay != that.availableDay) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (shortName != null ? !shortName.equals(that.shortName) : that.shortName != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (discount != null ? !discount.equals(that.discount) : that.discount != null) return false;
        return prices != null ? prices.equals(that.prices) : that.prices == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + pnt;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        temp = Double.doubleToLongBits(basePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(basePriceWithVAT);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(finalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(finalPriceWithVAT);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + VAT;
        result = 31 * result + quantityAvailable;
        result = 31 * result + availableDay;
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (prices != null ? prices.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AvailableProduct{" +
                "id=" + id +
                ", pnt=" + pnt +
                ", fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", link='" + link + '\'' +
                ", basePrice=" + basePrice +
                ", basePriceWithVAT=" + basePriceWithVAT +
                ", finalPrice=" + finalPrice +
                ", finalPriceWithVAT=" + finalPriceWithVAT +
                ", VAT=" + VAT +
                ", quantityAvailable=" + quantityAvailable +
                ", availableDay=" + availableDay +
                ", discount=" + discount +
                ", prices=" + prices +
                '}';
    }
}
