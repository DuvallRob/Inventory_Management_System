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

    @FXML
    private Button addPartButton;

    @FXML
    private Button modifyPartButton;

    @FXML
    private Button addProductButton;

    @FXML
    private Button modifyProductButton;

    @FXML
    private TableView<Part> tableParts;

    @FXML
    private TableView<Product> tableProducts;

    @FXML
    private TableColumn<Part, Integer> PartId;

    @FXML
    private TableColumn<Part, String> PartNamePart;

    @FXML
    private TableColumn<Part, Integer> InventoryLevelPart;

    @FXML
    private TableColumn<Part, Double> CostUnitPart;

    @FXML
    private TableColumn<Product, Integer> ProductId;

    @FXML
    private TableColumn<Product, String> ProductNameProduct;

    @FXML
    private TableColumn<Product, Integer> InventoryLevelProduct;

    @FXML
    private TableColumn<Product, Double> CostUnitProduct;

    @FXML
    private TextField PartSearchText;

    @FXML
    private TextField ProductSearchText;

    /**Action takes user to Add Part FXML page*/
    @FXML
    void onClickedAddPart() throws IOException {

            Stage stage = (Stage)addPartButton.getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/main/add-parts.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();

    }

    /**Action takes user to Modify Part FXML page*/
    @FXML
    void onClickedModifyPart(ActionEvent event) throws IOException {

        Parent parent;
        Stage stage;
        stage = (Stage) modifyPartButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/modify-part.fxml"));
        parent = loader.load();
        ModifyPartController controller = loader.getController();
        Part part = tableParts.getSelectionModel().getSelectedItem();
        int index = tableParts.getSelectionModel().getSelectedIndex();
        if(part != null) {
            controller.setPart(part, index);
        }
        Scene scene =new Scene(parent);
        stage.setScene(scene);
        stage.show();

    }

    /**Action takes user to Modify Product FXML page*/
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

    /**Action takes user to Add Product FXML page*/
    @FXML
    void onClickedAddProduct() throws IOException {

        Stage stage = (Stage)addProductButton.getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/main/add-products.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();

    }

    /**Initialize controllers and populates part and product tables*/
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

    /**Action searches for part on main screen*/
    public void searchPartsEvent2(KeyEvent enter){
        if (enter.getCode().equals(KeyCode.ENTER)) {
            searchPartsEvent();
        }
    }
    /**Action searches for part on main screen*/
    @FXML
    void searchPartsEvent() {
        String partSearched = PartSearchText.getText();

        if(partSearched.equals("")) {
            Alert partAlert = new Alert(Alert.AlertType.WARNING);
            partAlert.setTitle("Warning");
            partAlert.setContentText("You did not put ID nor Name");
            partAlert.showAndWait();
            tableParts.setItems(getAllParts());
        } else {
            boolean finds = false;
            try {
                partsFound = lookupPart(Integer.parseInt(partSearched));
                if (partsFound != null) {
                    ObservableList<Part> parts = FXCollections.observableArrayList();
                    parts.add(partsFound);
                    tableParts.setItems(parts);
                }
                else {
                    Alert partAlert = new Alert(Alert.AlertType.WARNING);
                    partAlert.setContentText("Does not match any Part ID");
                    partAlert.showAndWait();
                    tableParts.setItems(getAllParts());
                }
            } catch (NumberFormatException e) {
                ObservableList<Part> partsAll = getAllParts();
                if(partsAll.isEmpty()){
                    Alert partAlert = new Alert(Alert.AlertType.WARNING);
                    partAlert.setContentText("No parts\nAdd parts to parts list first");
                    partAlert.showAndWait();
                    tableParts.setItems(getAllParts());
                } else {
                    for (int i = 0; i < partsAll.size(); i++) {
                        Part p_art = partsAll.get(i);
                        if (p_art.getName().equals(partSearched)) {
                            finds = true;
                            parts = lookupPart(partSearched);
                            tableParts.setItems(parts);
                        }
                    } if (finds == false) {
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

        /**Action searches for product on main screen*/
        public void searchProductsEvent2(KeyEvent enter){
            if (enter.getCode().equals(KeyCode.ENTER)) {
                searchProductsEvent();
            }
        }

        /**Action searches for product on main screen*/
        @FXML
        void searchProductsEvent() {
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

        /**Action Deletes Product from Main Screen FXML page*/
        @FXML
        void onClickedDeleteProduct() {

            Product product = tableProducts.getSelectionModel().getSelectedItem();

            if (product == null) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText("Product not selected");
                alertError.showAndWait();
            } else {
                Alert productAlert = new Alert(Alert.AlertType.CONFIRMATION, "Want to delete Product?");
                Optional<ButtonType> answer = productAlert.showAndWait();

                if (answer.isPresent() && answer.get() == ButtonType.OK) {
                    ObservableList<Part> assopart = product.getAllAssociatedParts();
                    if (assopart.size() >= 1) {
                        productAlert = new Alert(Alert.AlertType.ERROR, "Need to delete associated part first!");
                        productAlert.showAndWait();
                    } else {
                        deleteProduct(product);
                    }
                }
            }
        }


        /**Action Deletes Part from Main Screen FXML page*/
        @FXML
        void onClickedDeletePart() {
            Part part = tableParts.getSelectionModel().getSelectedItem();
            if (part == null) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText("Part not selected!");
                alertError.showAndWait();
            } else {
                Alert partAlert = new Alert(Alert.AlertType.CONFIRMATION, "Ready to delete part?");
                Optional<ButtonType> answer = partAlert.showAndWait();
                if (answer.isPresent() && answer.get() == ButtonType.OK) {
                    Inventory.deletePart(part);
                }
            }
        }

        /**Action exits application*/
        @FXML
        void exitMain() {

            System.exit(0);

        }

}
