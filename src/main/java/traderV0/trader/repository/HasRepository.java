package traderV0.trader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import traderV0.trader.dto.SingleHasDto;
import traderV0.trader.entity.Has;

import java.util.List;

@Repository
public interface HasRepository extends JpaRepository<Has, Long> {
    @Query("SELECT new traderV0.trader.dto.SingleHasDto(h.company, h.price, h.quantity) " +
            "FROM Has h JOIN h.userInfo u JOIN h.company c WHERE u.id = :userId")
    List<SingleHasDto> findHasInfoByUserId(@Param("userId") String userId);

    @Query("SELECT SUM(h.quantity) FROM Has h JOIN h.userInfo u JOIN h.company c WHERE u.id = :userId and c.id = :companyId")
    Integer findQuantityByUserIdAndCompanyId(String userId, Long companyId);

    @Query("SELECT h FROM Has h JOIN h.userInfo u JOIN h.company c WHERE u.id = :userId and c.id = :companyId")
    Has findByUserIdAndCompanyId(String userId, Long companyId);
}
