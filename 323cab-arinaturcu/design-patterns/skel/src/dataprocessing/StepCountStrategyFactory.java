package dataprocessing;

import main.Utils;
import storage.DataRepository;

public class StepCountStrategyFactory {
    public StepCountStrategy createStrategy(String strategyType, DataRepository dataRepository) {
        return switch (strategyType) {
            case Utils.BASIC_STRATEGY -> new BasicStepCountStrategy(dataRepository);
            case Utils.FILTERED_STRATEGY -> new FilteredStepCountStrategy(dataRepository);
            default -> null;
        };
    }
}
