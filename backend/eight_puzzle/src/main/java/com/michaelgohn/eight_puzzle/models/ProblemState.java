package com.michaelgohn.eight_puzzle.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ProblemState {

    @Id
    @GeneratedValue
    private Long id;
    private State initState;
    private State goalState;
    private String heuristic;

    public ProblemState(){
        this.initState = null;
        this.goalState = null;
        this.heuristic = "";
    }

    public ProblemState(State initState, State goalState, String heuristic){
        this.initState = initState;
        this.goalState = goalState;
        this.heuristic = heuristic;
    }

    public State getInitState() {
        return initState;
    }
    public void setInitState(State initState) {
        this.initState = initState;
    }
    public State getGoalState() {
        return goalState;
    }
    public void setGoalState(State goalState) {
        this.goalState = goalState;
    }
    public String getHeuristic() {
        return heuristic;
    }
    public void setHeuristic(String heuristic) {
        this.heuristic = heuristic;
    }
}
