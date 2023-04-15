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

/**Class ModifyPartsController for modify-parts.fxml*/

public class ModifyPartController {
    @FXML
    private ToggleGroup RadioButton;
    private Part partSelected;
    private int indexSelected;

    @FXML
    private RadioButton houseModifyPart;

    @FXML
    private RadioButton outModifyPart;

    @FXML
    private Label machineIdModifyPart;

    @FXML
    private TextField idModifyPart;

    @FXML
    private TextField nameModifyPart;

    @FXML
    private TextField inventoryModifyPart;

    @FXML
    private TextField priceModifyPart;

    @FXML
    private TextField maxModifyPart;

    @FXML
    private TextField minModifyPart;

    @FXML
    private TextField machIneModifyPart;

    @FXML
    private Button saveModifyPart;

    @FXML
    private Button cancelModifyPartPage;

    @FXML
    public void houseOnClickedModifyPart(ActionEvent event){
        machineIdModifyPart.setText("Machine ID");
    }

    @FXML
    public void outOnClickedModifyPart(ActionEvent event){
        machineIdModifyPart.setText("Company Name");
    }
    @FXML
    public void onClickCancelModifyPartPageButton() throws IOException {

        Stage stage = (Stage)cancelModifyPartPage.getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/main/main-screen.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();

    }


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
