package com.michaelgohn.eight_puzzle.dtos;

public class PuzzleRequestDto {
    private int[][] initState;
    private int[][] goalState;
    private String heuristic;

    public int[][] getInitState() {
        return initState;
    }
    public void setInitState(int[][] initState) {
        this.initState = initState;
    }
    public int[][] getGoalState() {
        return goalState;
    }
    public void setGoalState(int[][] goalState) {
        this.goalState = goalState;
    }
    public String getHeuristic() {
        return heuristic;
    }
    public void setHeuristic(String heuristic) {
        this.heuristic = heuristic;
    }
}
