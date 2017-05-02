package com.rain.app.service.riot.api;

import java.util.Locale;
import java.util.Objects;

import com.rain.app.service.riot.api.endpoints.champion.methods.GetChampion;
import com.rain.app.service.riot.api.endpoints.champion.methods.GetChampions;
import com.rain.app.service.riot.api.endpoints.champion_mastery.methods.GetChampionMasteriesBySummoner;
import com.rain.app.service.riot.api.endpoints.champion_mastery.methods.GetChampionMasteriesBySummonerByChampion;
import com.rain.app.service.riot.api.endpoints.champion_mastery.methods.GetChampionMasteryScoresBySummoner;
import com.rain.app.service.riot.api.endpoints.constants.constant.ChampListData;
import com.rain.app.service.riot.api.endpoints.constants.constant.SpellListData;
import com.rain.app.service.riot.api.endpoints.constants.methods.GetDataChampion;
import com.rain.app.service.riot.api.endpoints.constants.methods.GetDataChampionList;
import com.rain.app.service.riot.api.endpoints.constants.methods.GetDataItem;
import com.rain.app.service.riot.api.endpoints.constants.methods.GetDataItemList;
import com.rain.app.service.riot.api.endpoints.constants.methods.GetDataLanguageStrings;
import com.rain.app.service.riot.api.endpoints.constants.methods.GetDataLanguages;
import com.rain.app.service.riot.api.endpoints.constants.methods.GetDataMaps;
import com.rain.app.service.riot.api.endpoints.constants.methods.GetDataMastery;
import com.rain.app.service.riot.api.endpoints.constants.methods.GetDataMasteryList;
import com.rain.app.service.riot.api.endpoints.constants.methods.GetDataProfileIcons;
import com.rain.app.service.riot.api.endpoints.constants.methods.GetDataRealm;
import com.rain.app.service.riot.api.endpoints.constants.methods.GetDataRune;
import com.rain.app.service.riot.api.endpoints.constants.methods.GetDataRuneList;
import com.rain.app.service.riot.api.endpoints.constants.methods.GetDataSummonerSpell;
import com.rain.app.service.riot.api.endpoints.constants.methods.GetDataSummonerSpellList;
import com.rain.app.service.riot.api.endpoints.constants.methods.GetDataVersions;
import com.rain.app.service.riot.api.endpoints.league.methods.GetChallengerLeague;
import com.rain.app.service.riot.api.endpoints.league.methods.GetLeagueBySummoners;
import com.rain.app.service.riot.api.endpoints.league.methods.GetLeagueEntryBySummoners;
import com.rain.app.service.riot.api.endpoints.league.methods.GetMasterLeague;
import com.rain.app.service.riot.api.endpoints.masteries.methods.GetMasteriesBySummoner;
import com.rain.app.service.riot.api.endpoints.match.methods.GetMatch;
import com.rain.app.service.riot.api.endpoints.match.methods.GetMatchByMatchIdAndTournamentCode;
import com.rain.app.service.riot.api.endpoints.match.methods.GetMatchIdsByTournamentCode;
import com.rain.app.service.riot.api.endpoints.match.methods.GetMatchListByAccountId;
import com.rain.app.service.riot.api.endpoints.match.methods.GetRecentMatchListByAccountId;
import com.rain.app.service.riot.api.endpoints.runes.methods.GetRunesBySummoner;
import com.rain.app.service.riot.api.endpoints.spectator.methods.GetActiveGameBySummoner;
import com.rain.app.service.riot.api.endpoints.spectator.methods.GetFeaturedGames;
import com.rain.app.service.riot.api.endpoints.status.methods.GetShardData;
import com.rain.app.service.riot.api.endpoints.summoner.methods.GetSummoner;
import com.rain.app.service.riot.api.endpoints.summoner.methods.GetSummonerByAccount;
import com.rain.app.service.riot.api.endpoints.summoner.methods.GetSummonerByName;
import com.rain.app.service.riot.api.endpoints.tournament.methods.CreateTournament;
import com.rain.app.service.riot.api.endpoints.tournament.methods.CreateTournamentCodes;
import com.rain.app.service.riot.api.endpoints.tournament.methods.CreateTournamentProvider;
import com.rain.app.service.riot.api.endpoints.tournament.methods.GetLobbyEventsByCode;
import com.rain.app.service.riot.api.endpoints.tournament.methods.GetTournamentCode;
import com.rain.app.service.riot.api.endpoints.tournament.methods.UpdateTournamentCode;
import com.rain.app.service.riot.api.request.AsyncRequest;
import com.rain.app.service.riot.api.request.RequestListener;
import com.rain.app.service.riot.constant.Platform;
import com.rain.app.service.riot.constant.Region;

