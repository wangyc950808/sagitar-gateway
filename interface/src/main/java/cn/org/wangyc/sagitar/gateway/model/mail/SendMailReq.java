package cn.org.wangyc.sagitar.gateway.model.mail;

import cn.org.wangyc.sagitar.gateway.model.BaseReq;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author Wangyc
 */
@Getter
@Setter
public class SendMailReq extends BaseReq {

    private static final long serialVersionUID = 6852414239114832785L;

    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件收件人
     */
    private String[] receivers;
    /**
     * 邮件抄送人
     */
    private String[] ccAddress;
    /**
     * 邮件暗送人
     */
    private String[] bccAddress;
    /**
     * 邮件回复人
     */
    private String replyTo;
    /**
     * 邮件内容是否为html
     */
    private boolean html;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 模版ID
     */
    private String templateCode;
    /**
     * 模版替代参数
     */
    private Map<String,String> params;

}
