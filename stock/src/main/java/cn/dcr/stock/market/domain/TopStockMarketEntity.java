package cn.dcr.stock.market.domain;

import cn.dcr.stock.market.util.MyTime;

import java.text.DecimalFormat;

//排行榜股票
public class TopStockMarketEntity {
    private String code;
    private String name;
    private Float price;
    private Float result;
    private Float increase;
    private Float increasePercent;
    private Float turnOverPercent;

    public TopStockMarketEntity(String sortBy, String strEastMoney){
        String [] strList = strEastMoney.split(",");
        //保留两位2小数
        DecimalFormat decimalFormat=new DecimalFormat(".00");
        this.code = strList[1];
        this.name = strList[2];
        this.increasePercent = MyTime.convertEastMoneyFloat(strList[3]);
        this.price = MyTime.convertEastMoneyFloat(strList[18]);
        this.increase = MyTime.convertEastMoneyFloat(strList[19]);
        this.turnOverPercent = MyTime.convertEastMoneyFloat(strList[5]);
        //按照涨跌幅
        if (sortBy.equals("ChangePercent")){
            this.result = this.increasePercent;
        }
        //换手率
        else if(sortBy.equals("TurnoverRate")){
            this.result = this.turnOverPercent;
        }
        else {
            this.result = 0.00F;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getResult() {
        return result;
    }

    public void setResult(Float result) {
        this.result = result;
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
        this.increase = increasePercent;
    }

    public Float getTurnOverPercent() {
        return turnOverPercent;
    }

    public void setTurnOverPercent(Float turnOverPercent) {
        this.turnOverPercent = turnOverPercent;
    }
}
