package first;

public class OutTask implements Task {
    private final String output;

    public OutTask(String output) {
        this.output = output;
    }

    public void execute() {
        System.out.println(output);
    }
}
