package com.quest.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "quest_id")
    private Quest quest;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "played_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date playedAt;

    public Game(Quest quest, User user) {
        this.quest = quest;
        this.user = user;
    }

    public Game() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Game game = (Game) object;
        return Objects.equals(id, game.id) && Objects.equals(quest, game.quest) && Objects.equals(user, game.user) && Objects.equals(playedAt, game.playedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quest, user, playedAt);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", quest=" + quest +
                ", user=" + user +
                ", playedAt=" + playedAt +
                '}';
    }
}
