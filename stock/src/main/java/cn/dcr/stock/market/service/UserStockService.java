package cn.dcr.stock.market.service;

import cn.dcr.stock.market.domain.SelectedStockMarketEntity;
import cn.dcr.stock.market.domain.UserStockAddRequest;
import cn.dcr.stock.market.domain.UserStockEnitity;
import cn.dcr.stock.market.repository.UserStockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserStockService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserStockRepository userStockRepository;

    private WebClient webClient = WebClient.builder()
            .baseUrl("http://hq.sinajs.cn")
            .defaultHeader(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)")
            .build();

    //增加
    public void addStock(UserStockAddRequest userStockAddRequest){
        UserStockEnitity userStockEnitity = new UserStockEnitity();
        userStockEnitity.setUserId(userStockAddRequest.getUserId());
        userStockEnitity.setStockName(userStockAddRequest.getStockName());
        userStockEnitity.setStockCode(userStockAddRequest.getStockCode());
        userStockEnitity.setStockSinaCode(userStockAddRequest.getStockSinaCode());
        userStockRepository.save(userStockEnitity);
    }

    //删除
    public void delStock(UserStockAddRequest userStockAddRequest){
        UserStockEnitity userStockEnitity = userStockRepository.getByUserIdAndAndStockCode(userStockAddRequest.getUserId(),
                userStockAddRequest.getStockCode());
        userStockRepository.delete(userStockEnitity);
    }

    //判断当前股票是否是自选
    public Boolean isSelectedStock(UserStockAddRequest userStockAddRequest){
        UserStockEnitity userStockEnitity = userStockRepository.getByUserIdAndAndStockCode(userStockAddRequest.getUserId(),
                userStockAddRequest.getStockCode());
        return (userStockEnitity != null);
    }

    //获取用户所有股票行情列表
    public List<SelectedStockMarketEntity> getSelectedStocks(String userId, Integer pageSize, Integer pageNum){
        //获取当前股票的列表
        List<UserStockEnitity> userStockEnitities = userStockRepository.getPageStock(userId, (pageNum-1)*pageSize, pageSize);

        StringBuilder url = new StringBuilder("/list=");
        for (UserStockEnitity userStockEnitity : userStockEnitities){
            url.append("s_").append(userStockEnitity.getStockSinaCode()).append(",");
        }
        logger.info(url.toString());
        String responseSpec = webClient.method(HttpMethod.GET)
                .uri(url.toString())
                .retrieve().bodyToMono(String.class).block();
        List<String> strList = getSinaValidString(responseSpec);
        List<SelectedStockMarketEntity> selectedStockMarketEntities = new ArrayList<>();

        for (int i = 0; i < strList.size(); i++){
           selectedStockMarketEntities.add(new SelectedStockMarketEntity(userStockEnitities.get(i), strList.get(i)));
        }

        return selectedStockMarketEntities;
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
