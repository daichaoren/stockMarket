package cn.dcr.stock.market.domain;

import java.text.DecimalFormat;

//指数实时价格
public class IndexRealMarketEntity {

    private String name;    //指数名称
    private Float price;    //当前点数
    private Float increase;     //增长值
    private Float increasePercent;  //增长百分比已经乘以100
    private Integer volume; //成交量 （手）
    private Integer amount; // 成交额 (万元)

    public IndexRealMarketEntity(String name, Float price, Float increase, Float increasePercent, Integer volume, Integer amount) {
        this.name = name;
        this.price = price;
        this.increase = increase;
        this.increasePercent = increasePercent;
        this.volume = volume;
        this.amount = amount;
    }

    public IndexRealMarketEntity(String strSina){
        String [] strList = strSina.split(",");
        //保留两位2小数
        DecimalFormat decimalFormat=new DecimalFormat(".00");
        this.name = strList[0];
        this.price = Float.parseFloat(decimalFormat.format(Float.parseFloat(strList[1])));
        this.increase = Float.parseFloat(decimalFormat.format(Float.parseFloat(strList[2])));
        this.increasePercent = Float.parseFloat(decimalFormat.format(Float.parseFloat(strList[3])));
        this.volume = Integer.parseInt(strList[4]);
        this.amount = Integer.parseInt(strList[5]);
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

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
