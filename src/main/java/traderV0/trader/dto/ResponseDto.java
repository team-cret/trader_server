package traderV0.trader.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ResponseDto {
    private int status;
    private HttpStatus httpStatus;

    public ResponseDto(int status, HttpStatus httpStatus) {
        this.status = status;
        this.httpStatus = httpStatus;
    }
}
