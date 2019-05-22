package cn.dcr.stock.market.domain;

import cn.dcr.stock.market.util.MyTime;

public class StockMarketCurrentEntity extends BlockMarketCurrentEntity {
    private Float buyPrice1;
    private Long buyAoumt1;
    private Float buyPrice2;
    private Long buyAoumt2;
    private Float buyPrice3;
    private Long buyAoumt3;
    private Float buyPrice4;
    private Long buyAoumt4;
    private Float buyPrice5;
    private Long buyAoumt5;

    private Float sellPrice1;
    private Long sellAoumt1;
    private Float sellPrice2;
    private Long sellAoumt2;
    private Float sellPrice3;
    private Long sellAoumt3;
    private Float sellPrice4;
    private Long sellAoumt4;
    private Float sellPrice5;
    private Long sellAoumt5;


    public StockMarketCurrentEntity(String strSina) {
        String [] strList = strSina.split(",");
        setName(strList[0]);
        setOpenPrice(MyTime.convertEastMoneyFloat(strList[1]));
        setLastPrice(MyTime.convertEastMoneyFloat(strList[2]));
        setCurrentPrice(MyTime.convertEastMoneyFloat(strList[3]));
        setHighPrice(MyTime.convertEastMoneyFloat(strList[4]));
        setLowPrice(MyTime.convertEastMoneyFloat(strList[5]));
        setVolumn(Long.parseLong(strList[8]));
        setAmount(MyTime.convertEastMoneyFloat(strList[9]));
        setIncrease(getCurrentPrice() - getLastPrice());
        setIncreasePercent(getIncrease()/getLastPrice()*100);
        setTurnOver(0.0F);

        this.buyAoumt1 = Long.parseLong(strList[10]);
        this.buyPrice1 = MyTime.convertEastMoneyFloat(strList[11]);
        this.buyAoumt2 = Long.parseLong(strList[12]);
        this.buyPrice2 = MyTime.convertEastMoneyFloat(strList[13]);
        this.buyAoumt3 = Long.parseLong(strList[14]);
        this.buyPrice3 = MyTime.convertEastMoneyFloat(strList[15]);
        this.buyAoumt4 = Long.parseLong(strList[16]);
        this.buyPrice4 = MyTime.convertEastMoneyFloat(strList[17]);
        this.buyAoumt5 = Long.parseLong(strList[18]);
        this.buyPrice5 = MyTime.convertEastMoneyFloat(strList[19]);

        this.sellAoumt1 = Long.parseLong(strList[20]);
        this.sellPrice1 = MyTime.convertEastMoneyFloat(strList[21]);
        this.sellAoumt2 = Long.parseLong(strList[22]);
        this.sellPrice2 = MyTime.convertEastMoneyFloat(strList[23]);
        this.sellAoumt3 = Long.parseLong(strList[24]);
        this.sellPrice3 = MyTime.convertEastMoneyFloat(strList[25]);
        this.sellAoumt4 = Long.parseLong(strList[26]);
        this.sellPrice4 = MyTime.convertEastMoneyFloat(strList[27]);
        this.sellAoumt5 = Long.parseLong(strList[28]);
        this.sellPrice5 = MyTime.convertEastMoneyFloat(strList[29]);
    }

    public Float getBuyPrice1() {
        return buyPrice1;
    }

    public void setBuyPrice1(Float buyPrice1) {
        this.buyPrice1 = buyPrice1;
    }

    public Long getBuyAoumt1() {
        return buyAoumt1;
    }

    public void setBuyAoumt1(Long buyAoumt1) {
        this.buyAoumt1 = buyAoumt1;
    }

    public Float getBuyPrice2() {
        return buyPrice2;
    }

    public void setBuyPrice2(Float buyPrice2) {
        this.buyPrice2 = buyPrice2;
    }

    public Long getBuyAoumt2() {
        return buyAoumt2;
    }

    public void setBuyAoumt2(Long buyAoumt2) {
        this.buyAoumt2 = buyAoumt2;
    }

    public Float getBuyPrice3() {
        return buyPrice3;
    }

    public void setBuyPrice3(Float buyPrice3) {
        this.buyPrice3 = buyPrice3;
    }

    public Long getBuyAoumt3() {
        return buyAoumt3;
    }

    public void setBuyAoumt3(Long buyAoumt3) {
        this.buyAoumt3 = buyAoumt3;
    }

    public Float getBuyPrice4() {
        return buyPrice4;
    }

    public void setBuyPrice4(Float buyPrice4) {
        this.buyPrice4 = buyPrice4;
    }

    public Long getBuyAoumt4() {
        return buyAoumt4;
    }

    public void setBuyAoumt4(Long buyAoumt4) {
        this.buyAoumt4 = buyAoumt4;
    }

    public Float getBuyPrice5() {
        return buyPrice5;
    }

    public void setBuyPrice5(Float buyPrice5) {
        this.buyPrice5 = buyPrice5;
    }

    public Long getBuyAoumt5() {
        return buyAoumt5;
    }

    public void setBuyAoumt5(Long buyAoumt5) {
        this.buyAoumt5 = buyAoumt5;
    }

    public Float getSellPrice1() {
        return sellPrice1;
    }

    public void setSellPrice1(Float sellPrice1) {
        this.sellPrice1 = sellPrice1;
    }

    public Long getSellAoumt1() {
        return sellAoumt1;
    }

    public void setSellAoumt1(Long sellAoumt1) {
        this.sellAoumt1 = sellAoumt1;
    }

    public Float getSellPrice2() {
        return sellPrice2;
    }

    public void setSellPrice2(Float sellPrice2) {
        this.sellPrice2 = sellPrice2;
    }

    public Long getSellAoumt2() {
        return sellAoumt2;
    }

    public void setSellAoumt2(Long sellAoumt2) {
        this.sellAoumt2 = sellAoumt2;
    }

    public Float getSellPrice3() {
        return sellPrice3;
    }

    public void setSellPrice3(Float sellPrice3) {
        this.sellPrice3 = sellPrice3;
    }

    public Long getSellAoumt3() {
        return sellAoumt3;
    }

    public void setSellAoumt3(Long sellAoumt3) {
        this.sellAoumt3 = sellAoumt3;
    }

    public Float getSellPrice4() {
        return sellPrice4;
    }

    public void setSellPrice4(Float sellPrice4) {
        this.sellPrice4 = sellPrice4;
    }

    public Long getSellAoumt4() {
        return sellAoumt4;
    }

    public void setSellAoumt4(Long sellAoumt4) {
        this.sellAoumt4 = sellAoumt4;
    }

    public Float getSellPrice5() {
        return sellPrice5;
    }

    public void setSellPrice5(Float sellPrice5) {
        this.sellPrice5 = sellPrice5;
    }

    public Long getSellAoumt5() {
        return sellAoumt5;
    }

    public void setSellAoumt5(Long sellAoumt5) {
        this.sellAoumt5 = sellAoumt5;
    }
}
