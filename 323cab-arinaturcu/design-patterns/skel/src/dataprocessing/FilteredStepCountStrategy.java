package dataprocessing;

import storage.DataRepository;
import storage.SensorData;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FilteredStepCountStrategy implements StepCountStrategy {
    private static final int MAX_DIFF_STEPS_CONSECUTIVE_RECORDS = 1000;
    private static final long TIME_FOR_MAX_DIFF = TimeUnit.SECONDS.toMillis(1);
    private DataRepository dataRepository;

    public FilteredStepCountStrategy (DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }
    @Override
    public int getTotalSteps() {
        int sum = 0;
        ArrayList<SensorData> data = dataRepository.getData();

        for (SensorData sensorData : data) {
            // if it's first element, checks only data.getStepCount() > 0
            if (sensorData.getStepsCount() > 0) {
                int index = dataRepository.getData().indexOf(sensorData);

                if (index == 0) {
                    sum += sensorData.getStepsCount();
                } else if (!(sensorData.getTimestamp() - data.get(index - 1).getTimestamp() < TIME_FOR_MAX_DIFF &&
                        sensorData.getStepsCount() - data.get(index - 1).getStepsCount() > MAX_DIFF_STEPS_CONSECUTIVE_RECORDS)) {
                    sum += sensorData.getStepsCount();
                }
            }
        }

        return sum;
    }

    @Override
    public String getStrategyDescription() {
        return "filtered";
    }
}
