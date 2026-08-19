package com.michaelgohn.eight_puzzle.models;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SolutionState {
    
    private long id;
    private String title;
    private int[][] initState;
    private int[][] goalState;
    private List<int[][]> solutionPath;
    private String heuristic;

    public SolutionState(long id, String title, int[][] initState, int[][] goalState, String heuristic) {
        this.id = id;
        this.title = title;
        this.initState = initState;
        this.goalState = goalState;
        this.solutionPath = new ArrayList<int[][]>();
        this.heuristic = heuristic;
    }

    public void populateSolutionPath(ProblemState problemState) {
        for(int i = problemState.getSolutionPath().size() - 1; i >= 0; i--) {
            solutionPath.add(problemState.getSolutionPath().get(i).getStatePosition());
        }
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
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
    public List<int[][]> getSolutionPath() {
        return solutionPath;
    }
    public void setSolutionPath(List<int[][]> solutionPath) {
        this.solutionPath = solutionPath;
    }
    public String getHeuristic() {
        return heuristic;
    }
    public void setHeuristic(String heuristic) {
        this.heuristic = heuristic;
    }
}
