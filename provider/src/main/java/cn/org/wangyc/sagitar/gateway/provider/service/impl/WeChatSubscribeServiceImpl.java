package cn.org.wangyc.sagitar.gateway.provider.service.impl;

import cn.org.wangyc.sagitar.common.util.SagJsonUtils;
import cn.org.wangyc.sagitar.gateway.provider.service.IWeChatSubscribeService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Wangyc
 */
@Service
@Slf4j
public class WeChatSubscribeServiceImpl implements IWeChatSubscribeService {

    @Lazy
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getAccessToken() {

        String appId = "wx14734c5329ef74c0";
        String secret = "a6f23d12269ce5eb8c854dcd08cf96d2";

        String oriUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

        String url = String.format(oriUrl, appId, secret);

        ResponseEntity<String> restResp = restTemplate.getForEntity(url, String.class);

        return SagJsonUtils.toJsonStr(restResp);
    }

}
