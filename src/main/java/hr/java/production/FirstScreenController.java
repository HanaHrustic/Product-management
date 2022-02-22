package hr.java.production;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class FirstScreenController {
    public void showItemSearchScreen() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource(
                        "itemSearch.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 550);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.getStage().setScene(scene);
        Main.getStage().show();
    }
    public void showCategorySearchScreen() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource(
                        "categorySearch.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 550);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.getStage().setScene(scene);
        Main.getStage().show();
    }
    public void showStoreSearchScreen() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource(
                        "storeSearch.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 550);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.getStage().setScene(scene);
        Main.getStage().show();
    }
    public void showFactorySearchScreen() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource(
                        "factorySearch.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 550);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.getStage().setScene(scene);
        Main.getStage().show();
    }
    public void showAddItemScreen() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource(
                        "addNewItemScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 550);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.getStage().setScene(scene);
        Main.getStage().show();
    }
    public void showAddCategoryScreen() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource(
                        "addNewCategoryScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 550);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.getStage().setScene(scene);
        Main.getStage().show();
    }
    public void showAddFactoryScreen() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource(
                        "addNewFactoryScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 550);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.getStage().setScene(scene);
        Main.getStage().show();
    }
    public void showAddStoreScreen() {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource(
                        "addNewStoreScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 550);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.getStage().setScene(scene);
        Main.getStage().show();
    }
}
