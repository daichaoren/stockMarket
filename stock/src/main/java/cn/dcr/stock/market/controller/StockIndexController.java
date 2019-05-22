package cn.dcr.stock.market.controller;

import cn.dcr.stock.market.domain.StockResponse;
import cn.dcr.stock.market.service.EastBlockService;
import cn.dcr.stock.market.service.StockIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

//获取首页数据的controller
@RestController
@EnableAutoConfiguration
@RequestMapping("/api/index")
public class StockIndexController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StockIndexService stockIndexService;
    @Autowired
    private EastBlockService eastBlockService;
    //获取三大股指行情
    @CrossOrigin
    @RequestMapping(method=RequestMethod.GET,value = "/market/index")
    public StockResponse getStockIndexInfo(){
        StockResponse stockResponse = new StockResponse();
        try{
            stockResponse.setState(0);
            stockResponse.setData(stockIndexService.getStockIndexInfo());

        }catch (Exception e){
            stockResponse.setState(-1);
            stockResponse.setErrorMessage(e.toString());
        }
        return stockResponse;
    }

    //获取板块的前三个板块， 行业板块 C._BKHY， 概念板块 C._BKGN, 地域板块 C._BKDY
    @CrossOrigin
    @RequestMapping(method=RequestMethod.GET,value = "/block/top/{blockType}")
    public StockResponse getTopBlockIndex(@PathVariable String blockType,
                                          @RequestParam(required = false, defaultValue = "3") Integer pageSize,
                                          @RequestParam(required = false, defaultValue = "1") Integer pageNum){
        StockResponse stockResponse = new StockResponse();
        try{
            stockResponse.setState(0);
            //如果成功，将数据填入到errorMessage中
            stockResponse.setErrorMessage(blockType);
            stockResponse.setData(eastBlockService.getTopBlockMarket(blockType, pageSize, pageNum));

        }catch (Exception e){
            stockResponse.setState(-1);
            stockResponse.setErrorMessage(e.toString());
        }
        return stockResponse;
    }

    //获取股票排行榜 top 10, sortBy - 按照涨跌幅 ChangePercent 按照换手率 TurnoverRate
    // sortType -1 - 降序 1 - 升序
    @CrossOrigin
    @RequestMapping(method=RequestMethod.GET,value = "/stock/top/{sortBy}")
    public StockResponse getTopStockMarket(@PathVariable String sortBy,
                                           @RequestParam(required = false, defaultValue = "-1") Integer sortType,
                                           @RequestParam(required = false, defaultValue = "3") Integer pageSize,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageNum) {
        StockResponse stockResponse = new StockResponse();
        try{
            stockResponse.setState(0);
            stockResponse.setData(eastBlockService.getTopStockMarket(sortBy, sortType, pageSize, pageNum));

        }catch (Exception e){
            stockResponse.setState(-1);
            stockResponse.setErrorMessage(e.toString());
        }
        return stockResponse;
    }

}
