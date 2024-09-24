package com.zoho.examportal.repository;

import com.zoho.examportal.entity.LogicalReasoningResults;
import com.zoho.examportal.entity.PsychometricEvaluationResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PsychometricEvaluationResRepo extends JpaRepository<PsychometricEvaluationResults,Integer> {
    Optional<PsychometricEvaluationResults> findByCandidateId(int candidateId);
}

