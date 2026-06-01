package com.michaelgohn.eight_puzzle.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProblemStateDBObj {
    
    @Id
    @GeneratedValue
    private long id;

    @Column(length = 17)
    private String initStatePosition;

    @Column(length = 17)
    private String goalStatePosition;
    private String heuristic;

    public ProblemStateDBObj(String initStatePosition, String goalStatePosition, String heuristic) {
        this.initStatePosition = initStatePosition;
        this.goalStatePosition = goalStatePosition;
        this.heuristic = heuristic;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getInitStatePosition() {
        return initStatePosition;
    }
    public void setInitStatePosition(String initStatePosition) {
        this.initStatePosition = initStatePosition;
    }
    public String getGoalStatePosition() {
        return goalStatePosition;
    }
    public void setGoalStatePosition(String goalStatePosition) {
        this.goalStatePosition = goalStatePosition;
    }
    public String getHeuristic() {
        return heuristic;
    }
    public void setHeuristic(String heuristic) {
        this.heuristic = heuristic;
    }

}
