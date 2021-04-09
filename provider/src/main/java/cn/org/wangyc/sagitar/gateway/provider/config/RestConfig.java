package cn.org.wangyc.sagitar.gateway.provider.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Wangyc
 */
@Configuration
@RefreshScope
@Setter
@Getter
public class RestConfig {

    @Value("${rest.read.timeout:5000}")
    private int readTimeOut;

    @Value("${rest.connect.timeout:5000}")
    private int connectTimeOut;

    @Bean
    public RestTemplate restTemplate(@Autowired @Qualifier("gatewayClientHttpRequestFactory") ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean("gatewayClientHttpRequestFactory")
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(readTimeOut);
        factory.setConnectTimeout(connectTimeOut);
        return factory;
    }


}
