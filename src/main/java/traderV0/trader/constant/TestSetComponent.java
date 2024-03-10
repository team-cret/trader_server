package traderV0.trader.constant;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import traderV0.trader.entity.*;
import traderV0.trader.function.Security;
import traderV0.trader.repository.*;


@Component
@RequiredArgsConstructor
@Transactional
public class TestSetComponent {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final FavoriteRepository favoriteRepository;
    private final HasRepository hasRepository;
    private final MinStickRepository minStickRepository;
    private final Security security;


    @PostConstruct
    public void init(){

        UserInfo user1 = new UserInfo("1", security.loginHashing("1"), "test1", 200000, null);
        userRepository.save(user1);

        UserInfo user2 = new UserInfo("2", security.loginHashing("2"), "test2", 200000, null);
        userRepository.save(user2);

        /*
        UserInfo user2 = new UserInfo("test2", "test", "test2", 30000, UserState.WAITING);
        user2.setHttpSession("2");
        user2.updateSessionDate();
        userRepository.save(user2);

        UserInfo user3 = new UserInfo("test3", "test", "test3", 10000, UserState.WAITING);
        user3.setHttpSession("3");
        user3.updateSessionDate();
        userRepository.save(user3);



        UserInfo user4 = new UserInfo("test4", "test", "test4", 40000, UserState.WAITING);
        user4.setHttpSession("4");
        user4.updateSessionDate();
        userRepository.save(user4);

        UserInfo user5 = new UserInfo("test5", "test", "test5", 50000, UserState.WAITING);
        user5.setHttpSession("5");
        user5.updateSessionDate();
        userRepository.save(user5);

         */

        /*
        Company company1 = new Company("A전자", 10000, 20000);
        Company company2 = new Company("B철강", 12000, 11000);
        Company company3 = new Company("C화학", 13000, 32000);
        Company company4 = new Company("D제약", 20000, 12000);
        Company company5 = new Company("E뷰티", 50000, 40000);


        companyRepository.save(company1);
        companyRepository.save(company2);
        companyRepository.save(company3);
        companyRepository.save(company4);
        companyRepository.save(company5);

        favoriteRepository.save(new Favorite(user1, company1));
        favoriteRepository.save(new Favorite(user1, company3));
        favoriteRepository.save(new Favorite(user1, company4));
        favoriteRepository.save(new Favorite(user1, company5));
        favoriteRepository.save(new Favorite(user2, company1));
        favoriteRepository.save(new Favorite(user2, company2));
        favoriteRepository.save(new Favorite(user3, company4));
        favoriteRepository.save(new Favorite(user3, company5));


        hasRepository.save(new Has(company1.getStockPrice(), 40, user1, company1));
        hasRepository.save(new Has(company1.getStockPrice(), 20, user2, company1));
        hasRepository.save(new Has(company1.getStockPrice(), 10, user2, company2));
        hasRepository.save(new Has(company4.getStockPrice(), 15, user3, company4));
        hasRepository.save(new Has(company4.getStockPrice(), 30, user5, company4));
        hasRepository.save(new Has(company5.getStockPrice(), 200, user5, company5));

        minStickRepository.save(new MinStick(10000, 11000, 12000, 9000, LocalDateTime.now(),company1));
        minStickRepository.save(new MinStick(11000, 13000, 14000, 9500, LocalDateTime.now(),company1));
        minStickRepository.save(new MinStick(13000, 9000, 13000, 9000, LocalDateTime.now(),company1));
        minStickRepository.save(new MinStick(9000, 8500, 20000, 8000, LocalDateTime.now(),company1));
        minStickRepository.save(new MinStick(8500, 10000, 12000, 6000, LocalDateTime.now(),company1));

         */
    }
}
