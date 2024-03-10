package traderV0.trader.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.util.List;

@Getter
public class HasDto extends ResponseDto{
    private List<SingleHasDto> result;

    public HasDto(int status, HttpStatus httpStatus, List<SingleHasDto> result) {
        super(status, httpStatus);
        this.result = result;
    }
}
