package cn.dcr.stock.market.domain;

public class UserStockAddRequest {
    private String userId;
    private String stockName;
    private String stockCode;
    private String stockSinaCode;

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
