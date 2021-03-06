package example.l5d.helloservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

	@Value("${service.name}")
	public String serviceName;
	
	@Value("${response.delay}")
	public int responseDelay;	
}
