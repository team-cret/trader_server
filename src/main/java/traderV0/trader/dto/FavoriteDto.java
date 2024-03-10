package traderV0.trader.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import traderV0.trader.entity.Company;
import java.util.List;

@Getter
public class FavoriteDto extends ResponseDto{

    private List<Company> result;

    public FavoriteDto(int status, HttpStatus httpStatus, List<Company> result) {
        super(status, httpStatus);
        this.result = result;
    }
}
