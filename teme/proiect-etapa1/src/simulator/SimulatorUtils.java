package simulator;

import entities.Consumer;
import entities.Distributor;
import entities.EntityFactory;
import input.ConsumerInput;
import input.CostsChange;
import input.MonthlyUpdate;

import java.util.List;

/**
 * Contains static methods that are helpful for doing the simulation
 */
public final class SimulatorUtils {
    private SimulatorUtils() {

    }

    /**
     * Applies the monthly update
     * @param update the update to be applied
     */
    public static void applyMonthlyUpdate(final MonthlyUpdate update) {
        CurrentState currentState = CurrentState.getInstance();

        for (ConsumerInput newConsumer : update.getNewConsumers()) {
            currentState.getConsumers().add((Consumer) EntityFactory.createEntity(
                                                       EntityFactory.EntityType.CONSUMER,
                                                       newConsumer));
        }

        for (CostsChange costsChange : update.getCostsChanges()) {
            Distributor distributor = Distributor.
                    findDistributorById(currentState.getDistributors(), costsChange.getId());

            assert distributor != null;
            distributor.setInfrastructureCost(costsChange.getInfrastructureCost());
            distributor.setProductionCost(costsChange.getProductionCost());
            distributor.updateProfit();
        }
    }

    /**
     * Removes contracts with no remaining months
     */
    public static void removeExpiredContracts() {
        CurrentState currentState = CurrentState.getInstance();

        for (Distributor d : currentState.getDistributors()) {
            d.getContracts().removeIf(contract -> {
                Consumer consumer = Consumer.findConsumerById(currentState.getConsumers(),
                        contract.getConsumerId());

                assert consumer != null;
                if (contract.getRemainedContractMonths() == 0 && consumer.isInDebt()) {
                    return false;
                }
                return contract.getRemainedContractMonths() == 0;
            });
        }
    }

    /**
     * Decreases the number of remaining months in each contract
     * @param distributors the list of distributors
     */
    public static void decreaseNumberOfContractMonths(final List<Distributor> distributors) {
        for (Distributor distributor : distributors) {
            distributor.getContracts().forEach(contract ->
                    contract.setRemainedContractMonths(contract.getRemainedContractMonths() - 1));
        }
    }

    /**
     * Removes all contracts with bankrupt clients
     */
    public static void removeBankruptConsumersContracts() {
        CurrentState currentState = CurrentState.getInstance();

        for (Distributor distributor : currentState.getDistributors()) {
            distributor.getContracts().removeIf(contract -> {
                Consumer consumer = Consumer.findConsumerById(currentState.getConsumers(),
                        contract.getConsumerId());
                if (consumer != null) {
                    return consumer.getIsBankrupt();
                }
                return false;
            });
        }
    }

    /**
     * Checks if all distributors are bankrupt
     * @param distributors the list of distributors
     * @return false if there is a distributor that is not bankrupt
     */
    public static boolean isGameOver(final List<Distributor> distributors) {
        for (Distributor d : distributors) {
            if (!d.getIsBankrupt()) {
                return false;
            }
        }
        return true;
    }
}
