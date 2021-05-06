package storage;

import dataprocessing.StepCountStrategy;

import java.util.Observable;
import java.util.Observer;

public class DataAggregator implements Observer {
    StepCountStrategy strategy;

    public DataAggregator (StepCountStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Total steps: " + strategy.getTotalSteps());
    }
}
