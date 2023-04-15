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

/*Inventory class with items of inventory and manipulating functions of inventory */
public class Inventory {
    /*Objects allParts and allProducts of type ObservableList*/
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /*@Param Part newPart added to allParts*/
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /*@Param Product newProduct added to allProducts*/
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /*@Param Part lookupPart to find part and return partFound if found and null if not found*/
    public static Part lookupPart(int partId) {
        for (int i = 0; allParts.size() > i; i++) {
            Part partFound = allParts.get(i);
            if (partFound.getId() == partId) {
                return partFound;
            }
        }
        return null;
    }

    /*@Param Product lookupProduct to find product and return productFound if found and null if not found*/
    public static Product lookupProduct(int productId) {
        for (int i = 0; allProducts.size() > i; i++) {
            Product productFound = allProducts.get(i);
            if (productFound.getIdProduct() == productId) {
                return productFound;
            }
        }
        return null;
    }

    /*@Param returns allParts in list*/
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /*@Param finds part with lookupPart and returns part if found*/
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> parts = FXCollections.observableArrayList();
        for (Part part : getAllParts()) {
            if (part.getName().contains(partName)) {
               parts.add(part);
            }
        }
        return parts;
    }

    /*@Param returns allProducts in list*/
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /*@Param finds product with lookupProduct and returns product if found*/
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> products = FXCollections.observableArrayList();
        for (Product product : getAllProducts()) {
            if (product.getName().contains(productName)) {
                products.add(product);
            }
        }
        return products;
    }

    /*@Param updatePart passes in index of selectedPart to be modified*/
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /*@Param updateProduct passes in index of newProduct to be modified*/
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /*@Param deletePart deletes part found*/
    public static boolean deletePart(Part selectedPart) {
        for (Part partToDelete : allParts) {
            if (partToDelete.getId() == selectedPart.getId()) {
                allParts.remove(partToDelete);
                return true;
            }
        }
        return false;
    }

    /*@Param deletePart deletes product found*/
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
