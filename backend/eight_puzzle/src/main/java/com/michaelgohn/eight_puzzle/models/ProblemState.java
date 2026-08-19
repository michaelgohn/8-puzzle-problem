package com.michaelgohn.eight_puzzle.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class ProblemState {

    private String title;
    private State initState;
    private State goalState;
    private List<State> solutionPath;
    private String heuristic;

    public ProblemState(){
        this.title = "";
        this.initState = null;
        this.goalState = null;
        this.solutionPath = null;
        this.heuristic = "";
    }

    public ProblemState(String title, State initState, State goalState, List<State> solutionPath, String heuristic){
        this.title = title;
        this.initState = initState;
        this.goalState = goalState;
        this.solutionPath = solutionPath;
        this.heuristic = heuristic;
    }

    public ProblemStateDBObj convertProblemState(){
        int[][] initMatrix = this.getInitState().getStatePosition();
        String initString = matrixToString(initMatrix);

        int[][] goalMatrix = this.getGoalState().getStatePosition();
        String goalString = matrixToString(goalMatrix);

        return new ProblemStateDBObj(this.title, initString, goalString, this.heuristic);
    }

    private String matrixToString(int[][] matrix){

        String temp = "";

        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix[row].length; col++){
                temp += matrix[row][col];
                if(row != matrix.length - 1 || col != matrix[row].length - 1){
                    temp += ",";
                }
            }
        }

        return temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{\n\t");

        sb.append(buildMatrixString(initState.getStatePosition()) + ",\n");
        sb.append("\t" + buildMatrixString(goalState.getStatePosition()) + ",\n");
        sb.append("\t" + heuristic);

        sb.append("\n}");

        return sb.toString();
    }

    /**
     * Helper method for toString
     * @return matrix as a string
     */
    private String buildMatrixString(int[][] matrix) {
        StringBuilder sb = new StringBuilder("[");

        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[row].length; col++) {

                sb.append(matrix[row][col]);

                if(row != matrix.length - 1 || col != matrix[row].length - 1){
                    sb.append(",");
                } else {
                    sb.append("]");
                }
            }
        }

        return sb.toString();
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
    public List<State> getSolutionPath() {
        return solutionPath;
    }

    public void setSolutionPath(List<State> solutionPath) {
        this.solutionPath = solutionPath;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
