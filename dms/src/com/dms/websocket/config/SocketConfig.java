package com.dms.websocket.config;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

public class SocketConfig implements ServerApplicationConfig{

	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
		System.out.println("Config................"+scanned.size());
		Set<Class<?>> results = new HashSet<>();
        for (Class<?> clazz : scanned) {
        	System.out.println(clazz.getPackage().getName());
            if (clazz.getPackage().getName().startsWith("com.dms.websocket.socket")) {
                results.add(clazz);
            }
        }
        System.out.println(results.size());
        return results;
	}

	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
