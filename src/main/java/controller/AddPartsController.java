package controller;

import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import java.io.IOException;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author
 * Duvall Roberts
 * drob801@wgu.edu
 * Student ID: 007792396
 */

/**Class AddPartsController for add-parts.fxml*/
public class AddPartsController {
    @FXML
    private RadioButton houseAddPartButton;

    @FXML
    private RadioButton outAddPartButton;
    
    @FXML
    private Button cancelAddPartsPage;
    
    @FXML
    private Button saveAddPartsPage;

    @FXML
    private Label machineIDAddPart;

    @FXML
    private TextField idAddPart;

    @FXML
    private TextField nameAddPart;

    @FXML
    private TextField inventoryAddPart;

    @FXML
    private TextField priceAddPart;

    @FXML
    private TextField maxAddPart;

    @FXML
    private TextField minAddPart;

    @FXML
    private TextField macAddPart;

    private boolean isHouse = true;

    /**Takes user back to main screen*/
    @FXML
    void onClickCancelAddPartsPageButton() throws IOException {

        Stage stage = (Stage) cancelAddPartsPage.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/main/main-screen.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void idAddPart(ActionEvent event){

    }

    /**Function for when In-House is clicked*/
    public void houseOnClickedAddPart(ActionEvent actionEvent) {
        isHouse = true;
        machineIDAddPart.setText("Machine ID");
    }

    /**Function for when Outsourced is clicked*/
    public void outOnClickedAddPart(ActionEvent actionEvent) {
        isHouse = false;
        machineIDAddPart.setText("Company Name");
    }

    /**Adds part*/
    @FXML
    public void onClickSaveAddPartsPageButton(ActionEvent actionEvent) throws IOException{

        int ID = 0;
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (int i = 0, allPartsSize = allParts.size(); i < allPartsSize; i++) {
            Part part = allParts.get(i);

            if (part.getId() > ID)

                ID = part.getId();

        }


        try{
            if (!(Integer.class.isInstance(Integer.parseInt(inventoryAddPart.getText())))){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory needs to be a number!");
                alert.showAndWait();
            } else if (!(Double.class.isInstance(Double.parseDouble(priceAddPart.getText())))){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Price needs to be a number!");
                alert.showAndWait();
            }
            else if (Integer.parseInt(minAddPart.getText()) > Integer.parseInt(maxAddPart.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Min value cannot be greater than Max value!");
                alert.showAndWait();
            } else if (Integer.parseInt(inventoryAddPart.getText()) > Integer.parseInt(maxAddPart.getText()) || Integer.parseInt(inventoryAddPart.getText()) < Integer.parseInt(minAddPart.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory amount must be between minimum and maximum!");
                alert.showAndWait();
            }else {
                idAddPart.setText(String.valueOf(++ID));
                int id = Integer.parseInt(idAddPart.getText());
                String name = nameAddPart.getText();
                int inventory = Integer.parseInt(inventoryAddPart.getText());
                double priceCost = Double.parseDouble(priceAddPart.getText());
                int max = Integer.parseInt(maxAddPart.getText());
                int min = Integer.parseInt(minAddPart.getText());

                if (houseAddPartButton.isSelected()) {
                    int machineID = Integer.parseInt(macAddPart.getText());
                    InHouse addInHousePart = new InHouse(id, name, priceCost, inventory, min, max
                            , machineID);

                    Inventory.addPart(addInHousePart);
                }
                if (outAddPartButton.isSelected()) {
                    String companyName = macAddPart.getText();
                    Outsourced addOutsourcedPart = new Outsourced(id, name, priceCost, inventory,
                            min, max, companyName);

                    Inventory.addPart(addOutsourcedPart);
                }


                Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
                Parent scene = FXMLLoader.load(getClass().getResource("/main/main-screen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }

        }

        catch(NumberFormatException e){


            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please enter a valid value for each text field.");
            alert.showAndWait();

        }
    }

    public void nameAddPart(ActionEvent event) {
    }

    public void inventoryAddPart(ActionEvent event) {
    }

    public void priceAddPart(ActionEvent event) {
    }

    public void maxAddPart(ActionEvent event) {
    }

    public void minAddPart(ActionEvent event) {
    }

    public void macAddPart(ActionEvent event) {
    }
}
