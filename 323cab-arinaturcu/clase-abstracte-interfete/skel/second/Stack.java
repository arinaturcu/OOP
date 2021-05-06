package second;

import first.Task;

public class Stack extends ContainerQS {
    @Override
    public Task pop() {
        return super.getTasks().remove(super.size() - 1);
    }
}
