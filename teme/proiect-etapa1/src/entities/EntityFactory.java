package entities;

import input.ConsumerInput;
import input.DistributorInput;
import input.EntityInput;

public final class EntityFactory {
    public enum EntityType {
        CONSUMER, DISTRIBUTOR
    }

    private EntityFactory() {

    }

    /**
     * Creates an object based the given type
     * @param type the type of the object
     * @param entity the object to take data from.
     *               Type and entity must match
     * @return the new object
     */
    public static Entity createEntity(final EntityType type, final EntityInput entity) {
        return switch (type) {
            case CONSUMER -> new Consumer(entity.getId(), false,
                    entity.getInitialBudget(), ((ConsumerInput) entity).getMonthlyIncome());
            case DISTRIBUTOR -> new Distributor(entity.getId(), false, entity.getInitialBudget(),
                    ((DistributorInput) entity).getContractLength(),
                    ((DistributorInput) entity).getInitialInfrastructureCost(),
                    ((DistributorInput) entity).getInitialProductionCost());
        };
    }
}
