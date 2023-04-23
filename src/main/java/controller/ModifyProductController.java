package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import static model.Inventory.*;
/**
 *
 * @author
 * Duvall Roberts
 * drob801@wgu.edu
 * Student ID: 007792396
 */

/**
 * This class is the controller for the modify-product.fxml file, which is responsible for modifying an existing product.
 * It implements the Initializable interface to initialize the controller after its root element has been completely processed.
 */
public class ModifyProductController implements Initializable {
    
    /**
     * The product to be modified.
     */
    Product productSelected;

    /**
     * The list of parts associated with the product.
     */
    ObservableList<Part> partModProd = FXCollections.observableArrayList();

    /**
     * The index of the selected product.
     */
    int indexSelected;

    /**
     * The text field for entering the ID of the product to be added.
     */
    @FXML
    private TextField IDProductAddTextField;

    /**
     * The text field for entering the name of the product to be added.
     */
    @FXML
    private TextField NameProductAddTextField;

    /**
     * The text field for entering the inventory of the product to be added.
     */
    @FXML
    private TextField InventoryProductAddTextField;

    /**
     * The text field for entering the price of the product to be added.
     */
    @FXML
    private TextField PriceProductAddTextField;

    /**
     * The text field for entering the maximum number of products that can be added.
     */
    @FXML
    private TextField MaxProductAddTextField;

    /**
     * The text field for entering the minimum number of products that must be added.
     */
    @FXML
    private TextField MinProductAddTextField;

    /**
     * The table view for displaying the parts to be modified.
     */
    @FXML
    private TableView<Part> tableModifyPart;

    /**
     * The column for displaying the ID of the parts to be modified.
     */
    @FXML
    private TableColumn<Part, Integer> partIdMod;

    /**
     * The column for displaying the name of the parts to be modified.
     */
    @FXML
    private TableColumn<Part, String> partNameMod;

    /**
     * The column for displaying the inventory of the parts to be modified.
     */
    @FXML
    private TableColumn<Part, Integer> partInventoryMod;

    /**
     * The column for displaying the price of the parts to be modified.
     */
    @FXML
    private TableColumn<Part, Double> partPriceMod;

    /**
     * The table view for displaying the associated parts.
     */
    @FXML
    private TableView<Part> tableModifyAssociatedPart;

    /**
     * The TableColumn class is used to represent a column in a table view of parts.
     * This class contains the properties and methods necessary to display the associated part information.
     *
     * @param <Part> The type of the associated part.
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartIdMod;

    /**
     * The TableColumn class is used to represent a column in a table view of parts.
     * This class contains the properties and methods necessary to display the associated part information.
     *
     * @param <Part> The type of the associated part.
     */
    @FXML
    private TableColumn<Part, String> associatedPartNameMod;

    /**
     * The TableColumn class is used to represent a column in a table view of parts.
     * This class contains the properties and methods necessary to display the associated part information.
     *
     * @param <Part> The type of the associated part.
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartInventoryMod;

    /**
     * The TableColumn class is used to represent a column in a table view of parts.
     * This class contains the properties and methods necessary to display the associated part information.
     *
     * @param <Part> The type of the associated part.
     */
    @FXML
    private TableColumn<Part, Double> associatedPartPriceMod;

    /**
     * The TextField class is used to represent a text field in the modify product page.
     * This class contains the properties and methods necessary to display and manipulate the text field.
     */
    @FXML
    private TextField modifyProductSearch;

    /**
     * The Button class is used to represent a button in the modify product page.
     * This class contains the properties and methods necessary to display and manipulate the button.
     */
    @FXML
    private Button addModifyProductPage;

    /**
     * The Button class is used to represent a button in the modify product page.
     * This class contains the properties and methods necessary to display and manipulate the button.
     */
    @FXML
    private Button removeModifyProductPage;

    /**
     * The Button class is used to represent a button in the modify product page.
     * This class contains the properties and methods necessary to display and manipulate the button.
     */
    @FXML
    private Button saveModifyProductPage;

