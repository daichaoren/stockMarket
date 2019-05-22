package cn.dcr.stock.market.domain;

import cn.dcr.stock.market.util.MyTime;

import java.text.DecimalFormat;

public class TopBlockMarketEntity {
    private String blockName;
    private String blockCode;
    private Float blockIncreasePercent;
    private String TopStockCode;
    private String TopStockName;
    private Float TopStockIncreasePercent;

    public TopBlockMarketEntity(String strEastMoney){
        String [] strList = strEastMoney.split(",");
        //保留两位2小数
        DecimalFormat decimalFormat=new DecimalFormat(".00");
        this.blockCode = strList[1];
        this.blockName = strList[2];
        this.blockIncreasePercent = MyTime.convertEastMoneyFloat(strList[3]);
        this.TopStockCode = strList[7];
        this.TopStockName = strList[9];
        this.TopStockIncreasePercent = MyTime.convertEastMoneyFloat(strList[11]);
    }
    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public Float getBlockIncreasePercent() {
        return blockIncreasePercent;
    }

    public void setBlockIncreasePercent(Float blockIncreasePercent) {
        this.blockIncreasePercent = blockIncreasePercent;
    }

    public String getTopStockCode() {
        return TopStockCode;
    }

    public void setTopStockCode(String topStockCode) {
        TopStockCode = topStockCode;
    }

    public String getTopStockName() {
        return TopStockName;
    }

    public void setTopStockName(String topStockName) {
        TopStockName = topStockName;
    }

    public Float getTopStockIncreasePercent() {
        return TopStockIncreasePercent;
    }

    public void setTopStockIncreasePercent(Float topStockIncreasePercent) {
        TopStockIncreasePercent = topStockIncreasePercent;
    }
}
