package com.rain.app.service.riot.api.endpoints.tournament;

import com.rain.app.service.riot.api.ApiConfig;
import com.rain.app.service.riot.api.ApiMethod;
import com.rain.app.service.riot.api.HttpHeadParameter;
import com.rain.app.service.riot.api.RiotApiException;
import com.rain.app.service.riot.api.UrlParameter;

public abstract class TournamentApiMethod extends ApiMethod {

	private boolean allowMockMode = false;
	private boolean requireTournamentApiKey = false;

	protected TournamentApiMethod(ApiConfig config) {
		super(config, "tournament");
		requireTournamentApiKey();
	}

	protected void addTournamentApiKeyParameter() {
		add(new UrlParameter("api_key", getConfig().getTournamentKey()));
		add(new HttpHeadParameter("X-Riot-Token", getConfig().getTournamentKey()));
	}

	protected void allowMockMode() {
		allowMockMode = true;
	}

	@Override
	public void checkRequirements() throws RiotApiException {
		if (!allowMockMode && getConfig().getTournamentMockMode()) {
			throw new RiotApiException(RiotApiException.FORBIDDEN, "This method isn't available in tournament mock mode");
		}
		if (requireTournamentApiKey && getConfig().getTournamentKey() == null) {
			throw new RiotApiException(RiotApiException.MISSING_TOURNAMENT_API_KEY);
		}
	}

	protected void requireTournamentApiKey() {
		requireTournamentApiKey = true;
	}
}
