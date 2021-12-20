package com.example.backend.controller;

import com.example.backend.model.Candidate;
import com.example.backend.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
public class CandidateController {

    @Autowired
    CandidateRepository candidateRepository;

    @GetMapping("/candidate")
    public List<Candidate> allCandidates(){
        return candidateRepository.findAll();
    }

    @GetMapping("/candidate/politcalParty/{id}")
    public List<Candidate> getCandidateByPartyID(@PathVariable int id) {
        return candidateRepository.findByPartyPartyID(id);
    }

    @PostMapping(value = "/candidate", consumes = "application/json")
    public ResponseEntity<Candidate> newCandidate(@RequestBody Candidate candidate) {
        candidateRepository.save(candidate);
        return new ResponseEntity<>(candidate, HttpStatus.CREATED);
    }

    @DeleteMapping("/candidate/{id}")
    public ResponseEntity<Object> deleteCandidate(@PathVariable int id){
        try {
            candidateRepository.deleteById(id);
        } catch (Exception err) {
            return new ResponseEntity<>("Candidate blev ikke fundet", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/candidate/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable int id, @RequestBody Candidate candidate){
        Optional<Candidate> candidateData = candidateRepository.findById(id);
        if(candidateData.isPresent()){
            Candidate _candidate = candidateData.get();
            _candidate.setCandidateName(candidate.getCandidateName());
            _candidate.setParty(candidate.getParty());
            _candidate.setNumberOfVotes(candidate.getNumberOfVotes());
            _candidate = candidateRepository.save(_candidate);
            return new ResponseEntity<>(_candidate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
