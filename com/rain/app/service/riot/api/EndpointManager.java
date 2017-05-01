package com.rain.app.service.riot.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import com.rain.app.service.riot.api.request.AsyncRequest;
import com.rain.app.service.riot.api.request.Request;
import com.rain.app.service.riot.api.request.RequestListener;
import com.rain.app.service.riot.api.request.limit.RateLimitException;

public class EndpointManager {

	private final ApiConfig config;
	private final AsyncRequestPool pool;
	private final Collection<RequestListener> listeners = new CopyOnWriteArrayList<RequestListener>();

	EndpointManager(ApiConfig config) {
		this.config = config;
		pool = new AsyncRequestPool(config);
	}

	void addListeners(RequestListener... listeners) {
		this.listeners.addAll(Arrays.asList(listeners));
	}

	void awaitAll() throws InterruptedException {
		pool.awaitAll();
	}

	void callMethod(ApiMethod method) throws RateLimitException, RiotApiException {
		new Request(config, method);
	}

	<T> T callMethodAndReturnDto(ApiMethod method) throws RateLimitException, RiotApiException {
		Request request = new Request(config, method);
		return request.getDto();
	}

	AsyncRequest callMethodAsynchronously(ApiMethod method) {
		AsyncRequest request = new AsyncRequest(config, method);
		request.addListeners(listeners.toArray(new RequestListener[listeners.size()]));
		pool.add(request);
		return request;
	}

	int getPoolSize() {
		return pool.getPoolSize();
	}

	int getQueueSize() {
		return pool.getQueueSize();
	}

	void removeListeners(RequestListener... listeners) {
		this.listeners.removeAll(Arrays.asList(listeners));
	}
}
