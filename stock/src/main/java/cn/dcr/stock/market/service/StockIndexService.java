package cn.dcr.stock.market.service;

import cn.dcr.stock.market.domain.IndexRealMarketEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockIndexService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private WebClient webClient = WebClient.builder()
            .baseUrl("http://hq.sinajs.cn")
            .defaultHeader(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)")
            .build();

    public List<IndexRealMarketEntity> getStockIndexInfo(){
        WebClient.ResponseSpec responseSpec = webClient.method(HttpMethod.GET)
                .uri("/list={code1},{code2},{code3}","s_sh000001", "s_sz399001", "s_sz399006")
                .retrieve();
        Mono<String> mono = responseSpec
                .onStatus(e -> e.is4xxClientError(),resp -> {
                    logger.error("error:{},msg:{}",resp.statusCode().value(),resp.statusCode().getReasonPhrase());
                    return Mono.error(new RuntimeException(resp.statusCode().value() + " : " + resp.statusCode().getReasonPhrase()));
                })
                .bodyToMono(String.class)
                .doOnError(WebClientResponseException.class, err -> {
                    logger.info("ERROR status:{},msg:{}",err.getRawStatusCode(),err.getResponseBodyAsString());
                    throw new RuntimeException(err.getMessage());
                })
                .onErrorReturn("fallback");
        List<String> strResultList = getSinaValidString(mono.block());
        List<IndexRealMarketEntity> indexRealMarketEntityList = new ArrayList<>();
        for (String strResult: strResultList) {
            indexRealMarketEntityList.add(new IndexRealMarketEntity(strResult));
        }
        return indexRealMarketEntityList;
    }


    //获取新浪返回中的有效值
    private List<String> getSinaValidString(String srcStr){
        //切分
        String[] strTmpList = srcStr.split("\n");
        List<String> strResultList = new ArrayList<>();
        for (String str:  strTmpList) {
            int firstIndex = str.indexOf("\"");
            int lastIndex = str.lastIndexOf("\"");
            //返回空串
            if (firstIndex == -1 || lastIndex == -1 || firstIndex == lastIndex){
                continue;
            }
            strResultList.add(str.substring(firstIndex+1, lastIndex));
        }

        return strResultList;
    }
}

