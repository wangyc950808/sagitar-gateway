package cn.org.wangyc.sagitar.gateway.provider.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wangyc
 * @date 2021-4-9 09:44:08
 */
@Configuration
@RefreshScope
@Getter
@Setter
public class MailConfig {

    @Value("${mail.sender.address:#{null}}")
    private String mailSenderAddress;


}
