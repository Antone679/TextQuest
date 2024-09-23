package com.quest;

import java.io.Serializable;
import java.util.Objects;

public class Unit implements Serializable {
    private int id;
    private static final long serialVersionUID = 1L;
    private String question;
    private String correctAnswer;
    private String wrongAnswer;
    private String failureDescription;

    public Unit() {
    }

    public Unit(String question, String correctAnswer, String wrongAnswer, String failureDescription) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer = wrongAnswer;
        this.failureDescription = failureDescription;
    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public String getFailureDescription() {
        return failureDescription;
    }

    public void setFailureDescription(String failureDescription) {
        this.failureDescription = failureDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Unit unit = (Unit) object;
        return id == unit.id && Objects.equals(question, unit.question) && Objects.equals(correctAnswer, unit.correctAnswer) && Objects.equals(wrongAnswer, unit.wrongAnswer) && Objects.equals(failureDescription, unit.failureDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, correctAnswer, wrongAnswer, failureDescription);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", wrongAnswer='" + wrongAnswer + '\'' +
                ", failureDescription='" + failureDescription + '\'' +
                '}';
    }
}