import net.rithms.riot.constant.Season;
import net.rithms.util.Convert;

public class RiotApiAsync {
	
	private final ApiConfig config;
	private final EndpointManager endpointManager;
	
	RiotApiAsync(ApiConfig config, EndpointManager endpointManager) {
		this.config = config;
		this.endpointManager = endpointManager;
	}
	
	public void addListeners(RequestListener... listeners) {
		endpointManager.addListeners(listeners);
	}
	
	public void awaitAll() throws InterruptedException {
		endpointManager.awaitAll();
	}
	
	public AsyncRequest callCustomApiMethod(ApiMethod method) throws RiotApiException {
		Objects.requireNonNull(method);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest createTournament(String tournamentName, int providerId) {
		ApiMethod method = new CreateTournament(getConfig(), tournamentName, providerId);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest createTournament(int providerId) {
		return createTournament(null, providerId);
	}
	
	public AsyncRequest createTournamentCodes(int tournamentId, int count, int teamSize, TournamentMap mapType, PickType pickType, SpectatorType spectatorType,
			String metaData, long... allowedSummonerIds) throws RiotApiException {
		Objects.requireNonNull(mapType);
		Objects.requireNonNull(pickType);
		Objects.requireNonNull(spectatorType);
		ApiMethod method = new CreateTournamentCodes(getConfig(), tournamentId, count, teamSize, mapType, pickType, spectatorType, metaData,
				allowedSummonerIds);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest createTournamentCodes(int tournamentId, int count, int teamSize, TournamentMap mapType, PickType pickType, SpectatorType spectatorType,
			long... allowedSummonerIds) throws RiotApiException {
		return createTournamentCodes(tournamentId, count, teamSize, mapType, pickType, spectatorType, null, allowedSummonerIds);
	}
	
	public AsyncRequest createTournamentProvider(String region, String callbackUrl) {
		Objects.requireNonNull(region);
		Objects.requireNonNull(callbackUrl);
		ApiMethod method = new CreateTournamentProvider(getConfig(), region, callbackUrl);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getActiveGameBySummoner(Platform platform, long summonerId) {
		Objects.requireNonNull(platform);
		Objects.requireNonNull(summonerId);
		ApiMethod method = new GetActiveGameBySummoner(getConfig(), platform, summonerId);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getChallengerLeague(Region region, QueueType queueType) {
		Objects.requireNonNull(region);
		Objects.requireNonNull(queueType);
		ApiMethod method = new GetChallengerLeague(getConfig(), region, queueType);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getChampion(Platform platform, int id) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetChampion(getConfig(), platform, id);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getChampionMasteriesBySummoner(Platform platform, long summonerId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetChampionMasteriesBySummoner(getConfig(), platform, summonerId);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getChampionMasteriesBySummonerByChampion(Platform platform, long summonerId, int championId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetChampionMasteriesBySummonerByChampion(getConfig(), platform, summonerId, championId);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getChampionMasteryScoresBySummoner(Platform platform, long summonerId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetChampionMasteryScoresBySummoner(getConfig(), platform, summonerId);
		return endpointManager.callMethodAsynchronously(method);
	}

	public AsyncRequest getChampions(Platform platform, boolean freeToPlay) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetChampions(getConfig(), platform, freeToPlay);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getChampions(Platform platform) {
		return getChampions(platform, false);
	}
	
	protected ApiConfig getConfig() {
		return config;
	}
	
	public AsyncRequest getDataChampion(Platform platform, int id, Locale locale, String version, ChampData... champData) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataChampion(getConfig(), platform, id, locale, version, champData);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getDataChampion(Platform platform, int id) {
		return getDataChampion(platform, id, null, null, (ChampData) null);
	}
	
	public AsyncRequest getDataChampionList(Platform platform, Locale locale, String version, boolean dataById, ChampListData... champListData) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataChampionList(getConfig(), platform, locale, version, dataById, champListData);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getDataChampionList(Platform platform) {
		return getDataChampionList(platform, null, null, false, (ChampListData) null);
	}
	
	public AsyncRequest getDataItem(Platform platform, int id, Locale locale, String version, ItemData... itemData) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataItem(getConfig(), platform, id, locale, version, itemData);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getDataItem(Platform platform, int id) {
		return getDataItem(platform, id, null, null, (ItemData) null);
	}
	
	public AsyncRequest getDataItemList(Platform platform, Locale locale, String version, ItemListData... itemListData) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataItemList(getConfig(), platform, locale, version, itemListData);
		return endpointManager.callMethodAsynchronously(method);
	}

	public AsyncRequest getDataItemList(Platform platform) {
		return getDataItemList(platform, null, null, (ItemListData) null);
	}
	
	public AsyncRequest getDataLanguages(Platform platform) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataLanguages(getConfig(), platform);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getDataLanguageStrings(Platform platform, Locale locale, String version) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataLanguageStrings(getConfig(), platform, locale, version);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getDataMaps(Platform platform, Locale locale, String version) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataMaps(getConfig(), platform, locale, version);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getDataMaps(Platform platform) {
		return getDataMaps(platform, null, null);
	}
	
	public AsyncRequest getDataMastery(Platform platform, int id, Locale locale, String version, MasteryData... masteryData) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataMastery(getConfig(), platform, id, locale, version, masteryData);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getDataMastery(Platform platform, int id) {
		return getDataMastery(platform, id, null, null, (MasteryData) null);
	}
	
	public AsyncRequest getDataMasteryList(Platform platform, Locale locale, String version, MasteryListData... masteryListData) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataMasteryList(getConfig(), platform, locale, version, masteryListData);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getDataMasteryList(Platform platform) {
		return getDataMasteryList(platform, null, null, (MasteryListData) null);
	}
	
	public AsyncRequest getDataProfileIcons(Platform platform, Locale locale, String version) throws RiotApiException {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataProfileIcons(getConfig(), platform, locale, version);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getDataProfileIcons(Platform platform) throws RiotApiException {
		return getDataProfileIcons(platform, null, null);
	}
	
	public AsyncRequest getDataRealm(Platform platform) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataRealm(getConfig(), platform);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getDataRune(Platform platform, int id, Locale locale, String version, RuneData... runeData) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataRune(getConfig(), platform, id, locale, version, runeData);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getDataRune(Platform platform, int id) {
		return getDataRune(platform, id, null, null, (RuneData) null);
	}
	
	public AsyncRequest getDataRuneList(Platform platform, Locale locale, String version, RuneListData... runeListData) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataRuneList(getConfig(), platform, locale, version, runeListData);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getDataRuneList(Platform platform) {
		return getDataRuneList(platform, null, null, (RuneListData) null);
	}
	
	public AsyncRequest getDataSummonerSpell(Platform platform, int id, Locale locale, String version, SpellData... spellData) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataSummonerSpell(getConfig(), platform, id, locale, version, spellData);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getDataSummonerSpell(Platform platform, int id) {
		return getDataSummonerSpell(platform, id, null, null, (SpellData) null);
	}
	
	public AsyncRequest getDataSummonerSpellList(Platform platform, Locale locale, String version, boolean dataById, SpellListData... spellListData) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataSummonerSpellList(getConfig(), platform, locale, version, dataById, spellListData);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getDataSummonerSpellList(Platform platform) {
		return getDataSummonerSpellList(platform, null, null, false, (SpellListData) null);
	}
	
	public AsyncRequest getDataVersions(Platform platform) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetDataVersions(getConfig(), platform);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getFeaturedGames(Platform platform) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetFeaturedGames(getConfig(), platform);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getLeagueBySummoners(Region region, String... summonerIds) {
		Objects.requireNonNull(region);
		Objects.requireNonNull(summonerIds);
		ApiMethod method = new GetLeagueBySummoners(getConfig(), region, Convert.joinString(",", summonerIds));
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getLeagueBySummoners(Region region, long... summonerIds) {
		return getLeagueBySummoners(region, Convert.longToString(summonerIds));
	}
	
	public AsyncRequest getLeagueEntryBySummoners(Region region, String... summonerIds) {
		Objects.requireNonNull(region);
		Objects.requireNonNull(summonerIds);
		ApiMethod method = new GetLeagueEntryBySummoners(getConfig(), region, Convert.joinString(",", summonerIds));
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getLeagueEntryBySummoners(Region region, long... summonerIds) {
		return getLeagueEntryBySummoners(region, Convert.longToString(summonerIds));
	}
	
	public AsyncRequest getLobbyEventsByTournament(String tournamentCode) {
		Objects.requireNonNull(tournamentCode);
		ApiMethod method = new GetLobbyEventsByCode(getConfig(), tournamentCode);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getMasterLeague(Region region, QueueType queueType) {
		Objects.requireNonNull(region);
		Objects.requireNonNull(queueType);
		ApiMethod method = new GetMasterLeague(getConfig(), region, queueType);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getMasteriesBySummoner(Platform platform, long summonerId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetMasteriesBySummoner(getConfig(), platform, summonerId);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getMatch(Platform platform, long matchId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetMatch(getConfig(), platform, matchId);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getMatchIdsByTournamentCode(Platform platform, String tournamentCode) {
		Objects.requireNonNull(platform);
		Objects.requireNonNull(tournamentCode);
		ApiMethod method = new GetMatchIdsByTournamentCode(getConfig(), platform, tournamentCode);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getMatchByMatchIdAndTournamentCode(Platform platform, long matchId, String tournamentCode) {
		Objects.requireNonNull(platform);
		Objects.requireNonNull(tournamentCode);
		ApiMethod method = new GetMatchByMatchIdAndTournamentCode(getConfig(), platform, matchId, tournamentCode);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getMatchListByAccountId(Platform platform, long accountId, String champion, String queue, String season, long beginTime, long endTime,
			int beginIndex, int endIndex) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetMatchListByAccountId(getConfig(), platform, accountId, champion, queue, season, beginTime, endTime, beginIndex, endIndex);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getMatchListByAccountId(Platform platform, long accountId, String champion, String queue, String season) {
		return getMatchListByAccountId(platform, accountId, champion, queue, season, -1, -1, -1, -1);
	}
	
	public AsyncRequest getMatchListByAccountId(Platform platform, long accountId) {
		return getMatchListByAccountId(platform, accountId, null, null, null);
	}
	
	@Deprecated
	public AsyncRequest getPlayerStatsSummary(Region region, Season season, long summonerId) {
		Objects.requireNonNull(region);
		ApiMethod method = new GetPlayerStatsSummary(getConfig(), region, season, summonerId);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	@Deprecated
	public AsyncRequest getPlayerStatsSummary(Region region, long summonerId) {
		return getPlayerStatsSummary(region, null, summonerId);
	}
	
	public int getPoolSize() {
		return endpointManager.getPoolSize();
	}
	
	public int getQueueSize() {
		return endpointManager.getQueueSize();
	}
	
	@Deprecated
	public AsyncRequest getRankedStats(Region region, Season season, long summonerId) {
		Objects.requireNonNull(region);
		ApiMethod method = new GetRankedStats(getConfig(), region, season, summonerId);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	@Deprecated
	public AsyncRequest getRankedStats(Region region, long summonerId) {
		return getRankedStats(region, null, summonerId);
	}
	
	public AsyncRequest getRecentMatchListByAccountId(Platform platform, long accountId) throws RiotApiException {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetRecentMatchListByAccountId(getConfig(), platform, accountId);
		return endpointManager.callMethodAsynchronously(method);
	}

	public AsyncRequest getRunesBySummoner(Platform platform, long summonerId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetRunesBySummoner(getConfig(), platform, summonerId);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getShardData(Platform platform) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetShardData(getConfig(), platform);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getSummonerByAccount(Platform platform, long accountId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetSummonerByAccount(getConfig(), platform, accountId);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getSummoner(Platform platform, long summonerId) {
		Objects.requireNonNull(platform);
		ApiMethod method = new GetSummoner(getConfig(), platform, summonerId);
		return endpointManager.callMethodAsynchronously(method);
	}

	public AsyncRequest getSummonerByName(Platform platform, String summonerName) {
		Objects.requireNonNull(platform);
		Objects.requireNonNull(summonerName);
		ApiMethod method = new GetSummonerByName(getConfig(), platform, summonerName);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public AsyncRequest getTournamentCode(String tournamentCode) {
		Objects.requireNonNull(tournamentCode);
		ApiMethod method = new GetTournamentCode(getConfig(), tournamentCode);
		return endpointManager.callMethodAsynchronously(method);
	}
	
	public void removeListeners(RequestListener... listeners) {
		endpointManager.removeListeners(listeners);
	}
	
	public void updateTournamentCode(String tournamentCode, TournamentMap mapType, PickType pickType, SpectatorType spectatorType, long... allowedSummonerIds) {
		Objects.requireNonNull(tournamentCode);
		ApiMethod method = new UpdateTournamentCode(getConfig(), tournamentCode, mapType, pickType, spectatorType, allowedSummonerIds);
		endpointManager.callMethodAsynchronously(method);
	}

	






}
