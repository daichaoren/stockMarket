package cn.dcr.stock.market.domain;

public class SearchStockEnity {
    private String code;
    private String sinaCode;
    private String name;
    private Integer market;

    public SearchStockEnity(String strSina){
        String [] strList = strSina.split(",");
        this.market = Integer.parseInt(strList[1]);
        this.code = strList[2];
        this.sinaCode = strList[3];
        this.name = strList[4];
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSinaCode() {
        return sinaCode;
    }

    public void setSinaCode(String sinaCode) {
        this.sinaCode = sinaCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMarket() {
        return market;
    }

    public void setMarket(Integer market) {
        this.market = market;
    }
}
