package hr.java.production;
import hr.java.production.model.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Database {
    private final static String DATABASE_PROPERTIES_FILE = "database.properties";




    private static Connection connectToDatabase() {
        try {
            Properties properties = new Properties();
            String path = new File(".").getAbsolutePath();
            properties.load(new FileReader(path.substring(0, path.length() - 1) + DATABASE_PROPERTIES_FILE));
            return DriverManager.getConnection(
                    properties.getProperty("databaseUrl")
                    ,
                    properties.getProperty("username")
                    ,
                    properties.getProperty("password"));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void closeDatabaseConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Category
    public static List<Category> getAllCategoriesFromDatabase() throws SQLException, IOException {
        Connection connection = connectToDatabase();
        List<Category> categoryList = new ArrayList<>();
        Statement sqlStatement = connection.createStatement();
        ResultSet categoryResultSet = sqlStatement.executeQuery(
                "SELECT * FROM category");
        while (categoryResultSet.next()) {
            Category newCategory = getCategoryFromResultSet(categoryResultSet);
            categoryList.add(newCategory);
        }
        closeDatabaseConnection(connection);
        return categoryList;
    }

    private static Category getCategoryFromResultSet(ResultSet categoryResultSet) throws SQLException {
        Long id = categoryResultSet.getLong("ID");
        String name = categoryResultSet.getString("NAME");
        String description = categoryResultSet.getString("DESCRIPTION");
        return new Category(id, name, description);
    }

    public static Category getCategoryById(Long id) {
        try {
            Connection connection = connectToDatabase();
            PreparedStatement sqlStatement = null;
            sqlStatement = connection.prepareStatement("SELECT * FROM category WHERE id = ?");
            sqlStatement.setString(1, id.toString());
            ResultSet categoryResultSet = sqlStatement.executeQuery();
            Category category = null;
            while (categoryResultSet.next()) {
                category = getCategoryFromResultSet(categoryResultSet);

            }
            closeDatabaseConnection(connection);
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void insertNewCategoryToDatabase(Category category) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Category (name, description) VALUES(?, ?)");
        stmt.setString(1, category.getName());
        stmt.setString(2, category.getDescription());
        stmt.executeUpdate();
        connection.close();
    }


    //Item
    public static List<Item> getAllItemsFromDatabase() throws SQLException, IOException {
        Connection connection = connectToDatabase();
        List<Item> itemList = new ArrayList<>();
        Statement sqlStatement = connection.createStatement();
        ResultSet itemResultSet = sqlStatement.executeQuery(
                "SELECT * FROM item");
        while (itemResultSet.next()) {
            Item newItem = getItemFromResultSet(itemResultSet);
            itemList.add(newItem);
        }
        closeDatabaseConnection(connection);
        return itemList;
    }

    private static Item getItemFromResultSet(ResultSet itemResultSet) throws SQLException {
        Long id = itemResultSet.getLong("ID");
        Long categoryId = itemResultSet.getLong("CATEGORY_ID");
        String name = itemResultSet.getString("NAME");
        BigDecimal width = itemResultSet.getBigDecimal("WIDTH");
        BigDecimal height = itemResultSet.getBigDecimal("HEIGHT");
        BigDecimal length = itemResultSet.getBigDecimal("LENGTH");
        BigDecimal production_cost = itemResultSet.getBigDecimal("PRODUCTION_COST");
        BigDecimal selling_price = itemResultSet.getBigDecimal("SELLING_PRICE");
        return new Item(id, categoryId, name, width, height, length, production_cost, selling_price);
    }

    public static Item getItemById(Long id) {
        try {
            Connection connection = connectToDatabase();
            PreparedStatement sqlStatement = null;
            sqlStatement = connection.prepareStatement("SELECT * FROM item WHERE id = ?");
            sqlStatement.setString(1, id.toString());
            ResultSet itemResultSet = sqlStatement.executeQuery();
            Item item = null;
            while (itemResultSet.next()) {
                item = getItemFromResultSet(itemResultSet);
            }
            closeDatabaseConnection(connection);
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void insertNewItemToDatabase(Item item) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Item (category_id, name, width, height, length, production_cost, selling_price) VALUES(?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, item.getCategoryId().toString());
        stmt.setString(2, item.getName());
        stmt.setString(3, item.getWidth().toString());
        stmt.setString(4, item.getHeight().toString());
        stmt.setString(5, item.getLength().toString());
        stmt.setString(6, item.getProductionCost().toString());
        stmt.setString(7, item.getSellingPrice().toString());
        stmt.executeUpdate();
        connection.close();
    }

    //Address
    public static List<Address> getAllAddressesFromDatabase() throws SQLException, IOException {
        Connection connection = connectToDatabase();
        List<Address> addressList = new ArrayList<>();
        Statement sqlStatement = connection.createStatement();
        ResultSet addressResultSet = sqlStatement.executeQuery(
                "SELECT * FROM address");
        while (addressResultSet.next()) {
            Address newAddress = getAddressFromResultSet(addressResultSet);
            addressList.add(newAddress);
        }
        closeDatabaseConnection(connection);
        return addressList;
    }

    private static Address getAddressFromResultSet(ResultSet addressResultSet) throws SQLException {
        Long id = addressResultSet.getLong("ID");
        String street = addressResultSet.getString("STREET");
        String houseNumber = addressResultSet.getString("HOUSE_NUMBER");
        String city = addressResultSet.getString("CITY");
        int postal_code = addressResultSet.getInt("POSTAL_CODE");
        return new Address.Builder().withId(id).withStreet(street).withHouseNumber(houseNumber).withCity(city).withPostalCode(postal_code).build();
    }

    public static Address getAddressById(Long id) {
        try {
            Connection connection = connectToDatabase();
            PreparedStatement sqlStatement = null;
            sqlStatement = connection.prepareStatement("SELECT * FROM address WHERE id = ?");
            sqlStatement.setString(1, id.toString());
            ResultSet addressResultSet = sqlStatement.executeQuery();
            Address address = null;
            while (addressResultSet.next()) {
                address = getAddressFromResultSet(addressResultSet);

            }
            closeDatabaseConnection(connection);
            return address;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void insertNewAddressToDatabase(Address address) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Address (street, house_number, city, postal_code) VALUES(?, ?, ?, ?)");
        stmt.setString(1, address.getStreet());
        stmt.setString(2, address.getHouseNumber());
        stmt.setString(3, address.getCity());
        stmt.setString(4, String.valueOf(address.getPostalCode()));
        stmt.executeUpdate();
        connection.close();
    }

    //Factory
    public static List<Factory> getAllFactoriesFromDatabase() throws SQLException, IOException {
        Connection connection = connectToDatabase();
        List<Factory> factoryList = new ArrayList<>();
        Statement sqlStatement = connection.createStatement();
        ResultSet factoryResultSet = sqlStatement.executeQuery(
                "SELECT * FROM factory");
        while (factoryResultSet.next()) {
            Factory newFactory = getFactoryFromResultSet(factoryResultSet);
            factoryList.add(newFactory);
        }
        closeDatabaseConnection(connection);
        return factoryList;
    }

    private static Factory getFactoryFromResultSet(ResultSet factoryResultSet) throws SQLException {
        Long id = factoryResultSet.getLong("ID");
        String name = factoryResultSet.getString("name");
        Long addressId = factoryResultSet.getLong("ADDRESS_ID");
        return new Factory(id, name, addressId);
    }

    public static Factory getFactoryById(Long id) {
        try {
            Connection connection = connectToDatabase();
            PreparedStatement sqlStatement = null;
            sqlStatement = connection.prepareStatement("SELECT * FROM factory WHERE id = ?");
            sqlStatement.setString(1, id.toString());
            ResultSet factoryResultSet = sqlStatement.executeQuery();
            Factory factory = null;
            while (factoryResultSet.next()) {
                factory = getFactoryFromResultSet(factoryResultSet);

            }
            closeDatabaseConnection(connection);
            return factory;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Item> getItemsByFactoryId(Long id) {
        try {
            Connection connection = connectToDatabase();
            PreparedStatement sqlStatement = null;
            sqlStatement = connection.prepareStatement("SELECT * FROM item JOIN factory_item on item.id = factory_item.item_id WHERE factory_item.item_id = ?;");
            sqlStatement.setString(1, id.toString());
            ResultSet itemResultSet = sqlStatement.executeQuery();
            List<Item> items = new ArrayList<>();
            while (itemResultSet.next()) {
                items.add(getItemFromResultSet(itemResultSet));
            }
            closeDatabaseConnection(connection);
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static Long insertNewFactoryToDatabase(Factory factory) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Factory (name, address_Id) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, factory.getName());
        stmt.setString(2, String.valueOf(factory.getAddressId()));
        stmt.executeUpdate();ResultSet rs = stmt.getGeneratedKeys();
        if(rs.next())
        {
            return rs.getLong(1);
        }
        connection.close();
        return null;
    }

    public static void insertItemToFactoryDatabase(Long factoryId, Long itemId) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Factory_item (factory_id, item_id) VALUES(?, ?)");
        stmt.setString(1, factoryId.toString());
        stmt.setString(2, itemId.toString());
        stmt.executeUpdate();
        connection.close();
    }

    //Store
    public static List<Store> getAllStoresFromDatabase() throws SQLException, IOException {
        Connection connection = connectToDatabase();
        List<Store> storeList = new ArrayList<>();
        Statement sqlStatement = connection.createStatement();
        ResultSet storeResultSet = sqlStatement.executeQuery(
                "SELECT * FROM store");
        while (storeResultSet.next()) {
            Store newFactory = getStoreFromResultSet(storeResultSet);
            storeList.add(newFactory);
        }
        closeDatabaseConnection(connection);
        return storeList;
    }

    private static Store getStoreFromResultSet(ResultSet storeResultSet) throws SQLException {
        Long id = storeResultSet.getLong("ID");
        String name = storeResultSet.getString("name");
        String webAddress = storeResultSet.getString("WEB_ADDRESS");
        return new Store(id, name, webAddress);
    }

    public static List<Item> getItemsByStoreId(Long id) {
        try {
            Connection connection = connectToDatabase();
            PreparedStatement sqlStatement = null;
            sqlStatement = connection.prepareStatement("SELECT * FROM item JOIN store_item on item.id = store_item.item_id WHERE store_item.item_id = ?;");
            sqlStatement.setString(1, id.toString());
            ResultSet itemResultSet = sqlStatement.executeQuery();
            List<Item> items = new ArrayList<>();
            while (itemResultSet.next()) {
                items.add(getItemFromResultSet(itemResultSet));
            }
            closeDatabaseConnection(connection);
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static Store getStoreById(Long id) {
        try {
            Connection connection = connectToDatabase();
            PreparedStatement sqlStatement = null;
            sqlStatement = connection.prepareStatement("SELECT * FROM store WHERE id = ?");
            sqlStatement.setString(1, id.toString());
            ResultSet storeResultSet = sqlStatement.executeQuery();
            Store store = null;
            while (storeResultSet.next()) {
                store = getStoreFromResultSet(storeResultSet);

            }
            closeDatabaseConnection(connection);
            return store;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Long insertNewStoreToDatabase(Store store) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Store (name, web_Address) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, store.getName());
        stmt.setString(2, store.getWebAddress());
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();
        if(rs.next())
        {
            return rs.getLong(1);
        }
        connection.close();
        return null;
    }

    public static void insertItemToStoreDatabase(Long storeId, Long itemId) throws SQLException, IOException {
        Connection connection = connectToDatabase();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Store_item (store_id, item_id) VALUES(?, ?)");
        stmt.setString(1, storeId.toString());
        stmt.setString(2, itemId.toString());
        stmt.executeUpdate();
        connection.close();
    }
}