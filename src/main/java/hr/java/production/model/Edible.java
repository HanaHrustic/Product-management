package hr.java.production.model;

/**
 * interface for Edible Item
 */
public interface Edible {
    /**
     * @return calculated Kcal
     */
    int calculateKilocalories();

    /**
     * @return calculated price
     */
    float calculatePrice();


}
