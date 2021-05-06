package input;

public abstract class EntityInput {
    private int id;
    private int initialBudget;

    /**
     * @return the entity's id
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
     * @return the entity's initial budget
     */
    public int getInitialBudget() {
        return initialBudget;
    }

    /**
     * Sets the entity's initial budget
     * @param initialBudget the new initial budget
     */
    public void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }
}
