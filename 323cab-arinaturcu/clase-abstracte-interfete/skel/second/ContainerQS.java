package second;

import first.Task;

import java.util.ArrayList;

public abstract class ContainerQS implements Container {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public abstract Task pop();

    @Override
    public void push(Task task) {
        tasks.add(task);
    }

    @Override
    public int size() {
        return tasks.size();
    }

    @Override
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    @Override
    public void transferFrom(Container container) {
        tasks.addAll(((ContainerQS) container).getTasks());

        ArrayList<Task> list = ((ContainerQS) container).getTasks();
        list.clear();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
