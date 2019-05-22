package cn.dcr.stock.market.controller;

import cn.dcr.stock.market.domain.StockResponse;
import cn.dcr.stock.market.service.EastBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

//板块也接口
@RestController
@EnableAutoConfiguration
@RequestMapping("/api/index")
public class BlockMarketController {
    @Autowired
    private EastBlockService eastBlockService;

    //获取当前板块包含股票的今日行情
    @CrossOrigin
    @RequestMapping(method=RequestMethod.GET,value = "/block/stocks/{blockId}")
    public StockResponse getBlockStocksMarket(@PathVariable String blockId,
                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageNum) {
        StockResponse stockResponse = new StockResponse();
        try{
            stockResponse.setState(0);
            stockResponse.setData(eastBlockService.getBlockStocksMarket(blockId, pageSize, pageNum, stockResponse));

        }catch (Exception e){
            stockResponse.setState(-1);
            stockResponse.setErrorMessage(e.toString());
        }
        return stockResponse;
    }

    //获取当前板块具体行情
    @CrossOrigin
    @RequestMapping(method=RequestMethod.GET,value = "/block/market/{blockId}")
    public StockResponse getBlocksMarket(@PathVariable String blockId){
        StockResponse stockResponse = new StockResponse();
        try{
            stockResponse.setState(0);
            stockResponse.setData(eastBlockService.getBlockMarket(blockId));

        }catch (Exception e){
            stockResponse.setState(-1);
            stockResponse.setErrorMessage(e.toString());
        }
        return stockResponse;
    }
}
