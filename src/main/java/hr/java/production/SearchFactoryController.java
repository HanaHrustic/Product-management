package hr.java.production;

import hr.java.production.enumeration.City;
import hr.java.production.model.Address;
import hr.java.production.model.Factory;
import hr.java.production.model.Item;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static hr.java.production.Database.getAddressById;


public class SearchFactoryController {
    private static final Logger logger = LoggerFactory.getLogger(SearchFactoryController.class);

    @FXML
    private TableView<Factory> tableView;
    @FXML
    private TableColumn<Factory, String> name;
    @FXML
    private TableColumn<Factory, String> address;
    @FXML
    private TableColumn<Factory, String> city;
    @FXML
    private TextField factoryNameFilter;
    @FXML
    private TextField factoryAddressFilter;

    private List<Factory> factories;

    public void initialize(){
        name.setCellValueFactory(new PropertyValueFactory<Factory, String>("name"));
        address.setCellValueFactory(celldata -> new SimpleStringProperty(getAddressById(celldata.getValue().getAddressId()).getStreet() + " " + getAddressById(celldata.getValue().getAddressId()).getHouseNumber()));
        city.setCellValueFactory(celldata -> new SimpleStringProperty(getAddressById(celldata.getValue().getAddressId()).getCity()));
        factories = getFactories();
        tableView.getItems().setAll(factories);
    }

    public void onSearch(){
        List<Factory> filteredFactory = factories.stream().filter(factory -> factory.getName().contains(factoryNameFilter.getText())).collect(Collectors.toList());
        filteredFactory = filteredFactory.stream().filter(factory -> getAddressById(factory.getAddressId()).getStreet().contains(factoryAddressFilter.getText())
                || getAddressById(factory.getAddressId()).getHouseNumber().contains(factoryAddressFilter.getText())).collect(Collectors.toList());
        tableView.getItems().setAll(filteredFactory);
    }

    public static List<Factory> getFactories() {
        try {
            return Database.getAllFactoriesFromDatabase();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Address> getAddresses() {
        try {
            return Database.getAllAddressesFromDatabase();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
