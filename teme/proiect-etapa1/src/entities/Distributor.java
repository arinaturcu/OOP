package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import contract.Contract;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({ "id", "budget", "isBankrupt", "contracts" })
@JsonIgnoreProperties({ "contractLength", "infrastructureCost", "productionCost",
                                                "currentContractPrice", "profit" })
public final class Distributor extends Entity {
    private static final double PROFIT_PERCENTAGE = 0.2;
    private int contractLength;
    private int infrastructureCost;
    private int productionCost;
    private List<Contract> contracts;
    private int currentContractPrice;
    private int profit;

    public Distributor(final int id, final boolean isBankrupt, final int budget,
                       final int contractLength, final int infrastructureCost,
                       final int productionCost) {
        super(id, isBankrupt, budget);

        this.contractLength       = contractLength;
        this.infrastructureCost   = infrastructureCost;
        this.productionCost       = productionCost;
        this.contracts            = new ArrayList<>();
        this.currentContractPrice = 0;
        this.profit               = (int) Math.round(Math.
                                    floor(PROFIT_PERCENTAGE * this.productionCost));
    }

    /**
     * Finds the distributor with the lowest contract price
     * @param distributors the list of distributors
     * @return the cheapest distributor
     */
    public static Distributor findCheapestDistributor(final List<Distributor> distributors) {
        Distributor cheapest = null;

        for (Distributor d : distributors) {
            if (!d.getIsBankrupt()) {
                cheapest = d;
                break;
            }
        }

        if (cheapest == null) {
            return null;
        }

        for (Distributor d : distributors) {
            if (!d.getIsBankrupt() && d.getCurrentContractPrice()
                    < cheapest.getCurrentContractPrice()) {
                cheapest = d;
            }
        }
        return cheapest;
    }

    /**
     * Finds the distributor with the given id
     * @param distributors the list of total distributor
     * @param id the distributor's id
     * @return the distributor
     */
    public static Distributor findDistributorById(final List<Distributor> distributors,
                                                  final int id) {
        for (Distributor distributor : distributors) {
            if (distributor.getId() == id) {
                return distributor;
            }
        }
        return null;
    }


    /**
     * Updates all distributors contract prices
     * @param distributors the list of distributors
     */
    public static void updateDistributorsPrices(final List<Distributor> distributors) {
        for (Distributor d : distributors) {
            d.updateContractPrice();
        }
    }

    /**
     * Pays the cost of the distributor
     * @param consumers the list of total consumers
     */
    public void payCosts() {
        int toPay = infrastructureCost + productionCost * contracts.size();

        if (getBudget() < toPay) {
            setIsBankrupt(true);
            contracts.clear();
        }
        setBudget(getBudget() - toPay);
    }

    /**
     * Sets the new contracts prices
     */
    public void updateContractPrice() {
        if (contracts.size() == 0) {
            currentContractPrice = infrastructureCost + productionCost + profit;
        } else {
            currentContractPrice = infrastructureCost / contracts.size()
                    + productionCost + profit;
        }
    }

    /**
     * Updates the profit
     */
    public void updateProfit() {
        this.profit = (int) Math.round(Math.floor(PROFIT_PERCENTAGE * this.productionCost));
    }

    public int getContractLength() {
        return contractLength;
    }

    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public int getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(final int productionCost) {
        this.productionCost = productionCost;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(final List<Contract> contracts) {
        this.contracts = contracts;
    }

    public int getCurrentContractPrice() {
        return currentContractPrice;
    }

    public void setCurrentContractPrice(final int currentContractPrice) {
        this.currentContractPrice = currentContractPrice;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(final int profit) {
        this.profit = profit;
    }
}
