package hr.java.production.model;

import hr.java.production.enumeration.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * represents Address object and holds value for street, houseNumber, city, postalCode
 */
public class Address extends NamedEntity {

    private static final Logger logger = LoggerFactory.getLogger(Address.class);

    private String street;
    private String houseNumber;
    private String city;
    private int postalCode;

    /**
     * Builder class for Address, implements the builder pattern
     */
    public static class Builder {
        private Long id;
        private String street;
        private String houseNumber;
        private String city;
        private int postalCode;

        public Builder() {}


        public Builder withId(Long id){
            this.id = id;
            return this;
        }
        /**
         * @param street text value of street
         * @return reference to address object that is being built
         */

        public Builder withStreet(String street){
            this.street = street;
            return this;
        }

        /**
         * @param houseNumber text value of houseNumber
         * @return reference to address object that is being built
         */
        public Builder withHouseNumber(String houseNumber){
            this.houseNumber = houseNumber;
            return this;
        }

        /**
         * @param city text value of city
         * @return reference to address object that is being built
         */
        public Builder withCity(String city){
            this.city = city;
            return this;
        }

        public Builder withPostalCode(int postalCode){
            this.postalCode = postalCode;
            return this;
        }

        /**
         * builds new address object
         * @return newly created address object
         */
        public Address build(){
            Address address = new Address();
            address.id = this.id;
            address.street = this.street;
            address.houseNumber = this.houseNumber;
            address.city = this.city;
            address.postalCode = this.postalCode;

            logger.info("Kreirana adresa: " + address);

            return address;
        }
    }

    /**
     * default constructor for Address
     */
    private Address(){}

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return street.equals(address.street) && houseNumber.equals(address.houseNumber) && city.equals(address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, houseNumber, city);
    }
}
