package cn.dcr.stock.market.domain;
import cn.dcr.stock.market.util.MyTime;


public class BlockMarketCurrentEntity {
    private String code;
    private String name;
    private Float increase;
    private Float increasePercent;
    private Float openPrice;
    private Float highPrice;
    private Float lowPrice;
    private Float lastPrice; // 昨收
    private Float currentPrice;
    private Float turnOver; //换手率
    private Float volumnRate; //量比
    private Long volumn; //成交量
    private Float amount; //成交额
    private Long outVol; //外盘
    private Long innerVol; //内盘
    private Long floatCaptal; //流通市值
    private Long floatEquity; //流通股本

    public BlockMarketCurrentEntity(){

    }

    public BlockMarketCurrentEntity(String strEastMoney){
        String [] strList = strEastMoney.split(",");

        this.code = strList[0];
        this.name = strList[1];
        this.currentPrice = MyTime.convertEastMoneyFloat(strList[3]);
        this.increasePercent = MyTime.convertEastMoneyFloat(strList[4]);
        this.increase = MyTime.convertEastMoneyFloat(strList[5]);
        this.openPrice = MyTime.convertEastMoneyFloat(strList[6]);
        this.highPrice = MyTime.convertEastMoneyFloat(strList[7]);
        this.volumn = Long.parseLong(strList[8]);
        this.innerVol = Long.parseLong(strList[10]);
        this.lastPrice = MyTime.convertEastMoneyFloat(strList[12]);
        this.lowPrice = MyTime.convertEastMoneyFloat(strList[13]);
        this.amount = MyTime.convertEastMoneyFloat(strList[14]);
        this.outVol = Long.parseLong(strList[16]);
        this.volumnRate = MyTime.convertEastMoneyFloat(strList[17]);
        this.floatCaptal = Long.parseLong(strList[18]);
        this.floatEquity = Long.parseLong(strList[20]);
        this.turnOver = MyTime.convertEastMoneyFloat(strList[22]);
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

    public Float getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Float openPrice) {
        this.openPrice = openPrice;
    }

    public Float getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Float highPrice) {
        this.highPrice = highPrice;
    }

    public Float getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Float lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Float getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(Float lastPrice) {
        this.lastPrice = lastPrice;
    }

    public Float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Float getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(Float turnOver) {
        this.turnOver = turnOver;
    }

    public Float getVolumnRate() {
        return volumnRate;
    }

    public void setVolumnRate(Float volumnRate) {
        this.volumnRate = volumnRate;
    }

    public Long getVolumn() {
        return volumn;
    }

    public void setVolumn(Long volumn) {
        this.volumn = volumn;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Long getOutVol() {
        return outVol;
    }

    public void setOutVol(Long outVol) {
        this.outVol = outVol;
    }

    public Long getInnerVol() {
        return innerVol;
    }

    public void setInnerVol(Long innerVol) {
        this.innerVol = innerVol;
    }

    public Long getFloatCaptal() {
        return floatCaptal;
    }

    public void setFloatCaptal(Long floatCaptal) {
        this.floatCaptal = floatCaptal;
    }

    public Long getFloatEquity() {
        return floatEquity;
    }

    public void setFloatEquity(Long floatEquity) {
        this.floatEquity = floatEquity;
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
}
