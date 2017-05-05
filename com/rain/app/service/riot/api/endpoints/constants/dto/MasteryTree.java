package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class MasteryTree extends Dto implements Serializable {

	private static final long serialVersionUID = 5475789665588541152L;

	private List<MasteryTreeList> Cunning;
	private List<MasteryTreeList> Ferocity;
	private List<MasteryTreeList> Resolve;

	public List<MasteryTreeList> getCunning() {
		return Cunning;
	}

	public List<MasteryTreeList> getFerocity() {
		return Ferocity;
	}

	public List<MasteryTreeList> getResolve() {
		return Resolve;
	}
}
