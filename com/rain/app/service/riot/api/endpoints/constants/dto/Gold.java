package com.rain.app.service.riot.api.endpoints.constants.dto;

import java.io.Serializable;

import com.rain.app.service.riot.api.Dto;

public class Gold extends Dto implements Serializable {

	private static final long serialVersionUID = -2996361282729522012L;

	private int base;
	private boolean purchasable;
	private int sell;
	private int total;

	public int getBase() {
		return base;
	}

	public int getSell() {
		return sell;
	}

	public int getTotal() {
		return total;
	}

	public boolean isPurchasable() {
		return purchasable;
	}

	@Override
	public String toString() {
		return String.valueOf(getBase());
	}
}
