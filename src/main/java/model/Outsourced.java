package model;

/**
 *
 * @author
 * Duvall Roberts
 * drob801@wgu.edu
 * Student ID: 007792396
 */

/**Outsourced class that extends from Part class*/
public class Outsourced extends Part {
    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**@param companyName to set companyName*/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**@return companyName*/
    public String getCompanyName() {
        return companyName;
    }

}
