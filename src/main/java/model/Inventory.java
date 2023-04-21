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
 * The Inventory class with items of inventory and manipulating functions of inventory
 */
public class Inventory {
    /**
     * Objects allParts and allProducts of type ObservableList
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Adds a new part to the inventory.
     *
     * @param newPart the part to be added to the inventory
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Adds a new product to the inventory.
     *
     * @param newProduct the product to be added to the inventory
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Looks up a part in the inventory by its ID.
     *
     * @param partId the ID of the part to be looked up
     * @return the part with the specified ID, or null if the part is not found
     */
    public static Part lookupPart(int partId) {
        for (int i = 0; allParts.size() > i; i++) {
            Part partFound = allParts.get(i);
            if (partFound.getId() == partId) {
                return partFound;
            }
        }
        return null;
    }

    /**
     * This method looks up a product by ID and returns the Product object if found, or null if not found.
     *
     * @param productId the ID of the product to be looked up
     * @return the Product object if found, or null if not found
     */
    public static Product lookupProduct(int productId) {
        for (int i = 0; i < allProducts.size(); i++) {
            Product productFound = allProducts.get(i);
            if (productFound.getIdProduct() == productId) {
                return productFound;
            }
        }
        return null;
    }

    /**
     * This method returns a list of all parts.
     *
     * @return an ObservableList of all parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * This method looks up a part by name and returns a list of all matching parts.
     *
     * @param partName the name of the part(s) to be looked up
     * @return an ObservableList of all matching parts
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> parts = FXCollections.observableArrayList();
        for (Part part : getAllParts()) {
            if (part.getName().contains(partName)) {
                parts.add(part);
            }
        }
        return parts;
    }

    /**
     * This method returns a list of all products.
     *
     * @return an ObservableList of all products
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }


    /**
     * Finds products based on the given product name
     *
     * @param productName the name of the product to be searched
     * @return an ObservableList of Product objects that match the given product name
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> products = FXCollections.observableArrayList();
        for (Product product : getAllProducts()) {
            if (product.getName().contains(productName)) {
                products.add(product);
            }
        }
        return products;
    }

    /**
     * Updates a Part object at the given index with a new Part object
     *
     * @param index        the index of the Part object to be updated
     * @param selectedPart the new Part object to replace the old Part object
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * Updates a Product object at the given index with a new Product object
     *
     * @param index      the index of the Product object to be updated
     * @param newProduct the new Product object to replace the old Product object
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * Deletes a Part object if found
     *
     * @param selectedPart the Part object to be deleted
     * @return true if the Part object was found and deleted, false otherwise
     */
    public static boolean deletePart(Part selectedPart) {
        for (Part partToDelete : allParts) {
            if (partToDelete.getId() == selectedPart.getId()) {
                allParts.remove(partToDelete);
                return true;
            }
        }
        return false;
    }

    /**
     * This method deletes a product from the "allProducts" list if a product with the same id as the "selectedProduct" is found.
     *
     * @param selectedProduct The product to be deleted from the "allProducts" list.
     * @return Returns true if the product is successfully deleted, otherwise returns false.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        for (Product productToDelete : allProducts) {
            if (productToDelete.getIdProduct() == selectedProduct.getIdProduct()) {
                allProducts.remove(productToDelete);
                return true;
            }
        }
        return false;
    }
}
