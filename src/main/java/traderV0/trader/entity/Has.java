package traderV0.trader.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Has {
    @Id @GeneratedValue
    @Column(name = "has_id")
    private Long id;

    private int price;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Has(int price, int quantity, UserInfo userInfo, Company company) {
        this.price = price;
        this.quantity = quantity;
        this.userInfo = userInfo;
        this.company = company;
    }

    public void subtractQuantity(int subtractedQuantity) {
        this.quantity -= subtractedQuantity;
    }

    public void changePriceAndQantity(int newPrice, int newQauntity){
        this.price = newPrice;
        this.quantity = newQauntity;
    }
}
