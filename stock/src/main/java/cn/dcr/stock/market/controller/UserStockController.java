package cn.dcr.stock.market.controller;

import cn.dcr.stock.market.domain.StockResponse;
import cn.dcr.stock.market.domain.UserStockAddRequest;
import cn.dcr.stock.market.service.UserStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

//用户自选股票接口
@RestController
@EnableAutoConfiguration
@RequestMapping("/api/user")
public class UserStockController {

    @Autowired
    UserStockService userStockService;

    //增加自选股票
    @CrossOrigin
    @RequestMapping(method=RequestMethod.POST, produces = "application/json;charset=UTF-8", value = "/add")
    public StockResponse addStock(@RequestBody UserStockAddRequest request){
        StockResponse stockResponse = new StockResponse();
        try{
            stockResponse.setState(0);
            userStockService.addStock(request);
        }catch (Exception e){
            stockResponse.setState(-1);
            stockResponse.setErrorMessage(e.toString());
        }
        return stockResponse;
    }

    //删除自选股票
    @CrossOrigin
    @RequestMapping(method=RequestMethod.POST, produces = "application/json;charset=UTF-8", value = "/del")
    public StockResponse delStock(@RequestBody UserStockAddRequest request){
        StockResponse stockResponse = new StockResponse();
        try{
            stockResponse.setState(0);
            userStockService.delStock(request);
        }catch (Exception e){
            stockResponse.setState(-1);
            stockResponse.setErrorMessage(e.toString());
        }
        return stockResponse;
    }

    //判断是否自选股票
    @CrossOrigin
    @RequestMapping(method=RequestMethod.POST, produces = "application/json;charset=UTF-8", value = "/is/selected")
    public StockResponse isSelectedStock(@RequestBody UserStockAddRequest request){
        StockResponse stockResponse = new StockResponse();
        try{
            stockResponse.setState(0);
            stockResponse.setData(userStockService.isSelectedStock(request));
        }catch (Exception e){
            stockResponse.setState(-1);
            stockResponse.setErrorMessage(e.toString());
        }
        return stockResponse;
    }


    //获取自选股票的行情
    @CrossOrigin
    @RequestMapping(method=RequestMethod.GET, produces = "application/json;charset=UTF-8", value = "/stocks")
    public StockResponse getSelectedStocks(@RequestParam String userId,
                                           @RequestParam(required = false, defaultValue = "1000") Integer pageSize,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageNum) {
        StockResponse stockResponse = new StockResponse();
        try{
            stockResponse.setState(0);
            stockResponse.setData(userStockService.getSelectedStocks(userId, pageSize, pageNum));
        }catch (Exception e){
            stockResponse.setState(-1);
            stockResponse.setErrorMessage(e.toString());
        }
        return stockResponse;
    }
}
