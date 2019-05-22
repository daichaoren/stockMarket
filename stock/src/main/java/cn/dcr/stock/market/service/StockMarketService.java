package cn.dcr.stock.market.service;

import cn.dcr.stock.market.domain.StockMarketCurrentEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class StockMarketService {

    private WebClient webClient = org.springframework.web.reactive.function.client.WebClient.builder()
            .baseUrl("http://hq.sinajs.cn")
            .defaultHeader(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)")
            .build();

    public StockMarketCurrentEntity getStockCurrentMarket(String strSinaCode){
        String responseString = webClient.method(HttpMethod.GET)
                .uri("/list={code}", strSinaCode)
                .retrieve().bodyToMono(String.class).block();
        int firstIndex = responseString.indexOf("\"");
        int lastIndex = responseString.lastIndexOf("\"");
        //返回空串
        if (firstIndex == -1 || lastIndex == -1 || firstIndex == lastIndex){
            return null;
        }
        String strValid = responseString.substring(firstIndex+1, lastIndex);
        StockMarketCurrentEntity stockMarketCurrentEntity = new StockMarketCurrentEntity(strValid);
        return stockMarketCurrentEntity;
    }
}
