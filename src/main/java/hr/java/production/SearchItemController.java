package hr.java.production;

import hr.java.production.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static hr.java.production.Database.getCategoryById;

public class SearchItemController {

    private static final Logger logger = LoggerFactory.getLogger(SearchItemController.class);

    @FXML
    private TableView<Item> tableView;
    @FXML
    private TableColumn<Item, String> name;
    @FXML
    private TableColumn<Item, String> category;
    @FXML
    private TableColumn<Item, BigDecimal> width;
    @FXML
    private TableColumn<Item, BigDecimal> height;
    @FXML
    private TableColumn<Item, BigDecimal> length;
    @FXML
    private TableColumn<Item, BigDecimal> productionCost;
    @FXML
    private TableColumn<Item, BigDecimal> sellingPrice;
    @FXML
    private TextField itemNameFilter;
    @FXML
    private ChoiceBox<String> categoryChoiceBox;

    private List<Item> items;

    public void initialize(){
        name.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        category.setCellValueFactory(celldata -> new SimpleStringProperty(getCategoryById(celldata.getValue().getCategoryId()).getName()));
        width.setCellValueFactory(new PropertyValueFactory<Item, BigDecimal>("width"));
        height.setCellValueFactory(new PropertyValueFactory<Item, BigDecimal>("height"));
        length.setCellValueFactory(new PropertyValueFactory<Item, BigDecimal>("length"));
        productionCost.setCellValueFactory(new PropertyValueFactory<Item, BigDecimal>("productionCost"));
        sellingPrice.setCellValueFactory(new PropertyValueFactory<Item, BigDecimal>("sellingPrice"));
        items = getItems();
        tableView.getItems().setAll(items);
        categoryChoiceBox.getItems().setAll(SearchCategoryController.getCategories().stream().map(NamedEntity::getName).collect(Collectors.toList()));
    }

    public void onSearch(){
        List<Item> filteredItems = items.stream().filter(item -> item.getName().contains(itemNameFilter.getText())).collect(Collectors.toList());
        if(categoryChoiceBox.getValue() != null){
            filteredItems = filteredItems.stream().filter(item -> getCategoryById(item.getCategoryId()).getName().equals(categoryChoiceBox.getValue())).collect(Collectors.toList());
        }
        tableView.getItems().setAll(filteredItems);
    }


    public static List<Item> getItems() {
        try {
            return Database.getAllItemsFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
