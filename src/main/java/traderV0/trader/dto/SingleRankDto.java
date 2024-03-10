package traderV0.trader.dto;

import lombok.Getter;
import traderV0.trader.entity.Company;

@Getter
public class SingleRankDto {
    private String name;
    private int money;

    public SingleRankDto(String name, int money) {
        this.name = name;
        this.money = money;
    }
}
