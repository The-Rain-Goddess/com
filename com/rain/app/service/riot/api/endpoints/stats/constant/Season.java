package com.rain.app.service.riot.api.endpoints.stats.constant;

public enum Season {
	// Seasons with Number
		SEASON3("SEASON3"),
		SEASON4("SEASON2014"),
		SEASON5("SEASON2015"),
		SEASON6("SEASON2016"),
		SEASON7("SEASON2017"),

		// Seasons with Year
		SEASON2013("SEASON3"),
		SEASON2014("SEASON2014"),
		SEASON2015("SEASON2015"),
		SEASON2016("SEASON2016"),
		SEASON2017("SEASON2017"),

		// Preseasons
		PRESEASON3("PRESEASON3"),
		PRESEASON2014("PRESEASON2014"),
		PRESEASON2015("PRESEASON2015"),
		PRESEASON2016("PRESEASON2016"),
		PRESEASON2017("PRESEASON2017"),

		// Number
		THREE("SEASON3"),
		FOUR("SEASON2014"),
		FIVE("SEASON2015"),
		SIX("SEASON2016"),
		SEVEN("SEASON2017"),

		// Current Season
		CURRENT("SEASON2017");

		private String season;

		Season(String season) {
			this.season = season;
		}

		public String getName() {
			return season;
		}

		@Override
		public String toString() {
			return getName();
		}
}
