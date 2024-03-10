package traderV0.trader.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import traderV0.trader.dto.*;
import traderV0.trader.entity.*;
import traderV0.trader.function.Security;
import traderV0.trader.repository.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final FavoriteRepository favoriteRepository;
    private final HasRepository hasRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final Security security;

    public ResponseDto getUserInfo(String httpSession){
        Optional<UserInfo> findUserOpt = userRepository.findByHttpSession(httpSession);
        if (findUserOpt.isEmpty()){
            return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Invalid Session");
        }else{
            UserInfo findUser = findUserOpt.get();
            if (security.isSessionValidDate(findUser.getSessionDate()) == false) {
                return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Session Has Expired");
            } else{
                findUser.updateSessionDate();
                return new UserInfoDto(202, HttpStatus.ACCEPTED, findUser.getName(), findUser.getMoney());
            }
        }


    }

    public ResponseDto getUserFavorite(String httpSession){
        Optional<UserInfo> findUserOpt = userRepository.findByHttpSession(httpSession);
        if (findUserOpt.isEmpty()){
            return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Invalid Session");
        }else {
            UserInfo findUser = findUserOpt.get();
            if (security.isSessionValidDate(findUser.getSessionDate()) == false) {
                return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Session Has Expired");
            }else{
                findUser.updateSessionDate();
                List<Company> favoriteCompanies = favoriteRepository.findCompaniesByUserId(findUser.getId());

                return new FavoriteDto(202, HttpStatus.ACCEPTED, favoriteCompanies);
            }
        }
    }

    public ResponseDto modifyUserFavorite(UserCompanyForm form){

        Optional<UserInfo> findUserOpt = userRepository.findByHttpSession(form.getUserSession());
        if (findUserOpt.isEmpty()){
            return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Invalid Session");
        }else {
            UserInfo findUser = findUserOpt.get();
            if (security.isSessionValidDate(findUser.getSessionDate()) == false) {
                return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Session Has Expired");
            }else{
                findUser.updateSessionDate();
                Company findCompany = companyRepository.findById(form.getCompanyId()).get();
                Long findFavoriteId = favoriteRepository.findByUserIdAndCompanyId(findUser.getId(), form.getCompanyId());
                if (findFavoriteId == null){
                    favoriteRepository.save(new Favorite(findUser, findCompany));
                    return new FavoriteModifyDto(201, HttpStatus.ACCEPTED, "Favorite add");
                }else{
                    favoriteRepository.deleteById(findFavoriteId);
                    return new FavoriteModifyDto(202, HttpStatus.ACCEPTED, "Favorite delete");
                }
            }
        }
    }

    public ResponseDto getUserHas(String httpSession){
        Optional<UserInfo> findUserOpt = userRepository.findByHttpSession(httpSession);
        if (findUserOpt.isEmpty()){
            return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Invalid Session");
        }else {
            UserInfo findUser = findUserOpt.get();
            if (security.isSessionValidDate(findUser.getSessionDate()) == false) {
                return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Session Has Expired");
            }else{
                findUser.updateSessionDate();
                List<SingleHasDto> hasInfo = hasRepository.findHasInfoByUserId(findUser.getId());
                return new HasDto(202, HttpStatus.ACCEPTED, hasInfo);
            }
        }
    }

    public ResponseDto getTradeInfo(String httpSession, Long companyId){
        Optional<UserInfo> findUserOpt = userRepository.findByHttpSession(httpSession);
        if (findUserOpt.isEmpty()){
            return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Invalid Session");
        }else {
            UserInfo findUser = findUserOpt.get();
            if (security.isSessionValidDate(findUser.getSessionDate()) == false) {
                return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Session Has Expired");
            }else{
                findUser.updateSessionDate();
                String findUserId = findUser.getId();
                int stockPrice = companyRepository.findById(companyId).get().getStockPrice();

                Integer maxSellQuantity = hasRepository.findQuantityByUserIdAndCompanyId(findUserId, companyId);
                int maxBuyPrice = userRepository.findById(findUserId).get().getMoney();
                if (maxSellQuantity == null){
                    maxSellQuantity = 0;
                }
                return new TradeInfoDto(202, HttpStatus.ACCEPTED, maxSellQuantity, maxBuyPrice, stockPrice);
            }
        }
    }

    @Transactional
    public ResponseDto tradeSell(TradeForm form){
        Optional<UserInfo> findUserOpt = userRepository.findByHttpSession(form.getUserSession());
        if (findUserOpt.isEmpty()){
            return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Invalid Session");
        }else {
            UserInfo findUser = findUserOpt.get();
            if (security.isSessionValidDate(findUser.getSessionDate()) == false) {
                return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Session Has Expired");
            }else{
                findUser.updateSessionDate();
                Has findHas = hasRepository.findByUserIdAndCompanyId(findUser.getId(), form.getCompanyId());
                if (findHas == null || findHas.getQuantity() < form.getQuantity()){
                    return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Not Enough User Having Quantity");
                }else{
                    findUser.addMoney(form.getPrice() * form.getQuantity());
                    if (findHas.getQuantity() == form.getQuantity()){
                        hasRepository.delete(findHas);
                    }else{
                        findHas.subtractQuantity(form.getQuantity());
                    }
                    return new SuccessDto(202, HttpStatus.ACCEPTED, "Sell Trade Complete");
                }
            }
        }
    }

    @Transactional
    public ResponseDto tradeBuy(TradeForm form){
        Optional<UserInfo> findUserOpt = userRepository.findByHttpSession(form.getUserSession());
        if (findUserOpt.isEmpty()){
            return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Invalid Session");
        }else {
            UserInfo findUser = findUserOpt.get();
            if (security.isSessionValidDate(findUser.getSessionDate()) == false) {
                return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Session Has Expired");
            }else{
                findUser.updateSessionDate();
                if (findUser.getMoney() < form.getPrice() * form.getQuantity()){
                    return new ErrorDto(400, HttpStatus.BAD_REQUEST, "Not Enough User Available Money");
                }else{
                    findUser.subtractMoney(form.getPrice() * form.getQuantity());

                    Has findHas = hasRepository.findByUserIdAndCompanyId(findUser.getId(), form.getCompanyId());
                    if (findHas == null){
                        hasRepository.save(new Has(form.getPrice(), form.getQuantity(), findUser, companyRepository.findById(form.getCompanyId()).get()));
                    }else{
                        findHas.changePriceAndQantity((form.getPrice() * form.getQuantity() + findHas.getPrice() * findHas.getQuantity()) / (form.getQuantity() + findHas.getQuantity())
                                , form.getQuantity() + findHas.getQuantity());
                    }
                    return new SuccessDto(202, HttpStatus.ACCEPTED, "Buy Trade Complete");
                }
            }
        }
    }
}
