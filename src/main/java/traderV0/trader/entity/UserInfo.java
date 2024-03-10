package traderV0.trader.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import traderV0.trader.constant.GameConst;
import traderV0.trader.constant.UserState;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserInfo {
    @Id
    @Column(name = "user_id")
    private String id;

    private String password;
    private String name;
    private Integer money;

    @Enumerated(EnumType.STRING)
    private UserState state;

    private String httpSession;
    private String webSession;
    private LocalDateTime sessionDate;

    public UserInfo(String id, String password, String name, Integer money, UserState state) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.money = money;
        this.state = state;
    }

    public void addMoney(int profit) {
        this.money += profit;
    }

    public void subtractMoney(int loss){
        this.money -= loss;
    }

    public void changeStateToPlaying(){
        this.state = UserState.PLAYING;
        this.money = GameConst.initialMoney;
    }

    public void changeStateToWaiting(){
        this.state = UserState.WAITING;
        this.money = null;
    }

    public void changeStateToOffLine(){
        this.state = UserState.OFFLINE;
        this.money = null;
    }

    public void setHttpSession(String httpSession) {
        this.httpSession = httpSession;
    }

    public void setWebSession(String webSession) {
        this.webSession = webSession;
    }

    public void updateSessionDate(){
        this.sessionDate = LocalDateTime.now();
    }
}
