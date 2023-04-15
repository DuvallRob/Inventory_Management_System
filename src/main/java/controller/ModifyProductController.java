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

/**ModifyProductController class for modify-product.fxml*/
public class ModifyProductController implements Initializable{
    Product productNew;
    Product productSelected;
    int indexSelected;

    @FXML
    private TextField IDProductAddTextField;

    @FXML
    private TextField NameProductAddTextField;

    @FXML
    private TextField InventoryProductAddTextField;

    @FXML
    private TextField PriceProductAddTextField;

    @FXML
    private TextField MaxProductAddTextField;

    @FXML
    private TextField MinProductAddTextField;

    @FXML
    private TableView<Part> tableModifyPart;

    @FXML
    private TableColumn<Part, Integer> partIdMod;

    @FXML
    private TableColumn<Part, String> partNameMod;

    @FXML
    private TableColumn<Part, Integer> partInventoryMod;

    @FXML
    private TableColumn<Part, Double> partPriceMod;

    @FXML
    private TableView<Part> tableModifyAssociatedPart;

    @FXML
    private TableColumn<Part, Integer> associatedPartIdMod;

    @FXML
    private TableColumn<Part, String> associatedPartNameMod;

    @FXML
    private TableColumn<Part, Integer> associatedPartInventoryMod;

    @FXML
    private TableColumn<Part, Double> associatedPartPriceMod;

    @FXML
    private TextField modifyProductSearch;

    @FXML
    private Button addModifyProductPage;

    @FXML
    private Button removeModifyProductPage;

    @FXML
    private Button saveModifyProductPage;

    @FXML
    private Button cancelModifyProductPage;

    /**Takes user back to Main Page*/
    @FXML
    void onClickCancelModifyProductPageButton() throws IOException {

        Stage stage = (Stage)cancelModifyProductPage.getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/main/main-screen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();

    }

    /**Updates current part*/
    public void updateTablePart() {
        tableModifyPart.setItems(getAllParts());
    }
    /**Initialize controllers and populates the part and product tables.*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        updateTablePart();

        partIdMod.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameMod.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryMod.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceMod.setCellValueFactory(new PropertyValueFactory<>("price"));

        productNew = new Product(0, null, 0.0, 0, 0, 0);
        tableModifyAssociatedPart.setItems(productNew.getAllAssociatedParts());

        associatedPartIdMod.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameMod.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryMod.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceMod.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**Function for setting product*/
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

    /**Updates current part and adds as new Product*/
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

    /**Removes selected from associated table*/
    public void removeModifyProductButton(ActionEvent actionEvent) {

        Alert remAlert = new Alert(Alert.AlertType.CONFIRMATION, "Want to remove part?");
        Optional<ButtonType> answer = remAlert.showAndWait();
        if (answer.isPresent() && answer.get() == ButtonType.OK) {
            Part part = tableModifyAssociatedPart.getSelectionModel().getSelectedItem();
            if (productSelected.deleteAssociatedPart(part)) {
                productSelected.getAllAssociatedParts().remove(tableModifyAssociatedPart.getSelectionModel().getSelectedItem());
            }
        }

    }

    /**Adds to associated table*/
    public void addModifyProductButton(ActionEvent actionEvent) {
        Part part = tableModifyPart.getSelectionModel().getSelectedItem();
        productSelected.addAssociatedPart(part);
    }

    /**Searches for part*/
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
