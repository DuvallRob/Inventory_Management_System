package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Part;
import model.Product;
import model.Inventory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static model.Inventory.*;

/**
 *
 * @author
 * Duvall Roberts
 * drob801@wgu.edu
 * Student ID: 007792396
 */

/**MainController Class for main-screen.fxml*/
public class MainController implements Initializable {



    /**
     * The add part button.
     */
    @FXML
    private Button addPartButton;

    /**
     * The modify part button.
     */
    @FXML
    private Button modifyPartButton;

    /**
     * The add product button.
     */
    @FXML
    private Button addProductButton;

    /**
     * The modify product button.
     */
    @FXML
    private Button modifyProductButton;

    /**
     * The table view for parts.
     */
    @FXML
    private TableView<Part> tableParts;

    /**
     * The table view for products.
     */
    @FXML
    private TableView<Product> tableProducts;

    /**
     * The part ID column for the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> PartId;

    /**
     * The part name column for the parts table.
     */
    @FXML
    private TableColumn<Part, String> PartNamePart;

    /**
     * The inventory level column for the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> InventoryLevelPart;

    /**
     * The cost per unit column for the parts table.
     */
    @FXML
    private TableColumn<Part, Double> CostUnitPart;

    /**
     * The product ID column for the products table.
     */
    @FXML
    private TableColumn<Product, Integer> ProductId;

    /**
     * The product name column for the products table.
     */
    @FXML
    private TableColumn<Product, String> ProductNameProduct;

    /**
     * The inventory level column for the products table.
     */
    @FXML
    private TableColumn<Product, Integer> InventoryLevelProduct;

    /**
     * The cost per unit column for the products table.
     */
    @FXML
    private TableColumn<Product, Double> CostUnitProduct;

    /**
     * The search text field for the parts table.
     */
    @FXML
    private TextField PartSearchText;

    /**
     * The search text field for the products table.
     */
    @FXML
    private TextField ProductSearchText;

    /**
     * Action takes user to Add Part FXML page
     * @throws IOException If FXMLLoader cannot load FXML resource
     */
    @FXML
    void onClickedAddPart() throws IOException {

        Stage stage = (Stage)addPartButton.getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/main/add-parts.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();

    }

    /**
     * Action takes user to Modify Part FXML page
     * @param event An event indicating that Modify Part button has been clicked
     * @throws IOException If FXMLLoader cannot load FXML resource
     */

