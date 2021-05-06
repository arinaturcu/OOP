package dataprocessing;

import storage.DataRepository;
import storage.SensorData;

public class BasicStepCountStrategy implements StepCountStrategy {
    private DataRepository dataRepository;

    public BasicStepCountStrategy (DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public int getTotalSteps() {
        int sum = 0;
        for (SensorData data : dataRepository.getData()) {
            sum += data.getStepsCount();
        }

        return sum;
    }

    @Override
    public String getStrategyDescription() {
        return "basic";
    }
}
