package cn.org.wangyc.sagitar.gateway.provider.dubbo;

import cn.org.wangyc.sagitar.gateway.model.mail.SendMailReq;
import cn.org.wangyc.sagitar.gateway.model.mail.SendMailResp;
import cn.org.wangyc.sagitar.gateway.provider.config.MailConfig;
import cn.org.wangyc.sagitar.gateway.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Wangyc
 * @date 2021-4-9 14:28:39
 */
@Service
@Slf4j
public class MailServiceImpl implements IMailService {

    @Lazy
    @Autowired
    private JavaMailSender javaMailSender;

    @Lazy
    @Autowired
    private MailConfig mailConfig;

    @Override
    public SendMailResp send(SendMailReq req) {
        SendMailResp result = new SendMailResp();
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setText(req.getContent());
            helper.setFrom(new InternetAddress(mailConfig.getMailSenderAddress(), "test", "UTF-8"));
            helper.setTo(req.getReceivers());
            helper.setReplyTo(req.getReplyTo());
            helper.setBcc(req.getBccAddress());
            helper.setCc(req.getCcAddress());
            helper.setSubject(req.getSubject());

            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            result.otherError(e);
            log.error("MailService send failed! errMsg:{};", e.getMessage(), e);
        }
        return result;
    }
}