    /**
     * Handles the event when the "Modify Part" button is clicked.
     *
     * @param event the ActionEvent triggered by clicking the button
     * @throws IOException if there is an input/output error
     */
    @FXML
    void onClickedModifyPart(ActionEvent event) throws IOException {

        // Get the current stage and load the "modify-part.fxml" file
        Parent parent;
        Stage stage;
        stage = (Stage) modifyPartButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/modify-part.fxml"));
        parent = loader.load();

        // Set the selected part in the ModifyPartController
        ModifyPartController controller = loader.getController();
        Part part = tableParts.getSelectionModel().getSelectedItem();
        int index = tableParts.getSelectionModel().getSelectedIndex();
        if(part != null) {
            controller.setPart(part, index);
        }

        // Create and show the scene
        Scene scene =new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * This method is called when the user clicks on the "Modify Product" button. It loads the Modify Product FXML page
     * and sets the selected product and its index on the ModifyProductController. The page is then displayed on a new stage.
     *
     * @param event The event triggered by clicking the button.
     * @throws IOException if the FXML file cannot be loaded.
     */
    @FXML
    void onClickedModifyProduct(ActionEvent event) throws IOException {

        Parent parent;
        Stage stage = (Stage)modifyProductButton.getScene().getWindow();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/main/modify-product.fxml"));
        parent = loader.load();
        ModifyProductController controller = loader.getController();
        Product product=tableProducts.getSelectionModel().getSelectedItem();
        int index = tableProducts.getSelectionModel().getSelectedIndex();

        if(product != null) {
            controller.setProduct(product, index);
        }
        Scene scene =new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Action takes user to Add Product FXML page
     * @throws IOException if there is an error loading the FXML file
     */
    @FXML
    void onClickedAddProduct() throws IOException {

        Stage stage = (Stage)addProductButton.getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/main/add-products.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();

    }

    /**
     * Initializes the controllers and populates the part and product tables with data from the Inventory class.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableParts.setItems(Inventory.getAllParts());

        tableProducts.setItems(Inventory.getAllProducts());

        PartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNamePart.setCellValueFactory(new PropertyValueFactory<>("name"));
        InventoryLevelPart.setCellValueFactory(new PropertyValueFactory<>("stock"));
        CostUnitPart.setCellValueFactory(new PropertyValueFactory<>("price"));

        ProductId.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        ProductNameProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
        InventoryLevelProduct.setCellValueFactory(new PropertyValueFactory<>("stock"));
        CostUnitProduct.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    ObservableList products;
    ObservableList parts;
    Part partsFound;
    Product productFound;

    /**
     * Handles the event of searching for parts based on either ID or name.
     * If the search field is empty, it displays a warning alert and shows all parts in the table.
     * If the search field contains an integer, it looks up the part with that ID and displays it in the table if found.
     * If the search field contains a non-integer string, it looks up parts with that name and displays them in the table if found.
     * If no parts are found, it displays a warning alert and shows all parts in the table.
     */
    @FXML
    void searchPartsEvent() {
        String partSearched = PartSearchText.getText();

        if(partSearched.equals("")) {
            // Display warning alert when search field is empty
            Alert partAlert = new Alert(Alert.AlertType.WARNING);
            partAlert.setTitle("Warning");
            partAlert.setContentText("You did not put ID nor Name");
            partAlert.showAndWait();
            tableParts.setItems(getAllParts());
        } else {
            boolean finds = false;
            try {
                // Look up the part by ID and display it in the table if found
                partsFound = lookupPart(Integer.parseInt(partSearched));
                if (partsFound != null) {
                    ObservableList<Part> parts = FXCollections.observableArrayList();
                    parts.add(partsFound);
                    tableParts.setItems(parts);
                }
                else {
                    // Display warning alert if no part is found with the given ID
                    Alert partAlert = new Alert(Alert.AlertType.WARNING);
                    partAlert.setContentText("Does not match any Part ID");
                    partAlert.showAndWait();
                    tableParts.setItems(getAllParts());
                }
            } catch (NumberFormatException e) {
                ObservableList<Part> partsAll = getAllParts();
                if(partsAll.isEmpty()){
                    // Display warning alert if no parts are present in the list
                    Alert partAlert = new Alert(Alert.AlertType.WARNING);
                    partAlert.setContentText("No parts\nAdd parts to parts list first");
                    partAlert.showAndWait();
                    tableParts.setItems(getAllParts());
                } else {
                    // Look up parts by name and display them in the table if found
                    for (int i = 0; i < partsAll.size(); i++) {
                        Part p_art = partsAll.get(i);
                        if (p_art.getName().equals(partSearched)) {
                            finds = true;
                            parts = lookupPart(partSearched);
                            tableParts.setItems(parts);
                        }
                    }
                    // Display warning alert if no parts are found with the given name
                    if (finds == false) {
                        Alert partAlert = new Alert(Alert.AlertType.WARNING);
                        partAlert.setContentText("No match to part name!");
                        partAlert.showAndWait();
                        tableParts.setItems(getAllParts());
                    }
                }
            }
        }
        PartSearchText.setText("");
    }

    /**
     * Handles the event of pressing the "Enter" key while searching for parts.
     * Calls the searchPartsEvent() method when "Enter" key is pressed.
     * @param enter The KeyEvent that occurred
     */
    public void searchPartsEvent2(KeyEvent enter){
        if (enter.getCode().equals(KeyCode.ENTER)) {
            searchPartsEvent();
        }
    }

    /**
     * This method is triggered when the "Enter" key is pressed while the user types in the search bar for products on the main screen.
     * It calls the searchProductsEvent() method.
     * @param enter The KeyEvent object that represents the "Enter" key press event.
     */
    public void searchProductsEvent2(KeyEvent enter){
        if (enter.getCode().equals(KeyCode.ENTER)) {
            searchProductsEvent();
        }
    }

        /**
        * This method searches for products on the main screen based on the ID or name entered by the user in the search bar.
        * If no ID or name is entered, it displays a warning message and shows all the products.
        * If an ID or name is entered, it searches for the product(s) and displays the result(s) in the table view.
         */
        @FXML
        void searchProductsEvent(){
            String productSearched = ProductSearchText.getText();

            if(productSearched.equals("")) {
                Alert productAlert = new Alert(Alert.AlertType.WARNING);
                productAlert.setTitle("Warning");
                productAlert.setContentText("You did not put ID nor Name");
                productAlert.showAndWait();
                tableProducts.setItems(getAllProducts());
            } else {
                boolean finds = false;
                try {
                    productFound = lookupProduct(Integer.parseInt(productSearched));
                    if (productFound != null) {
                        ObservableList<Product> products = FXCollections.observableArrayList();
                        products.add(productFound);
                        tableProducts.setItems(products);
                    }
                    else {
                        Alert productAlert = new Alert(Alert.AlertType.WARNING);
                        productAlert.setContentText("Does not match any Product ID");
                        productAlert.showAndWait();
                        tableProducts.setItems(getAllProducts());
                    }
                } catch (NumberFormatException e) {
                    ObservableList<Product> productsAll = getAllProducts();
                    if(productsAll.isEmpty()){
                        Alert productAlert = new Alert(Alert.AlertType.WARNING);
                        productAlert.setContentText("No products\nAdd products to products list first");
                        productAlert.showAndWait();
                        tableProducts.setItems(getAllProducts());
                    } else {
                        for (int i = 0; i < productsAll.size(); i++) {
                            Product p_roduct = productsAll.get(i);
                            if (p_roduct.getName().equals(productSearched)) {
                                finds = true;
                                products = lookupProduct(productSearched);
                                tableProducts.setItems(products);
                            }
                        } if (finds == false) {
                            Alert productAlert = new Alert(Alert.AlertType.WARNING);
                            productAlert.setContentText("No match to product name!");
                            productAlert.showAndWait();
                            tableProducts.setItems(getAllProducts());
                        }
                    }
                }
            }
            ProductSearchText.setText("");


        }

        /**
        * Deletes the selected product from the main screen FXML page.
         */
        @FXML
        void onClickedDeleteProduct() {
            // Retrieve the selected product
            Product product = tableProducts.getSelectionModel().getSelectedItem();

            // Check if a product has been selected
            if (product == null) {
                // Display an error message if no product has been selected
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText("Product not selected");
                alertError.showAndWait();
            } else {
                // Display a confirmation message for deleting the product
                Alert productAlert = new Alert(Alert.AlertType.CONFIRMATION, "Want to delete Product?");
                Optional<ButtonType> answer = productAlert.showAndWait();

                // Check if the user confirmed the deletion
                if (answer.isPresent() && answer.get() == ButtonType.OK) {
                    // Check if the product has any associated parts
                    ObservableList<Part> assopart = product.getAllAssociatedParts();
                    if (assopart.size() >= 1) {
                        // Display an error message if the product has associated parts
                        productAlert = new Alert(Alert.AlertType.ERROR, "Need to delete associated part first!");
                        productAlert.showAndWait();
                    } else {
                        // Delete the product if it has no associated parts
                        deleteProduct(product);
                    }
                }
            }
        }


        /**
        * Action method to delete a part from the main screen FXML page.
        */
        @FXML
        void onClickedDeletePart() {

            // Retrieve the selected part from the parts table
            Part part = tableParts.getSelectionModel().getSelectedItem();

            // If no part is selected, display an error alert
            if (part == null) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText("Part not selected!");
                alertError.showAndWait();

                // If a part is selected, confirm deletion with a dialog
            } else {
                Alert partAlert = new Alert(Alert.AlertType.CONFIRMATION, "Ready to delete part?");
                Optional<ButtonType> answer = partAlert.showAndWait();

                // If the user confirms deletion, call the deletePart() method of the Inventory class
                if (answer.isPresent() && answer.get() == ButtonType.OK) {
                    Inventory.deletePart(part);
                }
            }
        }

        /**
         * This method exits the application.
         */
        @FXML
        void exitMain() {
            System.exit(0);
        }

}
