package cn.dcr.stock.market.service;

import cn.dcr.stock.market.domain.SearchStockEnity;
import cn.dcr.stock.market.util.MyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

//搜索建议服务
@Service
public class SearchSuggestService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private WebClient webClient = WebClient.builder()
            .baseUrl("http://suggest3.sinajs.cn/suggest")
            .defaultHeader(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)")
            .build();

    public List<SearchStockEnity> getSuggestStocks(String strText){
        String responseString = webClient.method(HttpMethod.GET)
                .uri("/type=&key={strText}&name=suggestdata_{timeStamp}", strText, MyTime.getNowTimeStamp())
                .retrieve().bodyToMono(String.class).block();
        String [] validStringList = getValidContent(responseString);
        List<SearchStockEnity> searchStockEnities = new ArrayList<>();
        for (String strTemp : validStringList){
            SearchStockEnity searchStockEnity = new SearchStockEnity(strTemp);
            //只搜索沪深市场的股票
            if (searchStockEnity.getMarket() == 11)
                searchStockEnities.add(searchStockEnity);
        }
        return searchStockEnities;
    }

    private String [] getValidContent(String strSrc){
        Integer iStartIndex = strSrc.indexOf("=\"") ,iEndIndex = strSrc.indexOf("\";");

        if (iStartIndex == -1 || iEndIndex == -1 || iStartIndex.equals(iEndIndex))
            return null;
        String validString = strSrc.substring(iStartIndex+1, iEndIndex);
        return validString.split(";");
    }
}
