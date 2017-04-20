package com.cts.cldapps.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudException;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.cloud.service.common.MysqlServiceInfo;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.*;

public class SpringApplicationContextInitializer
		implements ApplicationContextInitializer<ConfigurableApplicationContext> {
	private static final Map<Class<? extends ServiceInfo>, String> serviceTypeToProfileName = new HashMap<>();
	static {
		serviceTypeToProfileName.put(MysqlServiceInfo.class, "mysql");
	}

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		Cloud cloud = getCloud();
		String[] persistenceProfiles = getCloudProfile(cloud);
	}

	private Cloud getCloud() {
		try {
			CloudFactory cloudFactory = new CloudFactory();
			return cloudFactory.getCloud();
		} catch (CloudException ce) {
			return null;
		}
	}

	public String[] getCloudProfile(Cloud cloud) {
		if (cloud == null) {
			return null;
		}
		List<String> profiles = new ArrayList<>();
		List<ServiceInfo> serviceInfos = cloud.getServiceInfos();
		for (ServiceInfo serviceInfo : serviceInfos) {
			if (serviceTypeToProfileName.containsKey(serviceInfo.getClass())) {
				profiles.add(serviceTypeToProfileName.get(serviceInfo.getClass()));
			}
		}

		if (profiles.size() > 0) {
			return createProfileNames(profiles.get(0), "cloud");
		}else{
			throw new IllegalStateException("Mysql Service is not available in cloud");
		}
		
	}

	private String[] createProfileNames(String baseName, String suffix) {
		String[] profileNames = { baseName, baseName + "-" + suffix };
		return profileNames;
	}
}
