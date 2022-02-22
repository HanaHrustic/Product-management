package hr.java.production;

import hr.java.production.model.Factory;
import hr.java.production.model.Store;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

import static hr.java.production.Database.insertItemToStoreDatabase;
import static hr.java.production.Database.insertNewStoreToDatabase;
import static hr.java.production.SearchStoreController.getStores;

public class AddNewStoreController {
    private static final Logger logger = LoggerFactory.getLogger(SearchCategoryController.class);

    @FXML
    private TextField addNewStoreName;
    @FXML
    private TextField addNewStoreAddress;
    @FXML
    private TextField addNewStoreItems;

    private List<Store> stores;

    public void initialize() {
        stores = getStores();
    }

    public void onSave(){
        if (checkValidity()){
            try {
                Long id = insertNewStoreToDatabase(new Store(null, addNewStoreName.getText(), addNewStoreAddress.getText()));
                if(id != null){
                    for (String itemId : addNewStoreItems.getText().split(",")) {
                        insertItemToStoreDatabase(id, Long.valueOf(itemId));
                    }
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
            initialize();
        }
    }

    private boolean checkValidity(){
        boolean valid = true;
        String errors = "";
        if(stores.stream().map(Store::getName).toList().contains(addNewStoreName.getText())){
            addNewStoreName.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Ime trgovine ne smije se ponavljati.";
        }
        else if(!addNewStoreName.getText().matches("^[ A-Za-z.]+$") || addNewStoreName.getText().equals("")){
            addNewStoreName.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Ime trgvoine ne smije sadrzavati specijalne znakove ili biti prazno.\n";
        }
        else{
            addNewStoreName.setStyle("-fx-text-box-border: black;");
        }

        if(!addNewStoreItems.getText().matches("^[([0-9])+,]+$")){
            addNewStoreItems.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Artikli trgovine moraju biti u formatu (\"[([0-9])+,]\")\n";
        }
        else{
            addNewStoreItems.setStyle("-fx-text-box-border: black;");
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
