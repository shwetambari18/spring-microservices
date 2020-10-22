package com.shweta.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shweta.microservices.limitsservice.bean.LimitsConfiguration;

@RestController
public class LimitsConfigurationController {
	
	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitsConfiguration retrieveLimitsConfigurations()
	{
		return new LimitsConfiguration(configuration.getMaximum(),configuration.getMinimum());
	}
	
	@GetMapping("/fault-tolerance-example")
	@HystrixCommand(fallbackMethod="retrieveFallbackConfiguration")
	public LimitsConfiguration retrieveConfiguration()
	{
		throw new RuntimeException("Not Available");
	}

	public LimitsConfiguration retrieveFallbackConfiguration()
	{
		return new LimitsConfiguration(9999,9);
	}
}
