package com.zoho.examportal.controller;

import com.zoho.examportal.entity.Candidate;
import com.zoho.examportal.repository.CandidateRepo;
import com.zoho.examportal.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    //Used to save candidate details
    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@Validated @RequestBody Candidate candidate) {
        Candidate saveCandidate = candidateService.saveCandidate(candidate);
        return new ResponseEntity<>(saveCandidate, HttpStatus.CREATED);
    }
    @GetMapping
    public List<Candidate> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable int id) {
        Optional<Candidate> candidate = candidateService.getCandidateById(id);
        return candidate.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
