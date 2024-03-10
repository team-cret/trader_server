package traderV0.trader.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TradeInfoDto extends ResponseDto{
    private int maxSellQuantity;
    private int maxBuyPrice;
    private int stockPrice;

    public TradeInfoDto(int status, HttpStatus httpStatus, int maxSellQuantity, int maxBuyPrice, int stockPrice) {
        super(status, httpStatus);
        this.maxSellQuantity = maxSellQuantity;
        this.maxBuyPrice = maxBuyPrice;
        this.stockPrice = stockPrice;
    }
}
