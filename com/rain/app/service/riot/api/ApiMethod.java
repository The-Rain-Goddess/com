package com.rain.app.service.riot.api;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.rain.app.service.riot.api.request.RequestMethod;
import com.rain.app.service.riot.constant.Platform;
import com.rain.app.service.riot.constant.Region;

abstract public class ApiMethod {

	private final ApiConfig config;
	private final String service;
	private Platform platform = null;
	// we might be able to remove the region variable when the switch to API 3 is done
	private Region region = null;
	private String urlBase;
	private final List<UrlParameter> urlParameters = new LinkedList<UrlParameter>();
	private final List<HttpHeadParameter> httpHeadParameters = new LinkedList<HttpHeadParameter>();
	private RequestMethod method = RequestMethod.GET;
	private String body = null;
	private Type returnType = null;

	private boolean requireApiKey = false;

	protected ApiMethod(ApiConfig config, String service) {
		this.config = config;
		this.service = service;
	}

	protected void add(HttpHeadParameter p) {
		httpHeadParameters.add(p);
	}

	protected void add(UrlParameter p) {
		urlParameters.add(p);
	}

	protected void addApiKeyParameter() {
		add(new UrlParameter("api_key", config.getKey()));
	}

	public void buildJsonBody(Map<String, Object> map) {
		body = new Gson().toJson(map);
	}

	public void checkRequirements() throws RiotApiException {
		if (doesRequireApiKey() && getConfig().getKey() == null) {
			throw new RiotApiException(RiotApiException.MISSING_API_KEY);
		}
	}

	public boolean doesRequireApiKey() {
		return requireApiKey;
	}

	public String getBody() {
		return body;
	}

	protected ApiConfig getConfig() {
		return config;
	}

	public Platform getPlatform() {
		return platform;
	}

	public Region getRegion() {
		return region;
	}

	public Type getReturnType() {
		return returnType;
	}

	public List<HttpHeadParameter> getHttpHeadParameters() {
		return httpHeadParameters;
	}

	public RequestMethod getMethod() {
		return method;
	}

	public String getService() {
		return service;
	}

	public String getUrl() {
		StringBuilder url = new StringBuilder(urlBase);
		char connector = '?';
		for (UrlParameter p : urlParameters) {
			url.append(connector).append(p.toString());
			connector = '&';
		}
		return url.toString();
	}

	protected void requireApiKey() {
		requireApiKey = true;
	}

	protected void setPlatform(Platform platform) {
		this.platform = platform;
	}

	protected void setRegion(Region region) {
		this.region = region;
	}

	protected void setReturnType(Type returnType) {
		this.returnType = returnType;
	}

	protected void setMethod(RequestMethod method) {
		this.method = method;
	}

	protected void setUrlBase(String urlBase) {
		this.urlBase = urlBase;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}
