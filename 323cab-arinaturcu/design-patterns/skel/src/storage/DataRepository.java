package storage;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Persists sensor data. Observable, its observers are notified when data is added it to.
 */
public class DataRepository extends Observable { // make this an Observable
    ArrayList<SensorData> data = new ArrayList<>();

    public void addData(SensorData dataRecord){
        // store data (e.g. in a List)
        data.add(dataRecord);
        // notify observers
        setChanged();
        notifyObservers(dataRecord);
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    // implement a method to get the stored data (needed by the strategies)
    public ArrayList<SensorData> getData() {
        return data;
    }
}


