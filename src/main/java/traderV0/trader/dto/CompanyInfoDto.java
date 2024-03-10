package traderV0.trader.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class CompanyInfoDto extends ResponseDto{
    private List<MinStickDto> result;
    public CompanyInfoDto(int status, HttpStatus httpStatus, List<MinStickDto> result) {
        super(status, httpStatus);
        this.result = result;
    }
}
