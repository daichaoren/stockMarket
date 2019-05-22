package cn.dcr.stock.market.util;

import java.text.DecimalFormat;

public class MyTime {
    //获取时间戳
    public static String getNowTimeStamp() {
        long time = System.currentTimeMillis();
        return String.valueOf(time);
    }

    public static Float convertEastMoneyFloat(String strFloat){
        try {
            //保留两位2小数
            DecimalFormat decimalFormat = new DecimalFormat(".00");
            return Float.parseFloat(decimalFormat.format(Float.parseFloat(strFloat)));
        }catch (Exception e){
            return 0.0F;
        }

    }
}
