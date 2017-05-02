package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.rain.app.service.riot.api.Dto;

public class Rune extends Dto implements Serializable{

	private static final long serialVersionUID = -4823668922402235509L;

	private String colloq;
	private boolean consumeOnFull;
	private boolean consumed;
	private int depth;
	private String description;
	private List<String> from;
	private String group;
	private boolean hideFromAll;
	private int id;
	private Image image;
	private boolean inStore;
	private List<String> into;
	private Map<String, Boolean> maps;
	private String name;
	private String plaintext;
	private String requiredChampion;
	private MetaData rune;
	private String sanitizedDescription;
	private int specialRecipe;
	private BasicDataStats stats;
	private int stacks;
	private List<String> tags;

	public String getColloq() {
		return colloq;
	}

	public int getDepth() {
		return depth;
	}

	public String getDescription() {
		return description;
	}

	public List<String> getFrom() {
		return from;
	}

	public String getGroup() {
		return group;
	}

	public int getId() {
		return id;
	}

	public Image getImage() {
		return image;
	}

	public List<String> getInto() {
		return into;
	}

	public Map<String, Boolean> getMaps() {
		return maps;
	}

	public String getName() {
		return name;
	}

	public String getPlaintext() {
		return plaintext;
	}

	public String getRequiredChampion() {
		return requiredChampion;
	}

	public MetaData getRune() {
		return rune;
	}

	public String getSanitizedDescription() {
		return sanitizedDescription;
	}

	public int getSpecialRecipe() {
		return specialRecipe;
	}

	public int getStacks() {
		return stacks;
	}

	public BasicDataStats getStats() {
		return stats;
	}

	public List<String> getTags() {
		return tags;
	}

	public boolean isConsumeOnFull() {
		return consumeOnFull;
	}

	public boolean isConsumed() {
		return consumed;
	}

	public boolean isHideFromAll() {
		return hideFromAll;
	}

	public boolean isInStore() {
		return inStore;
	}

	@Override
	public String toString() {
		return getId() + ": " + getName();
	}
}
