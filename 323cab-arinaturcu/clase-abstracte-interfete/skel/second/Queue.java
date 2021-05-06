package second;

import first.Task;

public class Queue extends ContainerQS {
    @Override
    public Task pop() {
        return super.getTasks().remove(0);
    }
}
