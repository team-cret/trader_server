package traderV0.trader.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import traderV0.trader.constant.UserState;
import traderV0.trader.dto.SingleRankDto;
import traderV0.trader.entity.UserInfo;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, String> {
    @Query("SELECT u FROM UserInfo u WHERE u.state = :state AND u.sessionDate BETWEEN :startDate AND :endDate Order By u.sessionDate")
    List<UserInfo> findByValidWaitingUserOrderByDate(@Param("state") UserState state,
                                                     @Param("startDate") LocalDateTime startDate,
                                                     @Param("endDate") LocalDateTime endDate,
                                                     Pageable pageable);

    @Query("SELECT u FROM UserInfo u WHERE u.state = :state")
    List<UserInfo> findByState(UserState state);

    List<UserInfo> findByStateOrderByMoneyDesc(UserState state);

    Optional<UserInfo> findByHttpSession(String httpSession);

    Optional<UserInfo> findByWebSession(String webSession);

    @Query("SELECT new traderV0.trader.dto.SingleRankDto(u.name, u.money) FROM UserInfo u WHERE u.state = :state ORDER BY u.money")
    List<SingleRankDto> findRankByState(UserState state);
}
