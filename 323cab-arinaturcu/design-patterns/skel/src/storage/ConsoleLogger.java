package storage;

import java.util.Observable;
import java.util.Observer;

public class ConsoleLogger implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("from ConsoleLogger: " + arg);
    }
}
