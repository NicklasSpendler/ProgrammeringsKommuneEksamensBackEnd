package com.example.backend.controller;

import com.example.backend.model.Candidate;
import com.example.backend.model.PoliticalParty;
import com.example.backend.repository.PoliticalPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PoliticalPartyController {

    @Autowired
    PoliticalPartyRepository politicalPartyRepository;

    @GetMapping("/politicalParty")
    public List<PoliticalParty> allPoliticalParties(){
        return politicalPartyRepository.findAll();
    }


    @PostMapping(value = "/politicalParty")
    public ResponseEntity<PoliticalParty> newPoliticalParty(@RequestBody PoliticalParty politicalParty) {
        politicalPartyRepository.save(politicalParty);
        return new ResponseEntity<>(politicalParty, HttpStatus.CREATED);
    }

    @DeleteMapping("/politicalParty/{id}")
    public ResponseEntity<Object> deletePoliticalParty(@PathVariable int id){
        try {
            politicalPartyRepository.deleteById(id);
        } catch (Exception err) {
            return new ResponseEntity<>("Candidate blev ikke fundet", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/politicalParty/{id}")
    public ResponseEntity<PoliticalParty> updatePoliticalParty(@PathVariable int id, @RequestBody PoliticalParty politicalParty){
        Optional<PoliticalParty> politicalPartyData = politicalPartyRepository.findById(id);
        if(politicalPartyData.isPresent()){
            PoliticalParty _politicalParty = politicalPartyData.get();
            _politicalParty.setPartyName(politicalParty.getPartyName());
            _politicalParty = politicalPartyRepository.save(_politicalParty);
            return new ResponseEntity<>(_politicalParty, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
