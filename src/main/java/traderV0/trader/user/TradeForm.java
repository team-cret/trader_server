package traderV0.trader.user;

import lombok.Getter;

@Getter
public class TradeForm {

    private String userSession;
    private Long companyId;
    private int price;
    private int quantity;

    public TradeForm(String userSession, Long companyId, int price, int quantity) {
        this.userSession = userSession;
        this.companyId = companyId;
        this.price = price;
        this.quantity = quantity;
    }
}
