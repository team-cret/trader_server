package traderV0.trader.game;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import traderV0.trader.dto.ResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/game")
public class GameController {

    private final GameService gameService;

    @GetMapping("/isgameready")
    public ResponseDto isGameReady(){
        return gameService.isGameReady();
    }

    @GetMapping("/rank")
    public ResponseDto getRank(){
        return gameService.getRank();
    }
}
