package traderV0.trader.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FavoriteModifyDto extends ResponseDto{
    private String Message;

    public FavoriteModifyDto(int status, HttpStatus httpStatus, String message) {
        super(status, httpStatus);
        Message = message;
    }
}
