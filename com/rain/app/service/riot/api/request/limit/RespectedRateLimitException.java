package com.rain.app.service.riot.api.request.limit;

public class RespectedRateLimitException extends RateLimitException {

	private static final long serialVersionUID = 8877368984231613516L;

	/**
	 * Constructs a {@code RespectedRateLimitException} with the specified attributes.
	 * 
	 * @param retryAfter
	 *            The time in seconds to wait before the api key limits get refreshed
	 * @param rateLimitType
	 *            The type of rate limit that has been exceeded
	 */
	public RespectedRateLimitException(final int retryAfter, final String rateLimitType) {
		super(getMessage(RATE_LIMITED) + " (Respected; Retry After: " + retryAfter + ")", retryAfter, rateLimitType);
	}
}
