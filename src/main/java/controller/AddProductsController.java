package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Inventory;
import model.Part;
import model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.Optional;
import java.util.ResourceBundle;

import java.io.IOException;

import static model.Inventory.*;
import static model.Inventory.getAllParts;

/**
 *
 * @author
 * Duvall Roberts
 * drob801@wgu.edu
 * Student ID: 007792396
 */


/**

 This class represents the controller for adding products page in a JavaFX application.

 It contains all the necessary fields and methods to manage the UI and user interactions

 related to adding a new product to the inventory management system.
 */
public class AddProductsController implements Initializable {
    /**
     * Represents a new product.
     */
    Product productNew;

    /**
     * An observable list of parts that have been added.
     */
    ObservableList<Part> partAdded = FXCollections.observableArrayList();
    
    /**
     * Button used to cancel adding products.
     */
    @FXML
    private Button cancelAddProductsPage;

    /**
     * Button used to save added products.
     */
    @FXML
    private Button saveAddProduct;

    /**
     * Button used to remove a selected product.
     */
    @FXML
    private Button removeAddProduct;

    /**
     * Button used to add a new product.
     */
    @FXML
    private Button addAddProduct;

    /**
     * Text field for entering the ID of a product.
     */
    @FXML
    private TextField idAddProduct;

    /**
     * Text field for entering the name of a product.
     */
    @FXML
    private TextField nameAddProduct;

    /**
     * Text field for entering the inventory level of a product.
     */
    @FXML
    private TextField invAddProduct;

    /**
     * Text field for entering the price of a product.
     */
    @FXML
    private TextField priceAddProduct;

    /**
     * Text field for entering the maximum allowed quantity of a product.
     */
    @FXML
    private TextField maxAddProduct;

    /**
     * Text field for entering the minimum allowed quantity of a product.
     */
    @FXML
    private TextField minAddProduct;

    /**
     * Text field for searching for a product.
     */
    @FXML
    private TextField searchAddProduct;

    /**
     * Table view for displaying parts that can be added to a product.
     */
    @FXML
    private TableView<Part> partAddProductTable;

    /**
     * Table column for displaying the ID of a part in the add product table.
     */
    @FXML
    private TableColumn<Part, Integer> idPartAddProduct;

    /**
     * Table column for displaying the name of a part in the add product table.
     */
    @FXML
    private TableColumn<Part, String> namePartAddProduct;

    /**
     * Table column for displaying the inventory level of a part in the add product table.
     */
    @FXML
    private TableColumn<Part, Integer> invPartAddProduct;

    /**
     * Table column for displaying the price of a part in the add product table.
     */
    @FXML
    private TableColumn<Part, Double> unitPartAddProduct;

    /**
     * Table view for displaying parts that have been added to a product.
     */
    @FXML
    private TableView<Part> partAddProductTableAdded;

    /**
     * Table column for displaying the ID of a part in the added product table.
     */
    @FXML
    private TableColumn<Part, Integer> idPartAddedProduct;

    /**
     * Table column for displaying the name of a part in the added product table.
     */
    @FXML
    private TableColumn<Part, String> namePartAddedProduct;

    /**
     * Table column for displaying the inventory level of a part in the added product table.
     */
    @FXML
    private TableColumn<Part, Integer> invPartAddedProduct;

    /**
     * Table column for displaying the price of a part in the added product table.
     */
    @FXML
    private TableColumn<Part, Double> unitPartAddedProduct;

    /**
     * Takes the user back to the main screen when the "cancel" button is clicked on the add products page.
     * @throws IOException if the resource file for the main screen cannot be loaded
     */
    @FXML
    void onClickCancelAddProductsPageButton() throws IOException {

        Stage stage = (Stage)cancelAddProductsPage.getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/main/main-screen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();

    }

    /**
     * Updates the part table with all parts in the inventory.
     */
    public void updatePartTable() {
        partAddProductTable.setItems(Inventory.getAllParts());
    }

