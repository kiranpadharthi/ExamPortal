package com.zoho.examportal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "psychometric_evaluation_questions")
public class PsychometricEvaluationQuestions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    @Column(name = "difficulty_level")
    private Integer difficultyLevel;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "fetched_at")
    private LocalDateTime fetchedAt;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PsychometricEvaluationOptions> options;

    public Integer getId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getFetchedAt() {
        return fetchedAt;
    }

    public void setFetchedAt(LocalDateTime fetchedAt) {
        this.fetchedAt = fetchedAt;
    }

    public Set<PsychometricEvaluationOptions> getOptions() {
        return options;
    }

    public void setOptions(Set<PsychometricEvaluationOptions> options) {
        this.options = options;
    }
}


