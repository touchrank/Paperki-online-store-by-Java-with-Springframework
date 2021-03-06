package com.kushnir.paperki.model.product;


import com.kushnir.paperki.model.Discount;

public class SearchProduct {
    private int id;
    private int pnt;
    private String imageName;
    private String fullName;
    private String shortName;
    private String link;
    private String categoryTranslitName;
    private String measure;
    private String countryFrom;
    private String countryMade;

    private double basePrice;
    private double basePriceWithVAT;
    private double finalPrice;
    private double finalPriceWithVAT;
    private int VAT;

    public SearchProduct() {
    }

    public SearchProduct(int id,
                         int pnt,
                         String imageName,
                         String fullName,
                         String shortName,
                         String link,
                         String categoryTranslitName,
                         String measure,
                         String countryFrom,
                         String countryMade,
                         double basePrice,
                         double basePriceWithVAT,
                         double finalPrice,
                         double finalPriceWithVAT,
                         int VAT) {
        this.id = id;
        this.pnt = pnt;
        this.imageName = imageName;
        this.fullName = fullName;
        this.shortName = shortName;
        this.link = link;
        this.categoryTranslitName = categoryTranslitName;
        this.measure = measure;
        this.countryFrom = countryFrom;
        this.countryMade = countryMade;
        this.basePrice = basePrice;
        this.basePriceWithVAT = basePriceWithVAT;
        this.finalPrice = finalPrice;
        this.finalPriceWithVAT = finalPriceWithVAT;
        this.VAT = VAT;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public String getCategoryTranslitName() {
        return categoryTranslitName;
    }

    public void setCategoryTranslitName(String categoryTranslitName) {
        this.categoryTranslitName = categoryTranslitName;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getCountryFrom() {
        return countryFrom;
    }

    public void setCountryFrom(String countryFrom) {
        this.countryFrom = countryFrom;
    }

    public String getCountryMade() {
        return countryMade;
    }

    public void setCountryMade(String countryMade) {
        this.countryMade = countryMade;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchProduct that = (SearchProduct) o;

        if (id != that.id) return false;
        if (pnt != that.pnt) return false;
        if (Double.compare(that.basePrice, basePrice) != 0) return false;
        if (Double.compare(that.basePriceWithVAT, basePriceWithVAT) != 0) return false;
        if (Double.compare(that.finalPrice, finalPrice) != 0) return false;
        if (Double.compare(that.finalPriceWithVAT, finalPriceWithVAT) != 0) return false;
        if (VAT != that.VAT) return false;
        if (imageName != null ? !imageName.equals(that.imageName) : that.imageName != null) return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null) return false;
        if (shortName != null ? !shortName.equals(that.shortName) : that.shortName != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (categoryTranslitName != null ? !categoryTranslitName.equals(that.categoryTranslitName) : that.categoryTranslitName != null)
            return false;
        if (measure != null ? !measure.equals(that.measure) : that.measure != null) return false;
        if (countryFrom != null ? !countryFrom.equals(that.countryFrom) : that.countryFrom != null) return false;
        return countryMade != null ? countryMade.equals(that.countryMade) : that.countryMade == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + pnt;
        result = 31 * result + (imageName != null ? imageName.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (categoryTranslitName != null ? categoryTranslitName.hashCode() : 0);
        result = 31 * result + (measure != null ? measure.hashCode() : 0);
        result = 31 * result + (countryFrom != null ? countryFrom.hashCode() : 0);
        result = 31 * result + (countryMade != null ? countryMade.hashCode() : 0);
        temp = Double.doubleToLongBits(basePrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(basePriceWithVAT);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(finalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(finalPriceWithVAT);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + VAT;
        return result;
    }

    @Override
    public String toString() {
        return "SearchProduct{" +
                "id=" + id +
                ", pnt=" + pnt +
                ", imageName='" + imageName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", link='" + link + '\'' +
                ", categoryTranslitName='" + categoryTranslitName + '\'' +
                ", measure='" + measure + '\'' +
                ", countryFrom='" + countryFrom + '\'' +
                ", countryMade='" + countryMade + '\'' +
                ", basePrice=" + basePrice +
                ", basePriceWithVAT=" + basePriceWithVAT +
                ", finalPrice=" + finalPrice +
                ", finalPriceWithVAT=" + finalPriceWithVAT +
                ", VAT=" + VAT +
                '}';
    }
}
