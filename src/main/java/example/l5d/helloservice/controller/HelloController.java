package example.l5d.helloservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import example.l5d.helloservice.config.ApplicationConfiguration;

@Controller
public class HelloController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected ApplicationConfiguration appConfig;	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String pwdReset(Model model) {
		
		logger.debug("Request received");
		model.addAttribute("serviceName", appConfig.serviceName);
		
		logger.debug("Waiting...");
		try {
			Thread.sleep(appConfig.responseDelay);
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("Returning response...");
		return "response";
	}	
}
