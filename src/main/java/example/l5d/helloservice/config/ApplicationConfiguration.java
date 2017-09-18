package example.l5d.helloservice.config;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ApplicationConfiguration {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${service.name:service}")
    public String serviceName;

    @Value("${response.latency:0}")
    public int latency;

    @Value("${target:}")
    public String target;

    @Value("${pod_ip:0.0.0.0}")
    public String podIp;

    @Value("${node_name:}")
    public String nodeName;

    @Value("${http.proxy:}")
    public String httpProxy;

    @Bean
//    @ConditionalOnProperty(name = "http.proxy")
    public RestTemplateCustomizer proxyCustomizer() {
        return new RestTemplateCustomizer() {
            @Override
            public void customize(RestTemplate restTemplate) {
                HttpHost proxy = new HttpHost(httpProxy);

                logger.debug("Customizing RestTemplate with proxy of: " + proxy.toHostString());

                HttpClient httpClient = HttpClientBuilder.create()
                        .setRoutePlanner(new DefaultProxyRoutePlanner(proxy) {

                            @Override
                            public HttpHost determineProxy(HttpHost target,
                                                           HttpRequest request, HttpContext context)
                                    throws HttpException {

                                logger.debug("Proxy has been called for: " + request.getRequestLine().getUri() + " with proxy of: " + proxy.toHostString());
                                return super.determineProxy(target, request, context);
                            }

                        }).build();
                restTemplate.setRequestFactory(
                        new HttpComponentsClientHttpRequestFactory(httpClient));
            }
        };
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
