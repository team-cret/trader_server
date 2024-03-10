package traderV0.trader.login;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import traderV0.trader.dto.ResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/auth")
public class LoginController extends TextWebSocketHandler {

    private final LoginService loginService;

    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody LoginForm form){
        return loginService.signUp(form);
    }

    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginForm form) {
        return loginService.login(form);
    }

}
