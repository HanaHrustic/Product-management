package hr.java.production;

import hr.java.production.model.Category;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

import static hr.java.production.Database.insertNewCategoryToDatabase;
import static hr.java.production.SearchCategoryController.getCategories;
import static hr.java.production.SearchItemController.getItems;

public class AddNewCategoryController {
    private static final Logger logger = LoggerFactory.getLogger(SearchCategoryController.class);

    @FXML
    private TextField addNewCategoryName;
    @FXML
    private TextField addNewCategoryDescription;

    private List<Category> categories;

    public void initialize(){
        categories = getCategories();
    }

    public void onSave(){
        if (checkValidity()){
            try {
                insertNewCategoryToDatabase(new Category(null, addNewCategoryName.getText(), addNewCategoryDescription.getText()));
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
            initialize();
        }
    }

    private boolean checkValidity(){
        boolean valid = true;
        String errors = "";
        if(categories.stream().map(Category::getName).toList().contains(addNewCategoryName.getText())){
            addNewCategoryName.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Ne smije postojati duplikat ime kategorije.\n";
        }
        else if(!addNewCategoryName.getText().matches("^[ A-Za-z.]+$") || addNewCategoryName.getText().equals("")){
            addNewCategoryName.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Ime kategorije ne smije sadrzavati specijalne znakove ili biti prazno.\n";
        }
        else{
            addNewCategoryName.setStyle("-fx-text-box-border: black;");
        }
        if(addNewCategoryDescription.getText().equals("")){
            addNewCategoryDescription.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Opis kategorije ne smije biti prazno.";
        }
        else{
            addNewCategoryDescription.setStyle("-fx-text-box-border: black;");
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
