package traderV0.trader.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LoginDto extends ResponseDto {
    private String uuid;

    public LoginDto(int status, HttpStatus httpStatus, String uuid) {
        super(status, httpStatus);
        this.uuid = uuid;
    }
}
