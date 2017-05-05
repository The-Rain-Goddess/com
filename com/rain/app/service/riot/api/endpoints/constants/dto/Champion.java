package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;
import java.util.List;

import com.rain.app.service.riot.api.Dto;

public class Champion extends Dto implements Serializable {

	private static final long serialVersionUID = 8120597968700936522L;

	private List<String> allytips;
	private String blurb;
	private List<String> enemytips;
	private int id;
	private Image image;
	private Info info;
	private String key;
	private String lore;
	private String name;
	private String partype;
	private Passive passive;
	private List<Recommended> recommended;
	private List<Skin> skins;
	private List<ChampionSpell> spells;
	private Stats stats;
	private List<String> tags;
	private String title;

	public List<String> getAllytips() {
		return allytips;
	}

	public String getBlurb() {
		return blurb;
	}

	public List<String> getEnemytips() {
		return enemytips;
	}

	public int getId() {
		return id;
	}

	public Image getImage() {
		return image;
	}

	public Info getInfo() {
		return info;
	}

	public String getKey() {
		return key;
	}

	public String getLore() {
		return lore;
	}

	public String getName() {
		return name;
	}

	public String getPartype() {
		return partype;
	}

	public Passive getPassive() {
		return passive;
	}

	public List<Recommended> getRecommended() {
		return recommended;
	}

	public List<Skin> getSkins() {
		return skins;
	}

	public List<ChampionSpell> getSpells() {
		return spells;
	}

	public Stats getStats() {
		return stats;
	}

	public List<String> getTags() {
		return tags;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return getId() + ": " + getName();
	}
}
