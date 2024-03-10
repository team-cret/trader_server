package traderV0.trader.dto;

import lombok.Getter;
import traderV0.trader.entity.Company;

@Getter
public class SingleHasDto {
    private Company company;
    private int hasPrice;
    private int quantity;

    public SingleHasDto(Company company, int hasPrice, int quantity) {
        this.company = company;
        this.hasPrice = hasPrice;
        this.quantity = quantity;
    }
}
