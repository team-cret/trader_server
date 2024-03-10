package traderV0.trader.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MinStickDto {
    private LocalDateTime date;
    private Integer startPrice;
    private Integer endPrice;
    private Integer HighPrice;
    private Integer LowPrice;

    public MinStickDto(LocalDateTime date, Integer startPrice, Integer endPrice, Integer highPrice, Integer lowPrice) {
        this.date = date;
        this.startPrice = startPrice;
        this.endPrice = endPrice;
        HighPrice = highPrice;
        LowPrice = lowPrice;
    }
}
