package traderV0.trader.game;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.TextMessage;
import traderV0.trader.constant.GameConst;
import traderV0.trader.constant.UserState;
import traderV0.trader.dto.ResponseDto;
import traderV0.trader.dto.SingleRankDto;
import traderV0.trader.dto.SuccessDto;
import traderV0.trader.dto.UserRankingDto;
import traderV0.trader.entity.*;
import traderV0.trader.function.MakeSocketMessage;
import traderV0.trader.repository.*;
import traderV0.trader.websocket.WebSocketHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private final UserRepository userRepository;
    private final HasRepository hasRepository;
    private final FavoriteRepository favoriteRepository;
    private final CompanyRepository companyRepository;
    private final MinStickRepository minStickRepository;
    private final WebSocketHandler webSocketHandler;
    private final MakeSocketMessage makeSocketMessage;

    private boolean isGameStart = false;
    private int gameTime = 0;

    @Transactional
    public ResponseDto isGameReady(){
        List<UserInfo> findUsers = userRepository.findByValidWaitingUserOrderByDate(UserState.WAITING, LocalDateTime.now().minusMinutes(GameConst.maximumSessionMinute), LocalDateTime.now(), Pageable.ofSize(GameConst.maximumPlayerNumber));

        if (isGameStart == false && findUsers.size() == GameConst.maximumPlayerNumber){
            for (UserInfo findUser : findUsers) {
                findUser.changeStateToPlaying();
            }
            startGame();
            isGameStart = true;
            webSocketHandler.sendAllPlayingUser(makeSocketMessage.basicJsonMessage("game", "status", "PLAYING"));
            return new SuccessDto(201, HttpStatus.CREATED, "Game Start");
        }else{
            log.warn("isGameReady");
            webSocketHandler.sendAllWaitingUser(makeSocketMessage.basicJsonMessage("game", "wait", String.valueOf(findUsers.size())));
            return new SuccessDto(202, HttpStatus.ACCEPTED, "Game Waiting");
        }
    }

    public ResponseDto getRank(){
        List<SingleRankDto> userRank = userRepository.findRankByState(UserState.PLAYING);
        return new UserRankingDto(202, HttpStatus.ACCEPTED, userRank);
    }

    @Scheduled(fixedRate = 1000)
    @Transactional
    public void gameFlow(){
        if (gameTime == GameConst.gameEndTime){
            endGame();
        }

        if (isGameStart == true && userRepository.findByState(UserState.PLAYING).isEmpty()){
            endGame();
        }

        if (isGameStart == true) {

            if (gameTime % GameConst.stickTime == 0){
                List<Company> companyList = companyRepository.findAll();
                for (Company company : companyList) {
                    company.changeStockPriceByRate();
                    company.updateEndPrice(company.getStockPrice());
                    minStickRepository.save(new MinStick(company.getStartPrice(), company.getEndPrice(),
                                                         company.getHighPrice(), company.getLowPrice(),
                                                         LocalDateTime.now(), company));
                    company.initMinStick(company.getEndPrice());
                }
            }


            if (gameTime % GameConst.newsGenerateTime == 0) {
                getNews();
            }
            gameTime += 1;
        }
    }

    private ArrayList getMarkets(){
        RestTemplate restTemplate = new RestTemplate();
        ArrayList response = restTemplate.getForObject(GameConst.serverUrlMarket, ArrayList.class);
        log.info("New News Generated");
        return response;
    }

    private void getNews(){
        RestTemplate restTemplate = new RestTemplate();
        ArrayList response = restTemplate.getForObject(GameConst.serverUrlNews, ArrayList.class);
        for (Object o : response) {
            String[] split = o.toString().split(",");
            Company findCompany = companyRepository.findByName(split[0]);
            findCompany.changePriceVarianceByNews(Double.parseDouble(split[1]));
            webSocketHandler.sendAllPlayingUser(makeSocketMessage.basicJsonMessage("news", "id", String.valueOf(findCompany.getId())));
            webSocketHandler.sendAllPlayingUser(makeSocketMessage.basicJsonMessage("news", "step1", split[2]));
            webSocketHandler.sendAllPlayingUser(makeSocketMessage.basicJsonMessage("news", "step2", split[3]));
            webSocketHandler.sendAllPlayingUser(makeSocketMessage.basicJsonMessage("news", "step3", split[4]));
        }
        log.info("New News Generated");
    }

    private void startGame(){
        ArrayList markets = getMarkets();
        for (Object market : markets) {
            log.info("market name : {}",market.toString());
            int startPrice = (new Random().nextInt(90) + 10) * 1000;
            Company initCompany = new Company(market.toString(), startPrice, startPrice, 1);
            initCompany.initMinStick(initCompany.getStockPrice());
            companyRepository.save(initCompany);
        }
        log.info("Initial Company");
    }

    private void endGame(){
        log.info("Game End");
        isGameStart = false;
        gameTime = 0;

        List<Company> companyList = companyRepository.findAll();
        for (Company company : companyList) {
            company.changeStockPriceByRate();
        }

        List<Has> restHas = hasRepository.findAll();
        for (Has has : restHas) {
            UserInfo hasUser = has.getUserInfo();
            hasUser.addMoney(has.getQuantity() * has.getCompany().getStockPrice());
        }

        List<UserInfo> findUsers = userRepository.findByStateOrderByMoneyDesc(UserState.PLAYING);
        webSocketHandler.sendAllPlayingUser(makeSocketMessage.basicJsonMessage("game","status","end"));

        for (UserInfo findUser : findUsers) {
            findUser.changeStateToOffLine();
        }


        favoriteRepository.deleteAll();
        hasRepository.deleteAll();
        minStickRepository.deleteAll();
        companyRepository.deleteAll();

        log.info("Delete Data Complete");
    }




}
