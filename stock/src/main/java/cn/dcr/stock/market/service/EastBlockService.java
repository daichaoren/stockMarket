package cn.dcr.stock.market.service;

import cn.dcr.stock.market.domain.*;
import cn.dcr.stock.market.util.MyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;


// 从东方财富获取板块类的服务
@Service
public class EastBlockService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private WebClient webClient = WebClient.builder()
            .baseUrl("http://nufm.dfcfw.com/")
            .defaultHeader(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)")
            .build();

    //获取板块类型中的排名前几位的
    public List<TopBlockMarketEntity> getTopBlockMarket(String blockType, Integer pageSize, Integer pageNum){
        String responseString = webClient.method(HttpMethod.GET)
                .uri("/EM_Finance2014NumericApplication/JS.aspx" +
                        "?cb=jQuery1124040776850467092984_{timeStamp1}" +
                        "&type=CT" +
                        "&token=4f1862fc3b5e77c150a2b985b12db0fd&sty=FPGBKI" +
                        "&js=(%7Bdata%3A%5B(x)%5D%2CrecordsFiltered%3A(tot)%7D)" +
                        "&cmd={blockType}" +
                        "&st=(ChangePercent)&sr=-1&p={pageNum}&ps={pageSize}&_={timeStamp2}", MyTime.getNowTimeStamp(),
                        blockType, pageNum.toString(), pageSize.toString(),

                        MyTime.getNowTimeStamp())
                .retrieve().bodyToMono(String.class).block();
        List<TopBlockMarketEntity> topBlockMarketEntityList = new ArrayList<>();
        String [] strList = getValidStringList(responseString);
        if (strList == null) return null;
        //我只取前三个
        for (String strTemp : strList){
            topBlockMarketEntityList.add(new TopBlockMarketEntity(strTemp));
        }
        return topBlockMarketEntityList;

    }

    //获取股票排行榜前10
    public List<TopStockMarketEntity> getTopStockMarket(String sortBy, Integer sortType,
                                                        Integer pageSize,  Integer pageNum){
        String responseString = webClient.method(HttpMethod.GET)
                .uri("/EM_Finance2014NumericApplication/JS.aspx" +
                                "?cb=jQuery1124040776850467092984_{timeStamp1}" +
                                "&type=CT" +
                                "&token=4f1862fc3b5e77c150a2b985b12db0fd&sty=FPGBKI" +
                                "&js=(%7Bdata%3A%5B(x)%5D%2CrecordsFiltered%3A(tot)%7D)" +
                                "&cmd=C._A" +
                                "&st=({sortBy})&sr={sortType}&p={pageNum}&ps={pageSize}&_={timeStamp2}", MyTime.getNowTimeStamp(),
                        sortBy, sortType.toString(), pageNum.toString(), pageSize.toString(),
                        MyTime.getNowTimeStamp())
                .retrieve().bodyToMono(String.class).block();
        List<TopStockMarketEntity> topStockMarketEntityList = new ArrayList<>();
        String [] strList = getValidStringList(responseString);
        for (String strTemp : strList){
            topStockMarketEntityList.add(new TopStockMarketEntity(sortBy, strTemp));
        }
        return topStockMarketEntityList;
    }

    //获取板块下的所有股票情况
    public List<BlockStockMarketEntity> getBlockStocksMarket(String blockId, Integer pageSize,
                                                             Integer pageNum, StockResponse stockResponse){
        String responseString = webClient.method(HttpMethod.GET)
                .uri("/EM_Finance2014NumericApplication/JS.aspx" +
                        "?cmd=C.{blockId}1&type=ct&st=(ChangePercent)" +
                        "&sr=-1&p={pageNum}&ps={pageSize}&token=894050c76af8597a853f5b408b759f5d&sty=DCFFITA" +
                        "&js=pages:(pc),data:[(x)]&rt=51936016", blockId, pageNum.toString(), pageSize.toString())
                .retrieve().bodyToMono(String.class).block();
        List<BlockStockMarketEntity> blockStockMarketEntityList = new ArrayList<>();
        stockResponse.setTotalPage(getPageTotalNum(responseString));
        String [] strList = getValidStringListBlock(responseString);
        for (String strTemp : strList){
            blockStockMarketEntityList.add(new BlockStockMarketEntity(strTemp));
        }
        return blockStockMarketEntityList;
    }
    private Integer getPageTotalNum(String srcStr){
        //解析总页数
        Integer iFirstIndex = srcStr.indexOf(',');
        Integer iSecondIndex = srcStr.indexOf(":");
        return Integer.parseInt(srcStr.substring(iSecondIndex+1, iFirstIndex));
    }

    //获取当前板块的具体行情
    public BlockMarketCurrentEntity getBlockMarket(String blockId){
        String responseString = webClient.method(HttpMethod.GET)
                .uri("/EM_Finance2014NumericApplication/JS.aspx" +
                        "?type=CT&cmd={blockID}1" +
                        "&sty=FDPBPFB&st=z&sr=&p=&ps=" +
                        "&cb=jQuery172015076467669787008_{timeStamp1}&js=([[(x)]])" +
                        "&token=7bc05d0d4c3c22ef9fca8c2a912d779c" +
                        "&_{timeStamp2}", blockId, MyTime.getNowTimeStamp(), MyTime.getNowTimeStamp())
                .retrieve().bodyToMono(String.class).block();

        return new BlockMarketCurrentEntity(getValidStringListBlockMarket(responseString));

    }

    private String getValidStringListBlockMarket(String srcStr){
        Integer iStartIndex = srcStr.indexOf("[[\"") ,iEndIndex = srcStr.indexOf("\"]]");

        if (iStartIndex == -1 || iEndIndex == -1 || iStartIndex.equals(iEndIndex))
            return null;
        return srcStr.substring(iStartIndex+5, iEndIndex);

    }

    private String[] getValidStringListBlock(String srcStr){

        Integer iStartIndex = srcStr.indexOf(":[\"") ,iEndIndex = srcStr.indexOf("\"]");

        if (iStartIndex == -1 || iEndIndex == -1 || iStartIndex.equals(iEndIndex))
            return null;
        String resultString = srcStr.substring(iStartIndex+4, iEndIndex);

        return resultString.split("\",\"");
    }
    private String[] getValidStringList(String srcStr){
        Integer iStartIndex = srcStr.indexOf("%5B\"") ,iEndIndex = srcStr.indexOf("\"%5D%2C");

        if (iStartIndex == -1 || iEndIndex == -1 || iStartIndex.equals(iEndIndex))
            return null;
        String resultString = srcStr.substring(iStartIndex+3, iEndIndex);

        return resultString.split("\",\"");
    }
}
