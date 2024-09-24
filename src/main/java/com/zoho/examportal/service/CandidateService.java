package com.zoho.examportal.service;


import com.zoho.examportal.entity.Candidate;
import com.zoho.examportal.repository.CandidateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepo candidateRepo;

    public Optional<Candidate> getCandidateById(int id) {
        return candidateRepo.findById(id);
    }

    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepo.save(candidate);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepo.findAll();
    }
}
