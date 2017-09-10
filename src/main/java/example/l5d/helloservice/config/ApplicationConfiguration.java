package example.l5d.helloservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

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

	@Value("${http.proxy.host:localhost}")
	public String httpProxyHost;

	@Value("${http.proxy.port:80}")
	public int httpProxyPort;
}
