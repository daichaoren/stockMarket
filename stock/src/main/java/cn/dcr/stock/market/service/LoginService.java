package cn.dcr.stock.market.service;

import cn.dcr.stock.market.domain.WxLoginResponse;;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LoginService {
    private final String appId = "wx0f01bf5a4dcf0581";
    private final String appSecret = "9d39643c1e67e8b34ec216ca6371bf2a";
    private Logger logger = LoggerFactory.getLogger(getClass());

    private WebClient webClient = WebClient.create("https://api.weixin.qq.com/sns");


    public String onLogin(String code){
        String strResponse= webClient.get()
                .uri("/jscode2session?appid={appID}&secret={appSecret}" +
                                "&js_code={code}&grant_type=authorization_code",appId,
                        appSecret, code)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve().bodyToMono(String.class).block();
        WxLoginResponse wxLoginResponse = JSON.parseObject(strResponse, WxLoginResponse.class);

        logger.info(wxLoginResponse.getOpenid());
        return wxLoginResponse.getOpenid();
    }
}
