package example.l5d.helloservice.controller;

import example.l5d.helloservice.config.ApplicationConfiguration;
import example.l5d.helloservice.response.DefaultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

import static org.springframework.util.StringUtils.hasText;

@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ApplicationConfiguration appConfig;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public DefaultResponse index() {

        logger.debug("Request received");

        logger.debug("Waiting...");
        try {
            Thread.sleep(appConfig.latency);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }

        DefaultResponse.DefaultResponseBuilder responseBuilder = DefaultResponse.builder()
                .serviceName(appConfig.serviceName)
                .nodeName(appConfig.nodeName)
                .podIp(appConfig.podIp)
                .latency(appConfig.latency);

        if (hasText(appConfig.target)) {
            String targetUrl = "http://" + appConfig.target;

            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(appConfig.httpProxyHost, appConfig.httpProxyPort));
            requestFactory.setProxy(proxy);

            responseBuilder.httpProxy(appConfig.httpProxyHost + ":" + appConfig.httpProxyPort);

            RestTemplate restTemplate = new RestTemplate(requestFactory);
            DefaultResponse response = restTemplate.getForObject(targetUrl, DefaultResponse.class);
            responseBuilder.downstreamResponse(response);
        }

        logger.debug("Returning response...");
        return responseBuilder
                .message("OK")
                .build();
    }
}
