package communication;

/**
 * Represents the content of the message sent to the server.
 */
public class ServerMessage {
    private int steps;
    private int clientId;
    private long timestamp;

    public ServerMessage(int steps, int clientId, long timestamp) {
        this.steps = steps;
        this.clientId = clientId;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "{" +
                "steps=" + steps +
                ", clientId=" + clientId +
                ", timestamp=" + timestamp +
                '}';
    }
}
