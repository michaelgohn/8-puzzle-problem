package com.michaelgohn.eight_puzzle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michaelgohn.eight_puzzle.models.ProblemState;

@Repository
public interface ProblemStateRepository extends JpaRepository<ProblemState, Long> {
    
}
