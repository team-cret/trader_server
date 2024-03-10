package traderV0.trader.company;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import traderV0.trader.dto.*;
import traderV0.trader.entity.Company;
import traderV0.trader.repository.CompanyRepository;
import traderV0.trader.repository.MinStickRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final MinStickRepository minStickRepository;

    public ResponseDto getCompanyList(){
        List<Company> companyList = companyRepository.findAll();
        return new CompanyListDto(202, HttpStatus.ACCEPTED, companyList);
    }

    public ResponseDto getCompanyGraph(Long companyId){
        List<MinStickDto> minStickInfo = minStickRepository.findByCompanyId(companyId);
        return new CompanyInfoDto(202, HttpStatus.ACCEPTED, minStickInfo);
    }


}
