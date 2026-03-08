package com.michaelgohn.eight_puzzle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michaelgohn.eight_puzzle.models.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    
}
