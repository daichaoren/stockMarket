package cn.dcr.stock.market.controller;

import cn.dcr.stock.market.domain.StockResponse;
import cn.dcr.stock.market.service.SearchSuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

//搜索股票建议接口
@RestController
@EnableAutoConfiguration
@RequestMapping("/api/index")
public class SearchSuggestController {
    @Autowired
    private SearchSuggestService searchSuggestService;

    //获取当前板块包含股票的今日行情
    @CrossOrigin
    @RequestMapping(method=RequestMethod.GET,value = "/search/stocks/{searchText}")
    public StockResponse getBlockStocksMarket(@PathVariable String searchText) {
        StockResponse stockResponse = new StockResponse();
        try{
            stockResponse.setState(0);
            stockResponse.setData(searchSuggestService.getSuggestStocks(searchText));

        }catch (Exception e){
            stockResponse.setState(-1);
            stockResponse.setErrorMessage(e.toString());
        }
        return stockResponse;
    }
}
