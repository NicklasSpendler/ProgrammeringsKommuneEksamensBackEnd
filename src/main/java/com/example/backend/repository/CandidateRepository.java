package com.example.backend.repository;

import com.example.backend.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    List<Candidate> findByPartyPartyID(int id);
}
