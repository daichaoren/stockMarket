package cn.dcr.stock.market.domain;

import java.text.DecimalFormat;

public class SelectedStockMarketEntity {
    private String name;
    private String code;
    private String sinaCode;
    private Float price;
    private Float increase;
    private Float increasePercent;

    public SelectedStockMarketEntity(UserStockEnitity userStockEnitity, String strSina){
        String [] strList = strSina.split(",");
        //保留两位2小数
        DecimalFormat decimalFormat=new DecimalFormat(".00");
        this.name = strList[0];
        this.price = Float.parseFloat(decimalFormat.format(Float.parseFloat(strList[1])));
        this.increase = Float.parseFloat(decimalFormat.format(Float.parseFloat(strList[2])));
        this.increasePercent = Float.parseFloat(decimalFormat.format(Float.parseFloat(strList[3])));
        this.code = userStockEnitity.getStockCode();
        this.sinaCode = userStockEnitity.getStockSinaCode();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Float getIncrease() {
        return increase;
    }

    public void setIncrease(Float increase) {
        this.increase = increase;
    }

    public Float getIncreasePercent() {
        return increasePercent;
    }

    public void setIncreasePercent(Float increasePercent) {
        this.increasePercent = increasePercent;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
