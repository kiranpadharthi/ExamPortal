package com.zoho.examportal.repository;

import com.zoho.examportal.entity.LogicalReasoningResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LogicalReasoningResRepo extends JpaRepository<LogicalReasoningResults,Integer> {
    Optional<LogicalReasoningResults> findByCandidateId(int candidateId);
}
