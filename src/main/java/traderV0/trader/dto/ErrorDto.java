package traderV0.trader.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorDto extends ResponseDto{
    private String errorMessage;

    public ErrorDto(int status, HttpStatus httpStatus, String errorMessage) {
        super(status, httpStatus);
        this.errorMessage = errorMessage;
    }
}
