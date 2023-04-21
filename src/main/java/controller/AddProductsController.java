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


/**Class AddProductsController for add-products.fxml*/
public class AddProductsController implements Initializable {
    Product productNew;
    ObservableList<Part> partAdded = FXCollections.observableArrayList();

    @FXML
    private Button cancelAddProductsPage;

    @FXML
    private Button saveAddProduct;

    @FXML
    private Button removeAddProduct;

    @FXML
    private Button addAddProduct;

    @FXML
    private TextField idAddProduct;

    @FXML
    private TextField nameAddProduct;

    @FXML
    private TextField invAddProduct;

    @FXML
    private TextField priceAddProduct;

    @FXML
    private TextField maxAddProduct;

    @FXML
    private TextField minAddProduct;

    @FXML
    private TextField searchAddProduct;

    @FXML
    private TableView<Part> partAddProductTable;

    @FXML
    private TableColumn<Part, Integer> idPartAddProduct;
    
    @FXML
    private TableColumn<Part, String> namePartAddProduct;
    
    @FXML
    private TableColumn<Part, Integer> invPartAddProduct;
    
    @FXML
    private TableColumn<Part, Double> unitPartAddProduct;

    @FXML
    private TableView<Part> partAddProductTableAdded;

    @FXML
    private TableColumn<Part, Integer> idPartAddedProduct;

    @FXML
    private TableColumn<Part, String> namePartAddedProduct;

    @FXML
    private TableColumn<Part, Integer> invPartAddedProduct;

    @FXML
    private TableColumn<Part, Double> unitPartAddedProduct;

    /**Takes user back to main screen*/
    @FXML
    void onClickCancelAddProductsPageButton() throws IOException {

        Stage stage = (Stage)cancelAddProductsPage.getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/main/main-screen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();

    }

    /**Updates top table*/
    public void updatePartTable() {
        partAddProductTable.setItems(Inventory.getAllParts());
    }

    /**Initialize controllers and populates the tables.*/
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

    /**Saves added products*/
    @FXML
    public void onClickSaveAddProductsPageButton(ActionEvent actionEvent) throws IOException{
        int ID = 0;
        for(Product proDuct : Inventory.getAllProducts()) {
            if(proDuct.getIdProduct() > ID)
                ID = proDuct.getIdProduct();
        }
        ID++; // increment ID after finding the maximum

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
            }else {
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


    /**Removes added product*/
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

    /**Adds products*/
    public void onClickAddAddProductsPageButton(ActionEvent actionEvent) {
        Part part = partAddProductTable.getSelectionModel().getSelectedItem();
        productNew.addAssociatedPart(part);
        partAdded = productNew.getAllAssociatedParts();
    }

    /**Function of search that is applied when enter is pressed*/
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
                            ObservableList parts = lookupPart(partSearched);
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

    public void idAddProduct(ActionEvent event) {
    }

    public void nameAddProduct(ActionEvent event) {
    }

    public void invAddProduct(ActionEvent event) {
    }

    public void priceAddProduct(ActionEvent event) {
    }

    public void maxAddProduct(ActionEvent event) {
    }

    public void minAddProduct(ActionEvent event) {
    }
}
