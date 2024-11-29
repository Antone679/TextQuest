package com.quest.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "quest_id", nullable = false)
    private Quest quest;
    @Column(name = "text", columnDefinition = "TEXT")
    private String text;
    @Column(name = "correct_answer", columnDefinition = "TEXT")
    private String correctAnswer;
    @Column(name = "wrong_answer", columnDefinition = "TEXT")
    private String wrongAnswer;
    @Column(name = "neutral_answer", columnDefinition = "TEXT")
    private String neutralAnswer;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date createdAt;

    public Question(Quest questId, String text, String correctAnswer, String wrongAnswer, String neutralAnswer) {
        this.quest = questId;
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer = wrongAnswer;
        this.neutralAnswer = neutralAnswer;
    }

    public Question() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(String wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }

    public String getNeutralAnswer() {
        return neutralAnswer;
    }

    public void setNeutralAnswer(String neutralAnswer) {
        this.neutralAnswer = neutralAnswer;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Question question = (Question) object;
        return Objects.equals(id, question.id) && Objects.equals(quest, question.quest) && Objects.equals(text, question.text) && Objects.equals(correctAnswer, question.correctAnswer) && Objects.equals(wrongAnswer, question.wrongAnswer) && Objects.equals(neutralAnswer, question.neutralAnswer) && Objects.equals(createdAt, question.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quest, text, correctAnswer, wrongAnswer, neutralAnswer, createdAt);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questId=" + quest +
                ", text='" + text + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", wrongAnswer='" + wrongAnswer + '\'' +
                ", neutralAnswer='" + neutralAnswer + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
