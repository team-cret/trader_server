package traderV0.trader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import traderV0.trader.entity.Company;
import traderV0.trader.entity.Favorite;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query("SELECT f.company FROM Favorite f JOIN f.userInfo u JOIN f.company c WHERE u.id = :userId")
    List<Company> findCompaniesByUserId(@Param("userId") String userId);

    @Query("SELECT f.id FROM Favorite f JOIN f.userInfo u JOIN f.company c WHERE u.id = :userId and c.id = :companyId")
    Long findByUserIdAndCompanyId(String userId, Long companyId);

}
