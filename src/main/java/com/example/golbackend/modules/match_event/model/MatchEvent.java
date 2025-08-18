package com.example.golbackend.modules.match_event.model;

import com.example.golbackend.modules.match.model.Match;
import com.example.golbackend.modules.players.model.Player;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "match_event")
public class MatchEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_event_id")
    private Long matchEventId;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Column(name = "event_minute")
    private Integer eventMinute;

    @Column(name = "notes")
    private String notes;
}
