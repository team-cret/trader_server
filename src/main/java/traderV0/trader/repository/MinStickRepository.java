package traderV0.trader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import traderV0.trader.dto.MinStickDto;
import traderV0.trader.entity.MinStick;
import java.util.List;
@Repository
public interface MinStickRepository extends JpaRepository<MinStick, Long> {
    @Query("SELECT new traderV0.trader.dto.MinStickDto(m.minStickDate, m.startPrice, m.endPrice, m.highPrice, m.lowPrice) " +
            "FROM MinStick m JOIN m.company c WHERE c.id = :companyId ORDER BY m.minStickDate")
    List<MinStickDto> findByCompanyId(@Param("companyId") Long companyId);
}
