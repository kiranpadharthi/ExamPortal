package com.zoho.examportal.repository;

import com.zoho.examportal.entity.LogicalReasoningQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogicalReasoningQRepo extends JpaRepository<LogicalReasoningQuestions,Integer> {

    @Query("SELECT q FROM LogicalReasoningQuestions q WHERE q.difficultyLevel=:level and q.fetchedAt<:time")
    List<LogicalReasoningQuestions> getQuestionByLevelAndTime(int level, @Param("time") LocalDateTime localDateTime);

    @Query("SELECT difficultyLevel FROM LogicalReasoningQuestions WHERE questionId=:questionId")
    Integer getLevel(int questionId);
}
