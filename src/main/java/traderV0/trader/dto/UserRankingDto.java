package traderV0.trader.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import traderV0.trader.entity.UserInfo;

import java.util.List;

@Getter
public class UserRankingDto extends ResponseDto{
    private List<SingleRankDto> result;
    public UserRankingDto(int status, HttpStatus httpStatus, List<SingleRankDto> result) {
        super(status, httpStatus);
        this.result = result;
    }
}
