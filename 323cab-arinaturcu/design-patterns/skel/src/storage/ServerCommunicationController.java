package storage;

import communication.ServerMessage;
import main.Utils;

import java.util.Observable;
import java.util.Observer;

public class ServerCommunicationController implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        ServerMessage message = new ServerMessage(((SensorData) arg).getStepsCount(),
                Utils.getClientId(), ((SensorData) arg).getTimestamp());

        System.out.println("from ServerCommunicationController: " + message);
    }
}
