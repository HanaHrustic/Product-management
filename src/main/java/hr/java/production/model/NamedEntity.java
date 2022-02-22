package hr.java.production.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Objects;

/**
 * represents NamedEntity object and holds value for name
 */
public abstract class NamedEntity implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(NamedEntity.class);

    String name;
    Long id;

    /**
     * @param name name of NamedEntity
     */
    public NamedEntity(Long id, String name) {
        this.id = id;
        this.name = name;
        logger.info("Kreiran imenovani objekt sa imenom: " + name);
    }

    public NamedEntity(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamedEntity that = (NamedEntity) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}