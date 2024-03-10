package traderV0.trader.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SuccessDto extends ResponseDto{
    private String SuccessMessage;

    public SuccessDto(int status, HttpStatus httpStatus, String successMessage) {
        super(status, httpStatus);
        SuccessMessage = successMessage;
    }
}
