package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.Map;

import com.rain.app.service.riot.api.Dto;

public class Realm extends Dto implements Serializable {
	private static final long serialVersionUID = -3494066446872616575L;

	private String cdn;
	private String css;
	private String dd;
	private String l;
	private String lg;
	private Map<String, String> n;
	private int profileiconmax;
	private String store;
	private String v;

	public String getCdn() {
		return cdn;
	}

	public String getCss() {
		return css;
	}

	public String getDd() {
		return dd;
	}

	public String getL() {
		return l;
	}

	public String getLg() {
		return lg;
	}

	public Map<String, String> getN() {
		return n;
	}

	public int getProfileiconmax() {
		return profileiconmax;
	}

	public String getStore() {
		return store;
	}

	public String getV() {
		return v;
	}

	@Override
	public String toString() {
		return getL();
	}
}
