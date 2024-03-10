package traderV0.trader.company;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import traderV0.trader.dto.ResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/company")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/list")
    public ResponseDto getCompanyList(){
        return companyService.getCompanyList();
    }

    @GetMapping("graph")
    public ResponseDto getCompanyGraph(@RequestParam("companyId") Long companyId){
        return companyService.getCompanyGraph(companyId);
    }
}
