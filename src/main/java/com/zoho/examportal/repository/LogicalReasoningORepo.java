package com.zoho.examportal.repository;

import com.zoho.examportal.entity.LogicalReasoningOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LogicalReasoningORepo extends JpaRepository<LogicalReasoningOptions,Integer> {

    List<LogicalReasoningOptions> findByQuestionId(int questionId);

    @Query("SELECT isCorrect from LogicalReasoningOptions where optionId=:optionId")
    Optional<Boolean> getAnswer(int optionId);
}
