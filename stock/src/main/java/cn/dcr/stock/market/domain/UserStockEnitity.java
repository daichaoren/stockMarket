package cn.dcr.stock.market.domain;

import javax.persistence.*;

@Entity
@Table(name = "user_stock")
public class UserStockEnitity {
    @Id
    @GeneratedValue
    private Integer id;
    private String userId;
    private String stockName;
    private String stockCode;
    private String stockSinaCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockSinaCode() {
        return stockSinaCode;
    }

    public void setStockSinaCode(String stockSinaCode) {
        this.stockSinaCode = stockSinaCode;
    }
}

