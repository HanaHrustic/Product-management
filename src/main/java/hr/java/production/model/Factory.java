package hr.java.production.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Set;

/**
 * represents Factory object and extends NamedEntity, holds value for address and array of items
 */
public class Factory extends NamedEntity {

    private static final Logger logger = LoggerFactory.getLogger(Factory.class);

    Long addressId;

    /**
     * @param name name of Factory
     */
    public Factory(Long id, String name, Long addressId) {
        super(id, name);
        this.addressId = addressId;

        logger.info("Kreirana tvornica: " + this);
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Factory factory = (Factory) o;
        return addressId.equals(factory.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), addressId);
    }
}
