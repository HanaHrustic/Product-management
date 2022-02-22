package hr.java.production.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * represents Item object and extends NamedEntity, holds value for category, width, height, length, productionCost, sellingPrice and discount
 */
public class Item extends NamedEntity {

    private static final Logger logger = LoggerFactory.getLogger(Item.class);

    Long categoryId;
    BigDecimal width;
    BigDecimal height;
    BigDecimal length;
    BigDecimal productionCost;
    BigDecimal sellingPrice;
    Discount discount;

    /**
     * @param name name of Item
     * @param width width of Item
     * @param height height of Item
     * @param length length of Item
     * @param productionCost productionCost of Item
     * @param sellingPrice sellingPrice of Item
     */
    public Item(Long id, Long categoryId, String name, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice) {
        super(id, name);
        this.categoryId = categoryId;
        this.width = width;
        this.height = height;
        this.length = length;
        this.productionCost = productionCost;
        this.sellingPrice = sellingPrice;

        logger.info("Kreiran artikl: " + this);
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(BigDecimal productionCost) {
        this.productionCost = productionCost;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getVolume(){
        return length.multiply(height.multiply(width));
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return categoryId.equals(item.categoryId) && width.equals(item.width) && height.equals(item.height) && length.equals(item.length) && productionCost.equals(item.productionCost) && sellingPrice.equals(item.sellingPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), categoryId, width, height, length, productionCost, sellingPrice, discount);
    }

    @Override
    public String toString() {
        return "Item{" +
                "sellingPrice=" + sellingPrice +
                ", name='" + name + '\'' +
                '}';
    }
}
