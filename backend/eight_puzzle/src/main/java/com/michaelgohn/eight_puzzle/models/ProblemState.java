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

    public boolean checkIfSolvable(State initState, State goalState){

        int[] flatInitState = new int[8];
        int flatInitStateIndex = 0;

        for(int i = 0; i < initState.getStatePosition().length; i++){
            for(int j = 0; j < initState.getStatePosition()[i].length; j++){
                if(initState.getStatePosition()[i][j] == 0){
                    continue;
                }
                flatInitState[flatInitStateIndex] = initState.getStatePosition()[i][j];
                flatInitStateIndex++;
            }
        }

        int[] flatGoalState = new int[8];
        int flatGoalStateIndex = 0;

        for(int i = 0; i < goalState.getStatePosition().length; i++){
            for(int j = 0; j < goalState.getStatePosition()[i].length; j++){
                if(goalState.getStatePosition()[i][j] == 0){
                    continue;
                }
                flatGoalState[flatGoalStateIndex] = goalState.getStatePosition()[i][j];
                flatGoalStateIndex++;
            }
        }

        int numInvInit = countInversions(flatInitState);
        int numInvGoal = countInversions(flatGoalState);

        if((numInvInit % 2 == 0 && numInvGoal % 2 == 0) || (numInvInit % 2 == 1 && numInvGoal % 2 == 1)) return true;
        else return false;
    }

    public int countInversions(int[] arr){

        int count = 0;
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = i + 1; j < arr.length; j++){
                if(arr[i] > arr[j]) count++;
            }
        }

        return count;
    }
}
