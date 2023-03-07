package com.stocks.api.stocksapi.repository;

import com.stocks.api.stocksapi.dto.Stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.util.List;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "stock", "date" }) })
public interface StockRepository extends JpaRepository<Stock, Integer> {
    @Modifying
    @Query(value = "SELECT * FROM stock s where s.stock=:ticker and s.user_id =:userId", nativeQuery = true)
    List<Stock> fetchStockList(@Param("ticker") String ticker, @Param("userId") String userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM stock s WHERE s.stock=:ticker and s.user_id =:userId", nativeQuery = true)
    Integer deleteStocksByTicker(@Param("ticker") String ticker, @Param("userId") String userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM stock s where s.stock is NULL and s.user_id =:userId", nativeQuery = true)
    Integer deleteStocksByTickerNull(@Param("userId") String userId);
}
