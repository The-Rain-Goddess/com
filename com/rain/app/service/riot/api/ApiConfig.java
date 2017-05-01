/**
 * 
 */
package com.rain.app.service.riot.api;

import java.util.Objects;
import java.util.logging.Level;

/**
 * @author Ryan May 5/1/17
 *
 */
public class ApiConfig implements Cloneable {

	/**
	 * 
	 */
	private int asyncRequestTimeout = 10000;
	private Level debugLevel = Level.WARNING;
	private boolean debugToFile = false;
	private String key = null;
	private int maxAsyncThreads = 0;
	private int requestTimeout = 0;
	private boolean respectRateLimit = true;
	private String tournamentKey = null;
	private boolean tournamentMockMode = false;
	
	@Override
	public ApiConfig clone(){
		return new ApiConfig().setAsyncRequestTimeout(getAsyncRequestTimeout()).setDebugLevel(getDebugLevel()).setDebugToFile(getDebugToFile()).setKey(getKey())
				.setMaxAsyncThreads(getMaxAsyncThreads()).setRequestTimeout(getRequestTimeout()).setRespectRateLimit(getRespectRateLimit())
				.setTournamentKey(getTournamentKey());
	}
	
	public int getAsyncRequestTimeout() {
		return asyncRequestTimeout;
	}

	public Level getDebugLevel() {
		return debugLevel;
	}

	public boolean getDebugToFile() {
		return debugToFile;
	}

	public String getKey() {
		return key;
	}

	public int getMaxAsyncThreads() {
		return maxAsyncThreads;
	}

	public int getRequestTimeout() {
		return requestTimeout;
	}

	public boolean getRespectRateLimit() {
		return respectRateLimit;
	}

	public String getTournamentKey() {
		return tournamentKey;
	}

	public boolean getTournamentMockMode() {
		return tournamentMockMode;
	}

	public ApiConfig setAsyncRequestTimeout(int asyncRequestTimeout) {
		if (asyncRequestTimeout < 0) {
			throw new IllegalArgumentException("The timeout value must be greater than or equal to 0");
		}
		this.asyncRequestTimeout = asyncRequestTimeout;
		return this;
	}

	public ApiConfig setDebugLevel(Level debugLevel) {
		Objects.requireNonNull(debugLevel, "debug level must not be null");
		this.debugLevel = debugLevel;
		return this;
	}
	
	public ApiConfig setDebugToFile(boolean debugToFile) {
		this.debugToFile = debugToFile;
		return this;
	}
	
	public ApiConfig setKey(String key) {
		Objects.requireNonNull(key, "key must not be null");
		this.key = key;
		return this;
	}
	
	public ApiConfig setMaxAsyncThreads(int maxAsyncThreads) {
		if (maxAsyncThreads < 0) {
			throw new IllegalArgumentException("The max amount of threads to run must be greater than or equal to 0");
		}
		this.maxAsyncThreads = maxAsyncThreads;
		return this;
	}
	
	public ApiConfig setRequestTimeout(int requestTimeout) {
		if (requestTimeout < 0) {
			throw new IllegalArgumentException("The timeout value must be greater than or equal to 0");
		}
		this.requestTimeout = requestTimeout;
		return this;
	}
	
	public ApiConfig setRespectRateLimit(boolean respectRateLimit) {
		this.respectRateLimit = respectRateLimit;
		return this;
	}
	
	public ApiConfig setTournamentKey(String tournamentKey) {
		Objects.requireNonNull(tournamentKey, "tournamentKey must not be null");
		this.tournamentKey = tournamentKey;
		return this;
	}
	
	public ApiConfig setTournamentMockMode(boolean tournamentMockMode) {
		this.tournamentMockMode = tournamentMockMode;
		return this;
	}

	
	public ApiConfig() {
		
	}

}
