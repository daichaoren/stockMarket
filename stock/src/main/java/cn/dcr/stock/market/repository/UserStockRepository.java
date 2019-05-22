package cn.dcr.stock.market.repository;

import cn.dcr.stock.market.domain.UserStockEnitity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserStockRepository extends JpaRepository<UserStockEnitity,Long> {
    UserStockEnitity getByUserIdAndAndStockCode(String userId, String stockCode);

    @Query(value="SELECT * FROM market.user_stock where userId=?1 limit ?2, ?3" ,nativeQuery = true)
    //(pageNo-1)*pageSize
    List<UserStockEnitity> getPageStock(String userId, Integer pageSize, Integer pageNum);
}
