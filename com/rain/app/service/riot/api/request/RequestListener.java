package com.rain.app.service.riot.api.request;

import com.rain.app.service.riot.api.RiotApiException;

public interface RequestListener {
	/**
	 * Invoked when a request fails.
	 * 
	 * @param e
	 *            Exception thrown
	 */
	public void onRequestFailed(RiotApiException e);

	/**
	 * Invoked when a request succeeds.
	 * 
	 * @param request
	 *            AsyncRequest object
	 * @see AsyncRequest
	 */
	public void onRequestSucceeded(AsyncRequest request);

	/**
	 * Invoked when a request times out.
	 * 
	 * @param request
	 *            AsyncRequest object
	 * @see AsyncRequest
	 */
	public void onRequestTimeout(AsyncRequest request);
}
