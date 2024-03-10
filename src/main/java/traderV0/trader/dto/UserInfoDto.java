package traderV0.trader.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserInfoDto  extends ResponseDto{
    private String name;
    private Integer money;

    public UserInfoDto(int status, HttpStatus httpStatus, String name, Integer money) {
        super(status, httpStatus);
        this.name = name;
        this.money = money;
    }
}
