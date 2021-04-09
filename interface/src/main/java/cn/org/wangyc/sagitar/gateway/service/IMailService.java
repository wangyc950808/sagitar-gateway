package cn.org.wangyc.sagitar.gateway.service;

import cn.org.wangyc.sagitar.gateway.model.mail.SendMailReq;
import cn.org.wangyc.sagitar.gateway.model.mail.SendMailResp;

/**
 * @author Wangyc
 * @date 2021-4-9 14:25:14
 */
public interface IMailService {

    /**
     * 邮件发送
     *
     * @param req req
     * @return result
     */
    SendMailResp send(SendMailReq req);
}
