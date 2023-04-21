package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author
 * Duvall Roberts
 * drob801@wgu.edu
 * Student ID: 007792396
 */

/**
 * The Product class represents a product in the inventory management system.
 * It contains information about the product, such as its ID, name, price, stock, minimum, and maximum.
 * It also allows for parts to be associated with the product.
 */
public class Product {
    /** The unique identifier of the product.*/
    private int idProduct;

    /** The name of the product.*/
    private String name;

    /** The price of the product.*/
    private double price;

    /** The current stock level of the product.*/
    private int stock;

    /** The minimum stock level of the product.*/
    private int min;

    /** The maximum stock level of the product.*/
    private int max;

    /** The list of associated parts with this product.*/
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
    * Constructs a new Product instance with the specified details.
    *
    * @param idProduct the unique identifier of the product.
    * @param name      the name of the product.
    * @param price     the price of the product.
    * @param stock     the current stock level of the product.
    * @param min       the minimum stock level of the product.
    * @param max       the maximum stock level of the product.
    */
    public Product(int idProduct, String name, double price, int stock, int min, int max) {
      this.idProduct = idProduct;
      this.name = name;
      this.price = price;
      this.stock = stock;
      this.min = min;
      this.max = max;
    }

    /**@param idProduct is the product's ID that will be set*/
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    /**@param name is the product's name that will be set */
    public void setName(String name) {
        this.name = name;
    }

    /**@param price is the product's price that will be set */
    public void setPrice(double price) {
        this.price = price;
    }

    /**@param stock is the product's stock that will be set */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**@param min is the product's min that will be set */
    public void setMin(int min) {
        this.min = min;
    }

    /**@param max is the product's max that will be set */
    public void setMax(int max) {
        this.max = max;
    }

    /**@return product's ID*/
    public int getIdProduct() {
        return idProduct;
    }

    /**@return product's Name*/
    public String getName() {
        return name;
    }

    /**@return product's price amount*/
    public double getPrice() {
        return price;
    }

    /**@return product's stock amount*/
    public int getStock() {
        return stock;
    }

    /**@return product's minimum amount of stock*/
    public int getMin() {
        return min;
    }

    /**@return product's maximum amount of stock*/
    public int getMax() {
        return max;
    }

    /**@param part to add part to associatedParts*/
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**@param selectedAssociatedPart deletes partDelete from associatedParts if IDs match*/
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        for (Part partDelete : associatedParts){
            if (partDelete.getId() == selectedAssociatedPart.getId()) {
                associatedParts.remove((partDelete));
                return true;
            }
        }
        return false;
    }

    /**@return associated parts' list*/
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
