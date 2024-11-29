package com.quest.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "quest")
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "success", nullable = false, columnDefinition = "TEXT")
    private String success;
    @Column(name = "failure", nullable = false, columnDefinition = "TEXT")
    private String failure;
    @Column(name = "difficulty", nullable = false)
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    public Quest() {
    }

    public Quest(String name, String description, String success, String failure, Difficulty difficulty) {
        this.name = name;
        this.description = description;
        this.success = success;
        this.failure = failure;
        this.difficulty = difficulty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFailure() {
        return failure;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Quest quest = (Quest) object;
        return Objects.equals(id, quest.id) && Objects.equals(name, quest.name) && Objects.equals(description, quest.description) && Objects.equals(success, quest.success) && Objects.equals(failure, quest.failure) && difficulty == quest.difficulty && Objects.equals(createdAt, quest.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, success, failure, difficulty, createdAt);
    }

    @Override
    public String toString() {
        return "Quest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", success='" + success + '\'' +
                ", failure='" + failure + '\'' +
                ", difficulty=" + difficulty +
                ", createdAt=" + createdAt +
                ", questions=" + questions +
                '}';
    }
}
