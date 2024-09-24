package com.zoho.examportal.service;


import com.zoho.examportal.entity.Candidate;
import com.zoho.examportal.entity.LogicalReasoningResults;
import com.zoho.examportal.entity.PsychometricEvaluationResults;
import com.zoho.examportal.repository.LogicalReasoningResRepo;
import com.zoho.examportal.repository.PsychometricEvaluationResRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PsychometricEvaluationResService {

    @Autowired
    private PsychometricEvaluationResRepo psychometricEvaluationResRepo;

    public PsychometricEvaluationResults saveResult(PsychometricEvaluationResults newEntry) {
        return psychometricEvaluationResRepo.save(newEntry);
    }
    public Optional<PsychometricEvaluationResults> findByCandidateId(int candidateId){
        return psychometricEvaluationResRepo.findByCandidateId(candidateId);
    }

}

