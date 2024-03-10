package traderV0.trader.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import traderV0.trader.dto.ResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public ResponseDto getUserInfo(@RequestParam("userSession") String httpSession){
        return userService.getUserInfo(httpSession);
    }

    @GetMapping("/favorite")
    public ResponseDto getUserFavorite(@RequestParam("userSession") String httpSession){
        return userService.getUserFavorite(httpSession);
    }

    @PostMapping("/favorite/modify")
    public ResponseDto modifyUserFavorite(@RequestBody UserCompanyForm userCompanyForm){
        return userService.modifyUserFavorite(userCompanyForm);
    }

    @GetMapping("/has")
    public ResponseDto getUserHas(@RequestParam("userSession") String httpSession){
        return userService.getUserHas(httpSession);
    }

    @GetMapping("/tradeinfo")
    public ResponseDto getTradeInfo(@RequestParam("userSession") String httpSession, @RequestParam("companyId") Long companyId){
        return userService.getTradeInfo(httpSession, companyId);
    }

    @PostMapping("/trade_sell")
    public ResponseDto tradeSell(@RequestBody TradeForm tradeForm){
        return userService.tradeSell(tradeForm);
    }

    @PostMapping("/trade_buy")
    public ResponseDto tradeBuy(@RequestBody TradeForm tradeForm){
        return userService.tradeBuy(tradeForm);
    }

}
