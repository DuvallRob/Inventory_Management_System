package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import model.Part;
import model.Inventory;
import java.io.IOException;
import java.util.Optional;

import static model.Inventory.updatePart;

/**
 *
 * @author
 * Duvall Roberts
 * drob801@wgu.edu
 * Student ID: 007792396
 */

/**
 * The ModifyPartController class controls the functionality of the modify-parts.fxml view.
 */
public class ModifyPartController {
    /**
     * The private variable representing the selected private part.
     */
    private Part partSelected;

    /**
     * The private variable representing the index of the selected private part.
     */
    private int indexSelected;

    /**
     * The ToggleGroup for the RadioButtons.
     */
    @FXML
    private ToggleGroup RadioButton;

    /**
     * The RadioButton for modifying a part in-house.
     */
    @FXML
    private RadioButton houseModifyPart;

    /**
     * The RadioButton for modifying a part outside the company.
     */
    @FXML
    private RadioButton outModifyPart;

    /**
     * The Label for displaying the machine ID of the part being modified.
     */
    @FXML
    private Label machineIdModifyPart;

    /**
     * The TextField for entering the ID of the part being modified.
     */
    @FXML
    private TextField idModifyPart;

    /**
     * The TextField for entering the name of the part being modified.
     */
    @FXML
    private TextField nameModifyPart;

    /**
     * The TextField for entering the inventory level of the part being modified.
     */
    @FXML
    private TextField inventoryModifyPart;

    /**
     * The TextField for entering the price of the part being modified.
     */
    @FXML
    private TextField priceModifyPart;

    /**
     * The TextField for entering the maximum quantity of the part being modified.
     */
    @FXML
    private TextField maxModifyPart;

    /**
     * The TextField for entering the minimum quantity of the part being modified.
     */
    @FXML
    private TextField minModifyPart;

    /**
     * The TextField for entering the machine ID of the part being modified.
     */
    @FXML
    private TextField machIneModifyPart;

    /**
     * The Button for saving the modifications made to the part.
     */
    @FXML
    private Button saveModifyPart;

    /**
     * The Button for canceling the modification of the part and returning to the main page.
     */
    @FXML
    private Button cancelModifyPartPage;

    /**
     * Handles events when the "House" radio button is clicked on the Modify Part page.
     * Sets the text of the "Machine ID" label to "Machine ID".
     * @param event An ActionEvent object.
     */
    @FXML
    public void houseOnClickedModifyPart(ActionEvent event){
        machineIdModifyPart.setText("Machine ID");
    }

    /**
     * Handles events when the "Outsourced" radio button is clicked on the Modify Part page.
     * Sets the text of the "Machine ID" label to "Company Name".
     * @param event An ActionEvent object.
     */
    @FXML
    public void outOnClickedModifyPart(ActionEvent event){
        machineIdModifyPart.setText("Company Name");
    }

    /**
     * Handles events when the "Cancel" button is clicked on the Modify Part page.
     * Redirects the user back to the main screen.
     * @throws IOException If an input/output error occurs.
     */
    @FXML
    public void onClickCancelModifyPartPageButton() throws IOException {

        Stage stage = (Stage)cancelModifyPartPage.getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/main/main-screen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();

    }

    /**
     * Sets the values of the text fields on the Modify Part page to those of the selected part.
     * @param part A Part object representing the selected part.
     * @param index An integer representing the index of the selected part.
     */
    public void setPart(Part part, int index) {
        partSelected = part;
        indexSelected = index;

        if (part instanceof InHouse) {
            InHouse partNew = (InHouse) part;
            houseModifyPart.setSelected(true);
            machineIdModifyPart.setText("Machine ID");
            this.nameModifyPart.setText(partNew.getName());
            this.inventoryModifyPart.setText(String.valueOf(partNew.getStock()));
            this.priceModifyPart.setText(String.valueOf(partNew.getPrice()));
            this.minModifyPart.setText(String.valueOf(partNew.getMin()));
            this.maxModifyPart.setText(String.valueOf(partNew.getMax()));
            this.machIneModifyPart.setText(String.valueOf(partNew.getMachineId()));
            this.idModifyPart.setText(String.valueOf(partNew.getId()));
        }else if (part instanceof Outsourced){
            Outsourced partNew = (Outsourced) part;
            outModifyPart.setSelected(true);
            machineIdModifyPart.setText("Company Name");
            this.nameModifyPart.setText(partNew.getName());
            this.inventoryModifyPart.setText(String.valueOf(partNew.getStock()));
            this.priceModifyPart.setText(String.valueOf(partNew.getPrice()));
            this.minModifyPart.setText(String.valueOf(partNew.getMin()));
            this.maxModifyPart.setText(String.valueOf(partNew.getMax()));
            this.machIneModifyPart.setText(String.valueOf(partNew.getCompanyName()));
            this.idModifyPart.setText(String.valueOf(partNew.getId()));
        }
    }

    /**
     * Handles events when the "Save" button is clicked on the Modify Part page.
     * Validates user input and updates the selected part in the Inventory list.
     * Redirects the user back to the main screen.
     * @param event An ActionEvent object.
     * @throws IOException If an input/output error occurs.
     */
    public void saveOnClickedModifyPart(ActionEvent event) throws IOException {
        try {
            int inventory = Integer.parseInt(inventoryModifyPart.getText());
            double price = Double.parseDouble(priceModifyPart.getText());
            int max = Integer.parseInt(maxModifyPart.getText());
            int min = Integer.parseInt(minModifyPart.getText());

            StringBuilder errorMessage = new StringBuilder();

            if (inventory < min || inventory > max) {
                errorMessage.append("Inventory amount must be within the range of minimum and maximum!\n");
            }

            if (min > max) {
                errorMessage.append("Minimum can't be greater than maximum!\n");
            }

            if (errorMessage.length() > 0) {

                Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage.toString());
                alert.showAndWait();
                return;
            }

            int id = partSelected.getId();
            String name = nameModifyPart.getText();

            if (houseModifyPart.isSelected()) {
                int machineID = Integer.parseInt(machIneModifyPart.getText());
                InHouse inhousePart = new InHouse(id, name, price, inventory, min, max, machineID);
                Inventory.getAllParts().set(indexSelected, inhousePart);
            } else {
                String companyName = machIneModifyPart.getText();
                Outsourced outsourcedPart = new Outsourced(id, name, price, inventory, min, max, companyName);
                Inventory.getAllParts().set(indexSelected, outsourcedPart);
            }

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Object scene = FXMLLoader.load(getClass().getResource("/main/main-screen.fxml"));
            stage.setScene(new Scene((Parent) scene));
            stage.show();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please enter a valid value!");
            alert.showAndWait();
        }
    }


}
