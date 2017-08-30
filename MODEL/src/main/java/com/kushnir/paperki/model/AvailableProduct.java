package com.kushnir.paperki.model;

import java.util.HashMap;

public class AvailableProduct {
    private Integer pnt;
    private String fullName;
    private String shortName;
    private Integer VAT;
    private Integer quantityAvailable;
    private Discount discount;
    private HashMap<Integer, Price> prices = new HashMap<>();

    public AvailableProduct() {
    }

    public AvailableProduct(Integer pnt,
                            Integer VAT,
                            Integer quantityAvailable,
                            Discount discount) {
        this.pnt = pnt;
        this.VAT = VAT;
        this.quantityAvailable = quantityAvailable;
        this.discount = discount;
    }

    public AvailableProduct(Integer pnt,
                            Integer VAT,
                            Integer quantityAvailable,
                            Discount discount,
                            HashMap<Integer, Price> prices) {
        this.pnt = pnt;
        this.VAT = VAT;
        this.quantityAvailable = quantityAvailable;
        this.discount = discount;
        this.prices = prices;
    }

    public AvailableProduct(Integer pnt,
                            String fullName,
                            String shortName,
                            Integer VAT,
                            Integer quantityAvailable,
                            Discount discount) {
        this.pnt = pnt;
        this.fullName = fullName;
        this.shortName = shortName;
        this.VAT = VAT;
        this.quantityAvailable = quantityAvailable;
        this.discount = discount;
    }

    public AvailableProduct(Integer pnt,
                            String fullName,
                            String shortName,
                            Integer VAT,
                            Integer quantityAvailable,
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

    public Integer getPnt() {
        return pnt;
    }

    public void setPnt(Integer pnt) {
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

    public Integer getVAT() {
        return VAT;
    }

    public void setVAT(Integer VAT) {
        this.VAT = VAT;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
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

        if (pnt != null ? !pnt.equals(that.pnt) : that.pnt != null) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (shortName != null ? !shortName.equals(that.shortName) : that.shortName != null) return false;
        if (VAT != null ? !VAT.equals(that.VAT) : that.VAT != null) return false;
        if (quantityAvailable != null ? !quantityAvailable.equals(that.quantityAvailable) : that.quantityAvailable != null)
            return false;
        if (discount != null ? !discount.equals(that.discount) : that.discount != null) return false;
        return prices != null ? prices.equals(that.prices) : that.prices == null;
    }

    @Override
    public int hashCode() {
        int result = pnt != null ? pnt.hashCode() : 0;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (VAT != null ? VAT.hashCode() : 0);
        result = 31 * result + (quantityAvailable != null ? quantityAvailable.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (prices != null ? prices.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AvailableProduct{" +
                "pnt=" + pnt +
                ", fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", VAT=" + VAT +
                ", quantityAvailable=" + quantityAvailable +
                ", discount=" + discount +
                ", prices=" + prices +
                '}';
    }
}
