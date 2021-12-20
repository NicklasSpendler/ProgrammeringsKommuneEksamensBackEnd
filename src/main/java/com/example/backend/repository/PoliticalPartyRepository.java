package com.example.backend.repository;

import com.example.backend.model.PoliticalParty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Integer> {

}