    /**
     * Initializes the controllers and populates the tables with data.
     *
     * @param url the location of the FXML file to be loaded
     * @param rb the resource bundle for the FXML file
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        updatePartTable();

        idPartAddProduct.setCellValueFactory(new PropertyValueFactory<>("id"));
        namePartAddProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
        invPartAddProduct.setCellValueFactory(new PropertyValueFactory<>("stock"));
        unitPartAddProduct.setCellValueFactory(new PropertyValueFactory<>("price"));

        productNew = new Product(0, null, 0.0, 0, 0, 0);
        partAdded = productNew.getAllAssociatedParts();
        partAddProductTableAdded.setItems(productNew.getAllAssociatedParts());

        idPartAddedProduct.setCellValueFactory(new PropertyValueFactory<>("id"));
        namePartAddedProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
        invPartAddedProduct.setCellValueFactory(new PropertyValueFactory<>("stock"));
        unitPartAddedProduct.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * Saves the added product to the inventory.
     *
     * This method retrieves the highest ID of all products in the inventory and increments it by one to assign
     * a new ID to the new product being added. It validates the input values entered by the user for the product's
     * inventory, price, minimum and maximum values. If the input values are valid, it creates a new product with the
     * entered details, assigns the new ID to the product, adds it to the inventory, and redirects to the main screen.
     * If the input values are invalid, it displays an alert message indicating the error.
     *
     * @param actionEvent The event triggered by the button click action.
     * @throws IOException If the FXML file for the main screen cannot be loaded.
     */
    @FXML
    public void onClickSaveAddProductsPageButton(ActionEvent actionEvent) throws IOException {
        int ID = 0;
        for(Product proDuct : Inventory.getAllProducts()) {
            if(proDuct.getIdProduct() > ID)
                ID = proDuct.getIdProduct();
        }
        ID++;

        try {
            if (!(Integer.class.isInstance(Integer.parseInt(invAddProduct.getText())))){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory needs to be a number!");
                alert.showAndWait();
            } else if (!(Double.class.isInstance(Double.parseDouble(priceAddProduct.getText())))){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Price needs to be a number!");
                alert.showAndWait();
            }
            else if (Integer.parseInt(minAddProduct.getText()) > Integer.parseInt(maxAddProduct.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Min value cannot be greater than Max value!");
                alert.showAndWait();
            } else if (Integer.parseInt(invAddProduct.getText()) > Integer.parseInt(maxAddProduct.getText()) || Integer.parseInt(invAddProduct.getText()) < Integer.parseInt(minAddProduct.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory amount must be between minimum and maximum!");
                alert.showAndWait();
            } else {
                String name = nameAddProduct.getText();
                int inventory = Integer.parseInt(invAddProduct.getText());
                double priceCost = Double.parseDouble(priceAddProduct.getText());
                int max = Integer.parseInt(maxAddProduct.getText());
                int min = Integer.parseInt(minAddProduct.getText());
                idAddProduct.setText(String.valueOf(ID));

                productNew.setIdProduct(ID);
                productNew.setName(name);
                productNew.setPrice(priceCost);
                productNew.setMax(max);
                productNew.setMin(min);
                productNew.setStock(inventory);

                Inventory.addProduct(productNew);

                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Object scene = FXMLLoader.load(getClass().getResource("/main/main-screen.fxml"));
                stage.setScene(new Scene((Parent) scene));
                stage.show();

                System.out.print("Works!");
            }
        } catch (NumberFormatException e){
            Alert catchAlert = new Alert(Alert.AlertType.WARNING);
            catchAlert.setContentText("Enter Value please!");
            catchAlert.showAndWait();
            System.out.print("Something is wrong!");
        }
    }


    /**
     * Removes the selected product from the list of added products.
     * Displays an error message if no product is selected.
     * Displays a confirmation dialog before removing the selected product.
     *
     * @param actionEvent The event that triggered the button click.
     */
    public void onClickRemoveAddProductsPageButton(ActionEvent actionEvent) {
        Part part = partAddProductTableAdded.getSelectionModel().getSelectedItem();

        if (part == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Part not selected!");
            alert.showAndWait();
        } else {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Want to delete!");
            Optional<ButtonType> answer = deleteAlert.showAndWait();
            if (answer.isPresent() && answer.get() == ButtonType.OK) {
                partAdded.remove(part);
                partAddProductTableAdded.setItems(partAdded);
            }
        }
    }

    /**
     * Adds the selected part to the list of associated parts for the new product.
     *
     * @param actionEvent The event that triggered the button click.
     */
    public void onClickAddAddProductsPageButton(ActionEvent actionEvent) {
        Part part = partAddProductTable.getSelectionModel().getSelectedItem();
        productNew.addAssociatedPart(part);
        partAdded = productNew.getAllAssociatedParts();
    }

    /**
     * This function is triggered when the searchAddProduct button is pressed. It searches for a part using the input provided
     * by the user and displays the results in the partAddProductTable. If no input is provided, it displays a warning alert.
     *
     * @param event The ActionEvent object for the searchAddProduct button press
     */
    public void searchAddProduct(ActionEvent event) {
        String partSearched = searchAddProduct.getText();

        if(partSearched.equals("")) {
            Alert partAlert = new Alert(Alert.AlertType.WARNING);
            partAlert.setTitle("Warning");
            partAlert.setContentText("You did not put ID nor Name");
            partAlert.showAndWait();
            partAddProductTable.setItems(getAllParts());
        } else {
            boolean finds = false;
            try {
                Part foundPart = lookupPart(Integer.parseInt(partSearched));
                if (foundPart != null) {
                    ObservableList<Part> parts = FXCollections.observableArrayList();
                    parts.add(foundPart);
                    partAddProductTable.setItems(parts);
                }
                else {
                    Alert partAlert = new Alert(Alert.AlertType.WARNING);
                    partAlert.setContentText("Does not match any Part ID");
                    partAlert.showAndWait();
                    partAddProductTable.setItems(getAllParts());
                }
            } catch (NumberFormatException e) {
                ObservableList<Part> partsAll = getAllParts();
                if(partsAll.isEmpty()){
                    Alert partAlert = new Alert(Alert.AlertType.WARNING);
                    partAlert.setContentText("No parts\nAdd parts to parts list first");
                    partAlert.showAndWait();
                    partAddProductTable.setItems(getAllParts());
                } else {
                    for (int i = 0; i < partsAll.size(); i++) {
                        Part p_art = partsAll.get(i);
                        if (p_art.getName().equals(partSearched)) {
                            finds = true;
                            ObservableList<Part> parts = lookupPart(partSearched);
                            partAddProductTable.setItems(parts);
                        }
                    } if (finds == false) {
                        Alert partAlert = new Alert(Alert.AlertType.WARNING);
                        partAlert.setContentText("No match to part name!");
                        partAlert.showAndWait();
                        partAddProductTable.setItems(getAllParts());
                    }
                }
            }
        }
        searchAddProduct.setText("");
    }

    /**
     * This method is called when the "Add Product" button is clicked for the product ID field.
     * It handles the action event for adding a new product ID.
     *
     * @param event The ActionEvent object representing the button click event.
     */
    public void idAddProduct(ActionEvent event) {
    }

    /**
     * This method is called when the "Add Product" button is clicked for the product name field.
     * It handles the action event for adding a new product name.
     *
     * @param event The ActionEvent object representing the button click event.
     */
    public void nameAddProduct(ActionEvent event) {
    }

    /**
     * This method is called when the "Add Product" button is clicked for the product inventory field.
     * It handles the action event for adding a new product inventory.
     *
     * @param event The ActionEvent object representing the button click event.
     */
    public void invAddProduct(ActionEvent event) {
    }

    /**
     * This method is called when the "Add Product" button is clicked for the product price field.
     * It handles the action event for adding a new product price.
     *
     * @param event The ActionEvent object representing the button click event.
     */
    public void priceAddProduct(ActionEvent event) {
    }

    /**
     * This method is called when the "Add Product" button is clicked for the product max field.
     * It handles the action event for adding a new product maximum.
     *
     * @param event The ActionEvent object representing the button click event.
     */
    public void maxAddProduct(ActionEvent event) {
    }

    /**
     * This method is called when the "Add Product" button is clicked for the product min field.
     * It handles the action event for adding a new product minimum.
     *
     * @param event The ActionEvent object representing the button click event.
     */
    public void minAddProduct(ActionEvent event) {
    }
}
