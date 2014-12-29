package com.rdkc.linked.hint;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.FactoryBean;

public class HttpClientFactoryBean implements FactoryBean<HttpClient> {

	private HttpClientConnectionManager connectionManager;

	@Override
	public HttpClient getObject() throws Exception {
		return HttpClients.custom().setConnectionManager(connectionManager)
				.build();
	}

	@Override
	public Class<? extends HttpClient> getObjectType() {
		return CloseableHttpClient.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	public void setConnectionManager(HttpClientConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
}