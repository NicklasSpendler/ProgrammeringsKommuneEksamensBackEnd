package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class PoliticalParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_ID")
    private int partyID;

    @Column(name = "party_name")
    String partyName;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "party_ID")
    @JsonBackReference
    private Set<Candidate> candidateSet;

    public PoliticalParty() {
    }

    public int getPartyID() {
        return partyID;
    }

    public void setPartyID(int partyID) {
        this.partyID = partyID;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public Set<Candidate> getCandidateSet() {
        return candidateSet;
    }

    public void setCandidateSet(Set<Candidate> candidateSet) {
        this.candidateSet = candidateSet;
    }
}
