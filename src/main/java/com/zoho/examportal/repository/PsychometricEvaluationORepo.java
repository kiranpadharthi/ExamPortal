package com.zoho.examportal.repository;

import com.zoho.examportal.entity.LogicalReasoningOptions;
import com.zoho.examportal.entity.PsychometricEvaluationOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PsychometricEvaluationORepo extends JpaRepository<PsychometricEvaluationOptions,Integer> {

    List<PsychometricEvaluationOptions> findByQuestionId(int questionId);

    @Query("SELECT isCorrect from PsychometricEvaluationOptions where optionId=:optionId")
    Optional<Boolean> getAnswer(int optionId);
}
