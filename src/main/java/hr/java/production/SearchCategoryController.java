package hr.java.production;


import hr.java.production.exception.DuplicateCategoryException;
import hr.java.production.model.Category;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchCategoryController {

    private static final Logger logger = LoggerFactory.getLogger(SearchCategoryController.class);

    @FXML
    private TableView<Category> tableView;
    @FXML
    private TableColumn<Category, String> name;
    @FXML
    private TableColumn<Category, String> description;
    @FXML
    private TextField categoryNameFilter;
    @FXML
    private TextField categoryDescriptionFilter;

    private List<Category> categories;

    public void initialize() {
        name.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
        description.setCellValueFactory(new PropertyValueFactory<Category, String>("description"));
        categories = getCategories();
        tableView.getItems().setAll(categories);
    }

    public void onSearch() {
        List<Category> filteredCategory = categories.stream().filter(category -> category.getName().contains(categoryNameFilter.getText())).collect(Collectors.toList());
        filteredCategory = filteredCategory.stream().filter(category -> category.getDescription().contains(categoryDescriptionFilter.getText())).collect(Collectors.toList());
        tableView.getItems().setAll(filteredCategory);
    }

    public static List<Category> getCategories() {
        try {
            return Database.getAllCategoriesFromDatabase();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
