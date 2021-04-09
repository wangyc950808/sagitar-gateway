package cn.org.wangyc.sagitar.gateway.provider.test;

import cn.org.wangyc.sagitar.common.util.SagJsonUtils;
import cn.org.wangyc.sagitar.gateway.model.mail.SendMailReq;
import cn.org.wangyc.sagitar.gateway.model.mail.SendMailResp;
import cn.org.wangyc.sagitar.gateway.provider.service.IWeChatSubscribeService;
import cn.org.wangyc.sagitar.gateway.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wangyc
 */
@RestController
@RequestMapping("/test")
@RefreshScope
public class TestController {

    @Value("${test.field:HAHAHA}")
    private String testField;

    @Lazy
    @Autowired
    private IWeChatSubscribeService weChatSubscribeService;

    @Lazy
    @Autowired
    private IMailService mailService;

    @RequestMapping(value = "/testSendMail", method = RequestMethod.GET)
    public String testApi(String testParam) {

        SendMailReq req = new SendMailReq();
        req.setSubject(testParam);
        req.setReceivers(new String[]{"870381736@qq.com"});
//        req.setCcAddress(copy.getCcAddress());
//        req.setBccAddress(copy.getBccAddress());
//        req.setReplyTo(copy.getReplyTo());
        req.setHtml(true);
        req.setContent("<html>\n" +
                "\tHello, World!\n" +
                "</html>");
//        req.setTemplateCode(copy.getTemplateCode());
//        req.setParams(copy.getParams());

        SendMailResp resp = mailService.send(req);

        return String.format("param:%s\n testField:%s\n sendResult:%s", testParam, testField, SagJsonUtils.toJsonStr(resp));
    }

    @RequestMapping(value = "/testGetToken", method = RequestMethod.GET)
    public String testGetToken() {
        //43_jjixm8Kw8R-UhXbXmMz-y0dVxqjIWsrOxeld63kkeH3E-OVjTJa7jl9EncIg8l5bK-AMwlXR7euHnigXkQQzYycfNTyoECf20mA80AefIb3U46oFNe_xvxPde5peYEG1nw4SiidEoad4au7aKQFcAHABHW
        return weChatSubscribeService.getAccessToken();
    }

    @RequestMapping(value = "/testAuthWeChatServer", method = RequestMethod.GET)
    public String testAuthWeChatServer(String signature, Long timestamp, String echostr, String nonce) {
        System.out.printf("sign:%s; timestamp:%s; echostr:%s; nonce:%s;%n", signature, timestamp, echostr, nonce);
        return echostr;
    }
}