    /**
     * The Button class is used to represent a button in the modify product page.
     * This class contains the properties and methods necessary to display and manipulate the button.
     */
    @FXML
    private Button cancelModifyProductPage;

    /**
     * This method handles the click event of the "Cancel" button on the "Modify Product" page and takes the user back to the main page.
     *
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    void onClickCancelModifyProductPageButton() throws IOException {

        Stage stage = (Stage)cancelModifyProductPage.getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/main/main-screen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();

    }

    /**
     * This method updates the table of parts.
     */
    public void updateTablePart() {
        tableModifyPart.setItems(getAllParts());
    }

    /**
     * This method initializes the controllers and populates the part and product tables.
     *
     * @param url the URL of the FXML file.
     * @param rb  the resource bundle associated with the FXML file.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        updateTablePart();

        partIdMod.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameMod.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryMod.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceMod.setCellValueFactory(new PropertyValueFactory<>("price"));

        productSelected = new Product(0, null, 0.0, 0, 0, 0);
        partModProd = productSelected.getAllAssociatedParts();
        tableModifyAssociatedPart.setItems(productSelected.getAllAssociatedParts());

        associatedPartIdMod.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameMod.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryMod.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceMod.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * Sets the selected product and its properties at the specified index.
     *
     * @param product the product to be set
     * @param index the index where the product should be set
     */
    public void setProduct(Product product, int index) {
        productSelected = product;
        indexSelected = index;
        if (product instanceof Product) {
            Product productNew = productSelected;

            this.NameProductAddTextField.setText(productNew.getName());
            this.InventoryProductAddTextField.setText((Integer.toString(productNew.getStock())));
            this.PriceProductAddTextField.setText((Double.toString(productNew.getPrice())));
            this.MaxProductAddTextField.setText((Integer.toString(productNew.getMax())));
            this.MinProductAddTextField.setText((Integer.toString(productNew.getMin())));
            tableModifyAssociatedPart.setItems(productNew.getAllAssociatedParts());
            updateProduct(indexSelected, productNew);
        }
    }

