package com.zoho.examportal.service;


import com.zoho.examportal.entity.Candidate;
import com.zoho.examportal.entity.LogicalReasoningResults;
import com.zoho.examportal.repository.LogicalReasoningResRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class LogicalReasoningResService {

    @Autowired
    private LogicalReasoningResRepo logicalReasoningResRepo;

    public LogicalReasoningResults saveResult(LogicalReasoningResults newEntry) {
        return logicalReasoningResRepo.save(newEntry);
    }
    public  Optional<LogicalReasoningResults> findByCandidateId(int candidateId){
        return logicalReasoningResRepo.findByCandidateId(candidateId);
    }

}
