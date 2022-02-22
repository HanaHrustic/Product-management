package hr.java.production.enumeration;

public enum City {
    ZAGREB("Zagreb", 10000),
    NEW_YORK("New York", 10001),
    PARIS("Paris", 75000),
    BERLIN("Berlin", 10115);

    String name;
    int postalCode;

    private City(String name, int postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public int getPostalCode() {
        return postalCode;
    }
}