    /**
     * Updates the current product and adds it as a new product.
     * @param actionEvent The event that triggers the save or modify product action.
     * @throws IOException If an input or output exception occurred.
     */
    @FXML
    public void saveModifyProductButton(ActionEvent actionEvent) throws IOException {
        try {
            if (!(Integer.class.isInstance(Integer.parseInt(InventoryProductAddTextField.getText())))){
                Alert invAlert = new Alert(Alert.AlertType.ERROR, "Inventory value needs to be number!");
                invAlert.showAndWait();
            } else if (!(Double.class.isInstance(Double.parseDouble(PriceProductAddTextField.getText())))) {
                Alert priceAlert = new Alert(Alert.AlertType.ERROR, "Inventory value needs to be number!");
                priceAlert.showAndWait();
            } else if (Integer.parseInt(MaxProductAddTextField.getText()) < Integer.parseInt(MinProductAddTextField.getText())){
                Alert minAlert = new Alert(Alert.AlertType.ERROR, "Minimum can't be greater than maximum!");
                minAlert.showAndWait();
            } else if (Integer.parseInt(InventoryProductAddTextField.getText()) < Integer.parseInt(MinProductAddTextField.getText()) || Integer.parseInt(InventoryProductAddTextField.getText()) > Integer.parseInt(MaxProductAddTextField.getText())){
                Alert invMaxAlert = new Alert(Alert.AlertType.ERROR, "Inventory amount must with the range of minimum and maximum!");
                invMaxAlert.showAndWait();
            } else {
                String modName = NameProductAddTextField.getText();
                int modInventory = Integer.parseInt(InventoryProductAddTextField.getText());
                double priceCost = Double.parseDouble(PriceProductAddTextField.getText());
                int max = Integer.parseInt(MaxProductAddTextField.getText());
                int min = Integer.parseInt(MinProductAddTextField.getText());

                productSelected.setName(modName);
                productSelected.setPrice(priceCost);
                productSelected.setStock(modInventory);
                productSelected.setMin(min);
                productSelected.setMax(max);

                updateProduct(indexSelected, productSelected);

                Stage modifyStage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Object scene = FXMLLoader.load(getClass().getResource("/main/main-screen.fxml"));
                modifyStage.setScene(new Scene((Parent) scene));
                modifyStage.show();
            }
        }
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter valid value!");
            alert.showAndWait();
        }
    }

    /**
     * Removes the selected part from the associated parts table in the Modify Product screen.
     * If no part is selected, an error alert is displayed.
     *
     * @param actionEvent The action event generated by clicking the remove button.
     */
    public void removeModifyProductButton(ActionEvent actionEvent) {

        // Get the selected part
        Part part = tableModifyAssociatedPart.getSelectionModel().getSelectedItem();

        // Display an error alert if no part is selected
        if (part == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Part not selected!");
            alert.showAndWait();
        } else {
            // Display a confirmation alert before removing the part
            Alert remAlert = new Alert(Alert.AlertType.CONFIRMATION, "Want to remove part?");
            Optional<ButtonType> answer = remAlert.showAndWait();
            if (answer.isPresent() && answer.get() == ButtonType.OK) {
                // Remove the part from the associated parts list and update the table
                partModProd.remove(part);
                tableModifyAssociatedPart.setItems(partModProd);
            }
        }
    }

    /**
     * Adds the selected part to the associated parts table in the Modify Product screen.
     *
     * @param actionEvent The action event generated by clicking the add button.
     */
    public void addModifyProductButton(ActionEvent actionEvent) {
        // Get the selected part
        Part part = tableModifyPart.getSelectionModel().getSelectedItem();
        // Add the part to the associated parts list and update the table
        productSelected.addAssociatedPart(part);
        partModProd = productSelected.getAllAssociatedParts();
    }

    /**
     * This method searches for a part by ID or name and modifies the table to display the found part or parts.
     * If the search field is empty, a warning message is displayed and the table is reset to display all parts.
     * If the search value is not a valid integer, the method searches for parts by name instead.
     *
     * @param actionEvent The ActionEvent that triggers the searchModifyProductIDName method.
     */
    public void searchModifyProductIDName(ActionEvent actionEvent) {
        String partSearched = modifyProductSearch.getText();

        if(partSearched.equals("")) {
            Alert partAlert = new Alert(Alert.AlertType.WARNING);
            partAlert.setTitle("Warning");
            partAlert.setContentText("You did not put ID nor Name");
            partAlert.showAndWait();
            tableModifyPart.setItems(getAllParts());
        } else {
            boolean finds = false;
            try {
                Part foundPart = lookupPart(Integer.parseInt(partSearched));
                if (foundPart != null) {
                    ObservableList<Part> parts = FXCollections.observableArrayList();
                    parts.add(foundPart);
                    tableModifyPart.setItems(parts);
                }
                else {
                    Alert partAlert = new Alert(Alert.AlertType.WARNING);
                    partAlert.setContentText("Does not match any Part ID");
                    partAlert.showAndWait();
                    tableModifyPart.setItems(getAllParts());
                }
            } catch (NumberFormatException e) {
                ObservableList<Part> partsAll = getAllParts();
                if(partsAll.isEmpty()){
                    Alert partAlert = new Alert(Alert.AlertType.WARNING);
                    partAlert.setContentText("No parts\nAdd parts to parts list first");
                    partAlert.showAndWait();
                    tableModifyPart.setItems(getAllParts());
                } else {
                    for (int i = 0; i < partsAll.size(); i++) {
                        Part p_art = partsAll.get(i);
                        if (p_art.getName().equals(partSearched)) {
                            finds = true;
                            ObservableList parts = lookupPart(partSearched);
                            tableModifyPart.setItems(parts);
                        }
                    } if (finds == false) {
                        Alert partAlert = new Alert(Alert.AlertType.WARNING);
                        partAlert.setContentText("No match to part name!");
                        partAlert.showAndWait();
                        tableModifyPart.setItems(getAllParts());
                    }
                }
            }
        }
        modifyProductSearch.setText("");
    }
}
