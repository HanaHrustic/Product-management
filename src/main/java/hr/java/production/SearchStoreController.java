package hr.java.production;

import hr.java.production.model.Item;
import hr.java.production.model.Store;
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

public class SearchStoreController {
    private static final Logger logger = LoggerFactory.getLogger(SearchStoreController.class);

    @FXML
    private TableView<Store> tableView;
    @FXML
    private TableColumn<Store, String> name;
    @FXML
    private TableColumn<Store, String> address;
    @FXML
    private TextField storeNameFilter;
    @FXML
    private TextField storeWebAddressFilter;

    private List<Store> stores;

    public void initialize(){
        name.setCellValueFactory(new PropertyValueFactory<Store, String>("name"));
        address.setCellValueFactory(new PropertyValueFactory<Store, String>("webAddress"));
        stores = getStores();
        tableView.getItems().setAll(stores);
    }

    public void onSearch(){
        List<Store> filteredStore = stores.stream().filter(store -> store.getName().contains(storeNameFilter.getText())).collect(Collectors.toList());
        filteredStore = filteredStore.stream().filter(store -> store.getWebAddress().contains(storeWebAddressFilter.getText())).collect(Collectors.toList());
        tableView.getItems().setAll(filteredStore);
    }

    public static List<Store> getStores() {
        try {
            return Database.getAllStoresFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
