package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class Mastery extends Dto implements Serializable {

	private static final long serialVersionUID = 8480226179945327205L;

	private List<String> description;
	private int id;
	private Image image;
	private String masteryTree;
	private String name;
	private String prereq;
	private int ranks;
	private List<String> sanitizedDescription;

	public List<String> getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public Image getImage() {
		return image;
	}

	// Legal values: Defense, Offense, Utility
	public String getMasteryTree() {
		return masteryTree;
	}

	public String getName() {
		return name;
	}

	public String getPrereq() {
		return prereq;
	}

	public int getRanks() {
		return ranks;
	}

	public List<String> getSanitizedDescription() {
		return sanitizedDescription;
	}

	@Override
	public String toString() {
		return getId() + ": " + getName();
	}
}
