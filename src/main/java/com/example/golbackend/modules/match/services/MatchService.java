package com.example.golbackend.modules.match.services;

import com.example.golbackend.modules.championship_managment.model.Championship;
import com.example.golbackend.modules.championship_managment.repositories.ChampionshipRepository;
import com.example.golbackend.modules.match.dto.MatchDto;
import com.example.golbackend.modules.match.dto.UpdateMatchResultDto;
import com.example.golbackend.modules.match.model.Match;
import com.example.golbackend.modules.match.repositories.MatchRepository;
import com.example.golbackend.modules.matchday.model.Matchday;
import com.example.golbackend.modules.matchday.repositories.MatchdayRepository;
import com.example.golbackend.modules.phase.model.Phase;
import com.example.golbackend.modules.standing.model.Standing;
import com.example.golbackend.modules.standing.repositories.StandingRepository;
import com.example.golbackend.modules.team_managment.model.Team;
import com.example.golbackend.modules.team_managment.repositories.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private MatchdayRepository matchdayRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private StandingRepository standingRepository;
    @Autowired
    private ChampionshipRepository championshipRepository;

    public Match createMatch(Long matchdayId, MatchDto matchDto) {
        Matchday matchday = matchdayRepository.findById(matchdayId)
                .orElseThrow(() -> new RuntimeException("Matchday not found with id: " + matchdayId));
        Team homeTeam = teamRepository.findById(matchDto.getHomeTeamId())
                .orElseThrow(() -> new RuntimeException("Home Team not found with id: " + matchDto.getHomeTeamId()));
        Team awayTeam = teamRepository.findById(matchDto.getAwayTeamId())
                .orElseThrow(() -> new RuntimeException("Away Team not found with id: " + matchDto.getAwayTeamId()));

        Match match = new Match();
        match.setMatchday(matchday);
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setMatchTime(matchDto.getMatchTime());
        match.setLocation(matchDto.getLocation());
        match.setStatus("PENDING");

        return matchRepository.save(match);
    }

    public List<Match> getMatchesByMatchday(Long matchdayId) {
        return matchRepository.findByMatchdayMatchdayId(matchdayId);
    }

    @Transactional
    public Match updateMatchResult(Long matchId, UpdateMatchResultDto resultDto) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + matchId));

        // Lógica para revertir puntos si el partido ya estaba terminado
        if ("FINISHED".equals(match.getStatus())) {
            updateStandings(match, true); // Revertir
        }

        match.setHomeGoals(resultDto.getHomeGoals());
        match.setAwayGoals(resultDto.getAwayGoals());
        match.setStatus(resultDto.getStatus());

        Match updatedMatch = matchRepository.save(match);

        // Aplicar nuevos puntos si el partido se marca como terminado
        if ("FINISHED".equals(updatedMatch.getStatus())) {
            updateStandings(updatedMatch, false); // Aplicar
        }

        return updatedMatch;
    }

    private void updateStandings(Match match, boolean revert) {
        Long phaseId = match.getMatchday().getPhase().getPhaseId();
        Championship championship = match.getMatchday().getPhase().getChampionship();

        Standing homeStanding = standingRepository.findByPhase_PhaseIdAndTeam_Id(phaseId, match.getHomeTeam().getId())
                .orElseGet(() -> createInitialStanding(match.getMatchday().getPhase(), match.getHomeTeam()));

        Standing awayStanding = standingRepository.findByPhase_PhaseIdAndTeam_Id(phaseId, match.getAwayTeam().getId())
                .orElseGet(() -> createInitialStanding(match.getMatchday().getPhase(), match.getAwayTeam()));

        int multiplier = revert ? -1 : 1;

        homeStanding.setMatchesPlayed(homeStanding.getMatchesPlayed() + multiplier);
        awayStanding.setMatchesPlayed(awayStanding.getMatchesPlayed() + multiplier);

        homeStanding.setGoalsFor(homeStanding.getGoalsFor() + (match.getHomeGoals() * multiplier));
        homeStanding.setGoalsAgainst(homeStanding.getGoalsAgainst() + (match.getAwayGoals() * multiplier));
        awayStanding.setGoalsFor(awayStanding.getGoalsFor() + (match.getAwayGoals() * multiplier));
        awayStanding.setGoalsAgainst(awayStanding.getGoalsAgainst() + (match.getHomeGoals() * multiplier));

        if (match.getHomeGoals() > match.getAwayGoals()) {
            homeStanding.setWins(homeStanding.getWins() + multiplier);
            homeStanding.setPoints(homeStanding.getPoints() + (championship.getPointsWin() * multiplier));
            awayStanding.setLosses(awayStanding.getLosses() + multiplier);
            awayStanding.setPoints(awayStanding.getPoints() + (championship.getPointsLose() * multiplier));
        } else if (match.getHomeGoals() < match.getAwayGoals()) {
            homeStanding.setLosses(homeStanding.getLosses() + multiplier);
            homeStanding.setPoints(homeStanding.getPoints() + (championship.getPointsLose() * multiplier));
            awayStanding.setWins(awayStanding.getWins() + multiplier);
            awayStanding.setPoints(awayStanding.getPoints() + (championship.getPointsWin() * multiplier));
        } else { // Empate
            homeStanding.setDraws(homeStanding.getDraws() + multiplier);
            homeStanding.setPoints(homeStanding.getPoints() + (championship.getPointsDraw() * multiplier));
            awayStanding.setDraws(awayStanding.getDraws() + multiplier);
            awayStanding.setPoints(awayStanding.getPoints() + (championship.getPointsDraw() * multiplier));
        }

        homeStanding.setGoalDifference(homeStanding.getGoalsFor() - homeStanding.getGoalsAgainst());
        awayStanding.setGoalDifference(awayStanding.getGoalsFor() - awayStanding.getGoalsAgainst());

        standingRepository.save(homeStanding);
        standingRepository.save(awayStanding);
    }

    private Standing createInitialStanding(Phase phase, Team team) {
        Standing standing = new Standing();
        standing.setPhase(phase);
        standing.setTeam(team);
        return standing;
    }
}
