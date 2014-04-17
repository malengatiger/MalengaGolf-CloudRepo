/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.golfkids.dto;

import java.util.List;

/**
 *
 * @author Aubrey Malabie
 */
public class ResponseDTO {

    private int statusCode;
    private String message;
    private PersonalPlayerDTO personalPlayer;
    private List<PersonalPlayerDTO> personalPlayerList;
    private List<PersonalScoreDTO> personalScoreList;
    private TourneyPlayerScoreDTO tourneyPlayerScore;
    private List<GolfGroupDTO> golfGroups;
    private List<ParentDTO> parents;
    private List<PlayerDTO> players;
    private List<TourneyPlayerScoreDTO> tourneyPlayers;
    private List<TourneyScoreByRoundDTO> tourneyScoreByRoundList;
    private List<TeeTimeDTO> teeTimeList;
    private List<TournamentDTO> tournaments;
    private List<LeaderBoardDTO> leaderBoardList;
    private List<CountryDTO> countries;
    private List<ProvinceDTO> provinces;
    private List<ClubDTO> clubs;
    private List<AgeGroupDTO> ageGroups;
    private List<AdministratorDTO> administrators;
    private List<GolfGroupPlayerDTO> golfGroupPlayers;
    private List<GolfGroupParentDTO> golfGroupParents;
    private List<ScorerDTO> scorers;
    private GolfGroupDTO golfGroup;
    private AdministratorDTO administrator;
    private List<LeaderBoardBucket> leaderBoardBucketList;
    
    //
    public static final int LOGIN_EXCEPTION = 101;
    public static final int DATA_EXCEPTION = 102;
    public static final int DUPLICATE_EXCEPTION = 103;
    public static final int GENERIC_EXCEPTION = 109;

    public List<LeaderBoardBucket> getLeaderBoardBucketList() {
        return leaderBoardBucketList;
    }

    public void setLeaderBoardBucketList(List<LeaderBoardBucket> leaderBoardBucketList) {
        this.leaderBoardBucketList = leaderBoardBucketList;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public AdministratorDTO getAdministrator() {
        return administrator;
    }

    public TourneyPlayerScoreDTO getTourneyPlayerScore() {
        return tourneyPlayerScore;
    }

    public void setTourneyPlayerScore(TourneyPlayerScoreDTO tourneyPlayerScore) {
        this.tourneyPlayerScore = tourneyPlayerScore;
    }

    public PersonalPlayerDTO getPersonalPlayer() {
        return personalPlayer;
    }

    public void setPersonalPlayer(PersonalPlayerDTO personalPlayer) {
        this.personalPlayer = personalPlayer;
    }

    public List<PersonalPlayerDTO> getPersonalPlayerList() {
        return personalPlayerList;
    }

    public void setPersonalPlayerList(List<PersonalPlayerDTO> personalPlayerList) {
        this.personalPlayerList = personalPlayerList;
    }

    public List<PersonalScoreDTO> getPersonalScoreList() {
        return personalScoreList;
    }

    public void setPersonalScoreList(List<PersonalScoreDTO> personalScoreList) {
        this.personalScoreList = personalScoreList;
    }

  

    public void setAdministrator(AdministratorDTO administrator) {
        this.administrator = administrator;
    }

    public GolfGroupDTO getGolfGroup() {
        return golfGroup;
    }

    public void setGolfGroup(GolfGroupDTO golfGroup) {
        this.golfGroup = golfGroup;
    }

    public List<ScorerDTO> getScorers() {
        return scorers;
    }

    public void setScorers(List<ScorerDTO> scorers) {
        this.scorers = scorers;
    }

    public List<GolfGroupPlayerDTO> getGolfGroupPlayers() {
        return golfGroupPlayers;
    }

    public void setGolfGroupPlayers(List<GolfGroupPlayerDTO> golfGroupPlayers) {
        this.golfGroupPlayers = golfGroupPlayers;
    }

    public List<GolfGroupParentDTO> getGolfGroupParents() {
        return golfGroupParents;
    }

    public void setGolfGroupParents(List<GolfGroupParentDTO> golfGroupParents) {
        this.golfGroupParents = golfGroupParents;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GolfGroupDTO> getGolfGroups() {
        return golfGroups;
    }

    public void setGolfGroups(List<GolfGroupDTO> golfGroups) {
        this.golfGroups = golfGroups;
    }

    public List<TourneyScoreByRoundDTO> getTourneyScoreByRoundList() {
        return tourneyScoreByRoundList;
    }

    public void setTourneyScoreByRoundList(List<TourneyScoreByRoundDTO> tourneyScoreByRoundList) {
        this.tourneyScoreByRoundList = tourneyScoreByRoundList;
    }

    public List<TeeTimeDTO> getTeeTimeList() {
        return teeTimeList;
    }

    public void setTeeTimeList(List<TeeTimeDTO> teeTimeList) {
        this.teeTimeList = teeTimeList;
    }

   

    public List<ParentDTO> getParents() {
        return parents;
    }

    public void setParents(List<ParentDTO> parents) {
        this.parents = parents;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }

    public List<TourneyPlayerScoreDTO> getTourneyPlayers() {
        return tourneyPlayers;
    }

    public void setTourneyPlayers(List<TourneyPlayerScoreDTO> tourneyPlayers) {
        this.tourneyPlayers = tourneyPlayers;
    }

    public List<TournamentDTO> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<TournamentDTO> tournaments) {
        this.tournaments = tournaments;
    }

    public List<LeaderBoardDTO> getLeaderBoardList() {
        return leaderBoardList;
    }

    public void setLeaderBoardList(List<LeaderBoardDTO> leaderBoardList) {
        this.leaderBoardList = leaderBoardList;
    }

    public List<CountryDTO> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryDTO> countries) {
        this.countries = countries;
    }

    public List<ProvinceDTO> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<ProvinceDTO> province) {
        this.provinces = province;
    }

    public List<ClubDTO> getClubs() {
        return clubs;
    }

    public void setClubs(List<ClubDTO> clubs) {
        this.clubs = clubs;
    }

    public List<AgeGroupDTO> getAgeGroups() {
        return ageGroups;
    }

    public void setAgeGroups(List<AgeGroupDTO> ageGroups) {
        this.ageGroups = ageGroups;
    }

    public List<AdministratorDTO> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<AdministratorDTO> administrators) {
        this.administrators = administrators;
    }
}
