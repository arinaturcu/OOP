package contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Keeps the contract's data between a consumer and a distributor
 */
@JsonIgnoreProperties({ "distributorId" })
public final class Contract {
    private int distributorId;
    private int consumerId;
    private int price;
    private int remainedContractMonths;

    public Contract(final int distributorId, final int consumerId,
                    final int price, final int remainedContractMonths) {
        this.distributorId = distributorId;
        this.consumerId    = consumerId;
        this.price         = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(final int distributorId) {
        this.distributorId = distributorId;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(final int consumerId) {
        this.consumerId = consumerId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }
}
