package hr.java.production.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * represents Discount record and holds value for discountAmount
 */
public record Discount(BigDecimal discountAmount) implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(Discount.class);

    /**
     * @param discountAmount discountAmount of Discount
     */
    public Discount(BigDecimal discountAmount){
        this.discountAmount = discountAmount;

        logger.info("Kreiran popust:" + discountAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return discountAmount.equals(discount.discountAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountAmount);
    }
}
