package simulator;

import entities.Consumer;
import entities.Distributor;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps the current state of the game
 */
public final class CurrentState {
    private static CurrentState currentState;
    private final List<Consumer> consumers;
    private final List<Distributor> distributors;

    private CurrentState() {
        consumers    = new ArrayList<>();
        distributors = new ArrayList<>();
    }

    /**
     * @return the only instance created
     */
    public static CurrentState getInstance() {
        if (currentState == null) {
            currentState = new CurrentState();
        }
        return currentState;
    }

    /**
     * Resets the instance (makes it null)
     */
    public static void reset() {
        currentState = null;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    public List<Distributor> getDistributors() {
        return distributors;
    }
}
