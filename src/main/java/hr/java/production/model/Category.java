package hr.java.production.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * represents Category object and extends namedEntity, holds value for description
 * */
public class Category extends NamedEntity {

    private static final Logger logger = LoggerFactory.getLogger(Category.class);

    String description;

    /**
     * constructor for categoty
     * @param name name of category
     * @param description description of category
     */
    public Category(Long id, String name, String description) {
        super(id, name);
        this.description = description;

        logger.info("Kreirana kategorija: " + this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return description.equals(category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description);
    }
}
