package traderV0.trader.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MinStick {

    @Id @GeneratedValue
    @Column(name = "min_stick_id")
    private Long id;

    private int startPrice;
    private int endPrice;
    private int highPrice;
    private int lowPrice;
    private LocalDateTime minStickDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public MinStick(int startPrice, int endPrice, int highPrice, int lowPrice, LocalDateTime minStickDate, Company company) {
        this.startPrice = startPrice;
        this.endPrice = endPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.minStickDate = minStickDate;
        this.company = company;
    }
}
