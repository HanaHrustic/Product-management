package hr.java.production.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Set;

/**
 * represents Store object and extends NamedEntity, holds value for webAddress and array of items
 */
public class Store extends NamedEntity {

    private static final Logger logger = LoggerFactory.getLogger(Store.class);

    String webAddress;

    /**
     * @param name name of Store
     * @param webAddress webAddress of Store
     */
    public Store(Long id, String name, String webAddress) {
        super(id, name);
        this.webAddress = webAddress;

        logger.info("Kreirana trgovina: " + this);
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Store store = (Store) o;
        return webAddress.equals(store.webAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}
