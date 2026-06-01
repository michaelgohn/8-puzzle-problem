package com.michaelgohn.eight_puzzle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michaelgohn.eight_puzzle.models.ProblemStateDBObj;

@Repository
public interface ProblemStateDBObjRepository extends JpaRepository<ProblemStateDBObj, Long> {
    
}
