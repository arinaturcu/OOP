package simulator;

import entities.Consumer;
import entities.Distributor;
import entities.EntityFactory;
import input.ConsumerInput;
import input.DistributorInput;
import contract.Contract;
import input.Input;

public final class Simulator {

    /**
     * Runs the simulation
     * @param input the input data
     */
    public void run(final Input input) {
        CurrentState currentState = CurrentState.getInstance();
        buildInitialStructure(input);

        runInitialTurn();
        if (SimulatorUtils.isGameOver(currentState.getDistributors())) {
            return;
        }

        for (int i = 0; i < input.getNumberOfTurns(); ++i) {
            SimulatorUtils.applyMonthlyUpdate(input.getMonthlyUpdates().get(i));
            Distributor.updateDistributorsPrices(currentState.getDistributors());
            runMonthlyTurn();

            if (SimulatorUtils.isGameOver(currentState.getDistributors())) {
                return;
            }
        }
    }

    private void runInitialTurn() {
        CurrentState currentState = CurrentState.getInstance();
        Distributor.updateDistributorsPrices(currentState.getDistributors());

        Distributor bestDistributor = Distributor.
                findCheapestDistributor(currentState.getDistributors());
        assert bestDistributor != null;

        for (Consumer c : currentState.getConsumers()) {
            Contract newContract = new Contract(bestDistributor.getId(), c.getId(),
                                                bestDistributor.getCurrentContractPrice(),
                                                bestDistributor.getContractLength());

            newContract.setRemainedContractMonths(newContract.getRemainedContractMonths() - 1);
            bestDistributor.getContracts().add(newContract);

            c.receiveMonthlyIncome();
            c.payUtilities(bestDistributor);
        }

        for (Distributor d : currentState.getDistributors()) {
            d.payCosts();
        }
    }

    private void runMonthlyTurn() {
        CurrentState currentState = CurrentState.getInstance();
        SimulatorUtils.removeExpiredContracts();

        for (Consumer c : currentState.getConsumers()) {
            if (!c.getIsBankrupt()) {
                c.receiveMonthlyIncome();

                Contract contract = c.findThisConsumersContract(currentState.getDistributors());
                if (contract == null) {
                    contract = c.findNewContract(currentState.getDistributors());
                    if (contract == null) {
                        // this code should be reached only when all distributors
                        // are bankrupt but this will never happen because of the
                        // SimulatorUtils.isGameOver method that is run before entering the
                        // current method
                        return;
                    }
                }

                // finds the distributor with whom the consumer has a contract
                Distributor distributor = Distributor.
                        findDistributorById(currentState.getDistributors(),
                        contract.getDistributorId());
                // will never be null
                assert distributor != null;

                if (!c.isInDebt()) {
                    c.payUtilities(distributor);
                } else if (!c.getIsBankrupt()) {
                    c.payUtilitiesWithInterest(distributor);
                }
            }
        }

        SimulatorUtils.decreaseNumberOfContractMonths(currentState.getDistributors());

        for (Distributor d : currentState.getDistributors()) {
            if (!d.getIsBankrupt()) {
                d.payCosts();
            }
        }

        SimulatorUtils.removeBankruptConsumersContracts();
    }

    private void buildInitialStructure(final Input input) {
        CurrentState currentState = CurrentState.getInstance();

        for (ConsumerInput ci : input.getInitialData().getConsumers()) {
            currentState.getConsumers().add((Consumer) EntityFactory
                    .createEntity(EntityFactory.EntityType.CONSUMER, ci));
        }

        for (DistributorInput di : input.getInitialData().getDistributors()) {
            currentState.getDistributors().add((Distributor) EntityFactory
                    .createEntity(EntityFactory.EntityType.DISTRIBUTOR, di));
        }
    }
}
