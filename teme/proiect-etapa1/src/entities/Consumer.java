package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import contract.Contract;

import java.util.List;

@JsonIgnoreProperties({ "monthlyIncome", "inDebt" })
public final class Consumer extends Entity {
    private static final double INTEREST_PERCENTAGE = 1.2;
    private int monthlyIncome;
    private boolean inDebt;

    public Consumer(final int id, final boolean isBankrupt, final int budget,
                    final int monthlyIncome) {
        super(id, isBankrupt, budget);
        this.monthlyIncome = monthlyIncome;
        this.inDebt        = false;
    }

    /**
     * Finds the consumer with the given id
     * @param consumers the list of total consumers
     * @param id the consumer's id
     * @return the consumer
     */
    public static Consumer findConsumerById(final List<Consumer> consumers,
                                            final int id) {
        for (Consumer consumer : consumers) {
            if (consumer.getId() == id) {
                return consumer;
            }
        }
        return null;
    }

    /**
     * Adds the monthly income to the budget
     */
    public void receiveMonthlyIncome() {
        setBudget(getBudget() + monthlyIncome);
    }

    /**
     * Gets the money for one month of the contract from the consumer's budget and
     * gives it to the distributor
     * @param d the distributor to be payed
     */
    public void payUtilities(final Distributor d) {
        Contract contract = null;
        for (Contract c : d.getContracts()) {
            if (c.getConsumerId() == this.getId()) {
                contract = c;
            }
        }
        if (contract == null) {
            return;
        }

        if (contract.getPrice() > getBudget()) {
            inDebt = true;
            return;
        }

        setBudget(getBudget() - contract.getPrice());
        d.setBudget(d.getBudget() + contract.getPrice());
    }

    /**
     * Searches for the contract of the consumer whom id is given
     * @param distributors the list of distributors
     * @return the contract
     */
    public Contract findThisConsumersContract(final List<Distributor> distributors) {
        for (Distributor distributor : distributors) {
            if (distributor.getIsBankrupt()) {
                continue;
            }
            for (Contract contract : distributor.getContracts()) {
                if (contract.getConsumerId() == this.getId()) {
                    return contract;
                }
            }
        }
        return null;
    }

    /**
     * Gets the money for 2 months of the contract plus the interest from the consumer's
     * budget and gives it to the distributor
     * @param d the distributor to be payed
     */
    public void payUtilitiesWithInterest(final Distributor d) {
        Contract contract = null;
        for (Contract c : d.getContracts()) {
            if (c.getConsumerId() == this.getId()) {
                contract = c;
            }
        }
        if (contract == null) {
            return;
        }

        int toPay = (int) Math.round(Math.floor(INTEREST_PERCENTAGE * contract.getPrice()))
                + contract.getPrice();

        if (toPay > getBudget()) {
            setIsBankrupt(true);
            return;
        }

        setBudget(getBudget() - toPay);
        d.setBudget(d.getBudget() + toPay);
    }

    /**
     * Makes a contract with the cheapest distributor
     * @param distributors the list of distributors
     * @return the new contract or null if the consumer can't afford any
     */
    public Contract findNewContract(final List<Distributor> distributors) {
        Distributor bestDistributor = Distributor.findCheapestDistributor(distributors);

        if (bestDistributor == null) {
            return null;
        }

        Contract newContract = new Contract(bestDistributor.getId(), this.getId(),
                bestDistributor.getCurrentContractPrice(),
                bestDistributor.getContractLength());
        this.setInDebt(false);
        bestDistributor.getContracts().add(newContract);

        return newContract;
    }

    public boolean isInDebt() {
        return inDebt;
    }

    public void setInDebt(final boolean inDebt) {
        this.inDebt = inDebt;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}
