package traderV0.trader.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import traderV0.trader.constant.GameConst;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Company {
    @Id @GeneratedValue
    @Column(name = "company_id")
    private Long id;

    private String name;
    private int stockPrice;
    private Integer beforePrice;

    private Integer startPrice;
    private Integer endPrice;
    private Integer highPrice;
    private Integer lowPrice;

    private double priceVariance;

    public Company(String name, int stockPrice, Integer beforePrice, double priceVariance) {
        this.name = name;
        this.stockPrice = stockPrice;
        this.beforePrice = beforePrice;
        this.priceVariance = priceVariance;
    }

    public void changeStockPriceByRate(){
        this.beforePrice = this.stockPrice;
        this.stockPrice = (int)(this.stockPrice * this.priceVariance / 100) * 100;
    }

    public void initMinStick(int endPrice){
        this.startPrice = endPrice;
        this.highPrice = endPrice;
        this.lowPrice = endPrice;
        this.endPrice = null;
    }

    public void updateEndPrice(int price){
        this.endPrice = price;
    }

    public void changePriceVarianceByNews(double rate){
        int r = (int)(this.priceVariance * (100 - GameConst.variationRatio_News) + rate * GameConst.variationRatio_News);
        this.priceVariance = r / 100.0;
    }
}
