package cn.dcr.stock.market.controller;

import cn.dcr.stock.market.domain.StockResponse;
import cn.dcr.stock.market.service.StockMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

//股票行情数据接口
@RestController
@EnableAutoConfiguration
@RequestMapping("/api/stock")
public class StockMarketController {

    @Autowired
    private StockMarketService stockMarketService;

    @CrossOrigin
    @RequestMapping(method=RequestMethod.GET,value = "/market/{sinaCode}")
    public StockResponse getStockIndexInfo(@PathVariable String sinaCode){
        StockResponse stockResponse = new StockResponse();
        try{
            stockResponse.setState(0);
            stockResponse.setData(stockMarketService.getStockCurrentMarket(sinaCode));

        }catch (Exception e){
            stockResponse.setState(-1);
            stockResponse.setErrorMessage(e.toString());
        }
        return stockResponse;
    }
}
