package com.zoho.examportal.repository;

import com.zoho.examportal.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CandidateRepo extends JpaRepository<Candidate,Integer> {

}
