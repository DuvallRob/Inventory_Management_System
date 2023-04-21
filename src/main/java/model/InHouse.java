package model;

/**
 *
 * @author
 * Duvall Roberts
 * drob801@wgu.edu
 * Student ID: 007792396
 */
public class InHouse extends Part {

    private int machineId;

    /**
     * Constructs an InHouse object with specified id, name, price, stock, min, max, and machine ID.
     *
     * @param id the ID of the InHouse part.
     * @param name the name of the InHouse part.
     * @param price the price of the InHouse part.
     * @param stock the inventory level of the InHouse part.
     * @param min the minimum inventory level of the InHouse part.
     * @param max the maximum inventory level of the InHouse part.
     * @param machineId the machine ID of the InHouse part.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Sets the machine ID of the InHouse part to the specified machine ID.
     *
     * @param machineId the machine ID of the InHouse part.
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * Returns the machine ID of the InHouse part.
     *
     * @return the machine ID of the InHouse part.
     */
    public int getMachineId() {
        return machineId;
    }
}
