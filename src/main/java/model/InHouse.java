package model;

/**
 *
 * @author
 * Duvall Roberts
 * drob801@wgu.edu
 * Student ID: 007792396
 */

/**InHouse class that extends from Part class*/
public class InHouse extends Part {
    private int machineId;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**@param machineId to set machineId*/
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**@return machineId*/
    public int getMachineId() {
        return machineId;
    }
}
