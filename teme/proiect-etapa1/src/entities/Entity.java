package entities;

public abstract class Entity {
    private int id;
    private boolean isBankrupt;
    private int budget;

    public Entity(final int id, final boolean isBankrupt, final int budget) {
        this.id = id;
        this.isBankrupt = isBankrupt;
        this.budget = budget;
    }

    /**
     * Gives the entity's id
     * @return entity's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the entity's id
     * @param id the new id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @return true if entity is bankrupt
     */
    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    /**
     * Sets the current state of the entity (bankrupt or not)
     * @param bankrupt the state
     */
    public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    /**
     * @return the entity's budget
     */
    public int getBudget() {
        return budget;
    }

    /**
     * Sets the entity's budget
     * @param budget the new budget
     */
    public void setBudget(final int budget) {
        this.budget = budget;
    }
}
