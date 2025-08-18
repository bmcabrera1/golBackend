package com.example.golbackend.modules.match_event.services;

import com.example.golbackend.modules.match.model.Match;
import com.example.golbackend.modules.match.repositories.MatchRepository;
import com.example.golbackend.modules.match_event.dto.MatchEventDto;
import com.example.golbackend.modules.match_event.model.MatchEvent;
import com.example.golbackend.modules.match_event.repositories.MatchEventRepository;
import com.example.golbackend.modules.players.model.Player;
import com.example.golbackend.modules.players.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchEventService {
    @Autowired
    private MatchEventRepository matchEventRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private PlayerRepository playerRepository;

    public MatchEvent createMatchEvent(Long matchId, MatchEventDto eventDto) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + matchId));

        MatchEvent event = new MatchEvent();
        event.setMatch(match);

        if (eventDto.getPlayerId() != null) {
            Player player = playerRepository.findById(eventDto.getPlayerId())
                    .orElseThrow(() -> new RuntimeException("Player not found with id: " + eventDto.getPlayerId()));
            event.setPlayer(player);
        }

        event.setEventType(eventDto.getEventType());
        event.setEventMinute(eventDto.getEventMinute());
        event.setNotes(eventDto.getNotes());

        return matchEventRepository.save(event);
    }

    public List<MatchEvent> getEventsByMatch(Long matchId) {
        return matchEventRepository.findByMatchMatchIdOrderByEventMinuteAsc(matchId);
    }
}
