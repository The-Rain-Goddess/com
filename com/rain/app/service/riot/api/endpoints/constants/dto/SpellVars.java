package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class SpellVars extends Dto implements Serializable {

	private static final long serialVersionUID = -8316487089517907400L;

	private List<Double> coeff;
	private String dyn;
	private String key;
	private String link;
	private String ranksWith;

	public List<Double> getCoeff() {
		return coeff;
	}

	public String getDyn() {
		return dyn;
	}

	public String getKey() {
		return key;
	}

	public String getLink() {
		return link;
	}

	public String getRanksWith() {
		return ranksWith;
	}

	@Override
	public String toString() {
		return getKey();
	}
}
