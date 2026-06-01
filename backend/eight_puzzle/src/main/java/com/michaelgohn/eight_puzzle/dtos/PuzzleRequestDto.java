package com.michaelgohn.eight_puzzle.dtos;

public class PuzzleRequestDto {
    private int[][] initMatrix;
    private int[][] goalMatrix;
    private String heuristic;

    public int[][] getInitMatrix() {
        return initMatrix;
    }
    public void setInitMatrix(int[][] initMatrix) {
        this.initMatrix = initMatrix;
    }
    public int[][] getGoalMatrix() {
        return goalMatrix;
    }
    public void setGoalMatrix(int[][] goalMatrix) {
        this.goalMatrix = goalMatrix;
    }
    public String getHeuristic() {
        return heuristic;
    }
    public void setHeuristic(String heuristic) {
        this.heuristic = heuristic;
    }
}
