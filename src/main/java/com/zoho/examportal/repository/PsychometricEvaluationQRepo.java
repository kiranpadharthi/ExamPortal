package com.zoho.examportal.repository;

import com.zoho.examportal.entity.LogicalReasoningQuestions;
import com.zoho.examportal.entity.PsychometricEvaluationQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PsychometricEvaluationQRepo extends JpaRepository<PsychometricEvaluationQuestions,Integer> {

    @Query("SELECT q FROM PsychometricEvaluationQuestions q WHERE q.difficultyLevel=:level and q.fetchedAt<:time")
    List<PsychometricEvaluationQuestions> getQuestionByLevelAndTime(int level, @Param("time") LocalDateTime localDateTime);

    @Query("SELECT difficultyLevel FROM PsychometricEvaluationQuestions WHERE questionId=:questionId")
    Integer getLevel(int questionId);
}

