package cn.dcr.stock.market.controller;

import cn.dcr.stock.market.domain.StockResponse;
import cn.dcr.stock.market.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

//登录接口
@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
public class LoginController {
    @Autowired
    LoginService loginService;

    //微信登录接口
    @CrossOrigin
    @RequestMapping(method=RequestMethod.GET,value = "/onlogin")
    public StockResponse getBlockStocksMarket(@RequestParam String code) {
        StockResponse stockResponse = new StockResponse();
        try{
            stockResponse.setState(0);
            stockResponse.setData(loginService.onLogin(code));
        }catch (Exception e){
            stockResponse.setState(-1);
            stockResponse.setErrorMessage(e.toString());
        }
        return stockResponse;
    }
}
