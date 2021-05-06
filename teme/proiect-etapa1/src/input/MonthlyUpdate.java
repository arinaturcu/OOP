package input;

import java.util.List;

public final class MonthlyUpdate {
    private List<ConsumerInput> newConsumers;
    private List<CostsChange> costsChanges;

    public List<ConsumerInput> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final List<ConsumerInput> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public List<CostsChange> getCostsChanges() {
        return costsChanges;
    }

    public void setCostsChanges(final List<CostsChange> costsChanges) {
        this.costsChanges = costsChanges;
    }
}
