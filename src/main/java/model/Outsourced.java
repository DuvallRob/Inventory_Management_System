package model;

/**
 *
 * @author
 * Duvall Roberts
 * drob801@wgu.edu
 * Student ID: 007792396
 */

/**
 * The Outsourced class is a subclass of the Part class, representing a part that is purchased from an external company.
 */
public class Outsourced extends Part {

    /** The name of the external company that supplies this part. */
    private String companyName;

    /**
     * Constructs an Outsourced object with the specified attributes.
     *
     * @param id the unique ID of the part
     * @param name the name of the part
     * @param price the price of the part
     * @param stock the current stock level of the part
     * @param min the minimum stock level of the part
     * @param max the maximum stock level of the part
     * @param companyName the name of the external company that supplies this part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Sets the name of the external company that supplies this part.
     *
     * @param companyName the name of the external company
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Returns the name of the external company that supplies this part.
     *
     * @return the name of the external company
     */
    public String getCompanyName() {
        return companyName;
    }

}
