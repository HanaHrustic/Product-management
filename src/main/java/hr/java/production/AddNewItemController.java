package hr.java.production;

import hr.java.production.model.Category;
import hr.java.production.model.Item;
import hr.java.production.model.NamedEntity;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

import static hr.java.production.Database.insertNewItemToDatabase;
import static hr.java.production.SearchCategoryController.getCategories;
import static hr.java.production.SearchItemController.getItems;

public class AddNewItemController {
    private static final Logger logger = LoggerFactory.getLogger(SearchCategoryController.class);

    @FXML
    private TextField addNewItemName;
    @FXML
    private ChoiceBox<String> addNewItemCategory;
    @FXML
    private TextField addNewItemWidth;
    @FXML
    private TextField addNewItemHeight;
    @FXML
    private TextField addNewItemLength;
    @FXML
    private TextField addNewItemProductionCost;
    @FXML
    private TextField addNewItemSellingPrice;

    private List<Item> items;
    private List<Category> categories;

    public void initialize(){
        addNewItemCategory.getItems().setAll(SearchCategoryController.getCategories().stream().map(NamedEntity::getName).collect(Collectors.toList()));
        items = getItems();
        categories = getCategories();
    }

    public void onSave(){
        if (checkValidity()){
            Long categoryId = categories.stream().filter(category -> category.getName().equals(addNewItemCategory.getValue())).map(Category::getId).findFirst().orElse(1L);
            try {
                insertNewItemToDatabase(new Item(null, categoryId, addNewItemName.getText(), BigDecimal.valueOf(Long.parseLong(addNewItemWidth.getText())), BigDecimal.valueOf(Long.parseLong(addNewItemHeight.getText())), BigDecimal.valueOf(Long.parseLong(addNewItemLength.getText())), BigDecimal.valueOf(Long.parseLong(addNewItemProductionCost.getText())), BigDecimal.valueOf(Long.parseLong(addNewItemSellingPrice.getText()))));
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
            initialize();
        }
    }

    private boolean checkValidity(){
        boolean valid = true;
        String errors = "";
        if(!addNewItemName.getText().matches("^[ A-Za-z]+$")){
            addNewItemName.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Ime smije sadržavati slova i razmake\n";
        }
        else if (addNewItemName.getText().equals("")) {
            addNewItemName.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Ime mora biti uneseno\n";
        }
        else{
            addNewItemName.setStyle("-fx-text-box-border: black;");
        }
        if(addNewItemWidth.getText().equals("")){
            addNewItemWidth.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Širina mora biti unesena\n";
        }
        else if (!addNewItemWidth.getText().matches("\\d+(\\.\\d+)?")) {
            addNewItemWidth.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Širina mora biti broj\n";
        }
        else{
            addNewItemWidth.setStyle("-fx-text-box-border: black;");
        }
        if(addNewItemLength.getText().equals("")){
            addNewItemLength.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Duljina mora biti unesena\n";
        }
        else if (!addNewItemLength.getText().matches("\\d+(\\.\\d+)?")) {
            addNewItemLength.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Duljina mora biti broj\n";
        }
        else{
            addNewItemLength.setStyle("-fx-text-box-border: black;");
        }
        if(addNewItemHeight.getText().equals("")){
            addNewItemHeight.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Visina mora biti unesena\n";
        }
        else if (!addNewItemHeight.getText().matches("\\d+(\\.\\d+)?")) {
            addNewItemHeight.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Visina mora biti broj\n";
        }
        else{
            addNewItemHeight.setStyle("-fx-text-box-border: black;");
        }
        if(addNewItemSellingPrice.getText().equals("")){
            addNewItemSellingPrice.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Prodajna cijena mora biti unesena\n";
        }
        else if (!addNewItemSellingPrice.getText().matches("\\d+(\\.\\d+)?")) {
            addNewItemSellingPrice.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Prodajna cijena mora biti broj\n";
        }
        else{
            addNewItemSellingPrice.setStyle("-fx-text-box-border: black;");
        }
        if(addNewItemProductionCost.getText().equals("")){
            addNewItemProductionCost.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Cijena proizvodnje mora biti unesena\n";
        }
        else if (!addNewItemProductionCost.getText().matches("\\d+(\\.\\d+)?")) {
            addNewItemProductionCost.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Cijena proizvodnje mora biti broj\n";
        }
        else{
            addNewItemProductionCost.setStyle("-fx-text-box-border: black;");
        }


        if(valid){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Success");
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(errors);
            alert.show();
        }

        return valid;
    }
}
