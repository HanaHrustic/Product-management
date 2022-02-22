package hr.java.production;

import hr.java.production.model.Address;
import hr.java.production.model.Factory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

import static hr.java.production.Database.*;
import static hr.java.production.SearchFactoryController.getAddresses;
import static hr.java.production.SearchFactoryController.getFactories;

public class AddNewFactoryController {
    private static final Logger logger = LoggerFactory.getLogger(SearchCategoryController.class);

    @FXML
    private TextField addNewFactoryName;
    @FXML
    private ChoiceBox<String> addNewFactoryAddress;
    @FXML
    private TextField addNewFactoryItem;

    private List<Factory> factories;
    private List<Address> addresses;

    public void initialize(){
        factories = getFactories();
        addresses = getAddresses();
        addNewFactoryAddress.getItems().setAll(addresses.stream().map(Address::getStreet).collect(Collectors.toList()));
    }

    public void onSave(){
        if (checkValidity()){
            Long addressId = addresses.stream().filter(address ->
                    address.getStreet().equals(
                            addNewFactoryAddress.getValue()
                    ))
                    .map(Address::getId).findFirst().orElse(1L);
            try {
                Long id = insertNewFactoryToDatabase(new Factory(null, addNewFactoryName.getText(), addressId));
                if(id != null){
                    for (String itemId : addNewFactoryItem.getText().split(",")) {
                        insertItemToFactoryDatabase(id, Long.valueOf(itemId));
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
        if(factories.stream().map(Factory::getName).toList().contains(addNewFactoryName.getText())){
            addNewFactoryName.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Ne smije postojati duplikat ime tvornice.\n";
        }
        else if(!addNewFactoryName.getText().matches("^[ A-Za-z.]+$") || addNewFactoryName.getText().equals("")){
            addNewFactoryName.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Ime tvornice ne smije sadrzavati specijalne znakove ili biti prazno.\n";
        }
        else{
            addNewFactoryName.setStyle("-fx-text-box-border: black;");
        }
        if(!addNewFactoryItem.getText().matches("^[([0-9])+,]+$")){
            addNewFactoryItem.setStyle("-fx-text-box-border: red;");
            valid = false;
            errors += "Artikli tvornice moraju biti u formatu (\"[([0-9])+,]\")\n";
        }
        else{
            addNewFactoryItem.setStyle("-fx-text-box-border: black;");
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
