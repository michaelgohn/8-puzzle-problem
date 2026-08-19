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

    private String title;
    
    @Column(length = 17)
    private String initStatePosition;

    @Column(length = 17)
    private String goalStatePosition;
    private String heuristic;

    public ProblemStateDBObj(String title, String initStatePosition, String goalStatePosition, String heuristic) {
        this.title = title;
        this.initStatePosition = initStatePosition;
        this.goalStatePosition = goalStatePosition;
        this.heuristic = heuristic;
    }

    /**
     * Converts a ProblemStateDBObj to a ProblemState object
     * @return ProblemState
     */
    public ProblemState convertToProblemState() {
        System.out.println("initStatePosition (String): " + this.initStatePosition);
        int[][] initMatrix = stringToMatrix(this.initStatePosition);
        int[][] goalMatrix = stringToMatrix(this.goalStatePosition);

        for (int[] row : initMatrix) {
            for (int col : row) {
                System.out.println("initMatrix: " + col);
            }
        }

        State initState = new State(initMatrix);
        State goalState = new State(goalMatrix);

        return new ProblemState(title, initState, goalState, null, heuristic);
    }

    /**
     * Helper function for convertToProblemState
     * @param matrixString
     * @return int[][]
     */
    public int[][] stringToMatrix(String matrixString) {
        String[] matrixStringArr = matrixString.split(",");
        int[][] stateMatrix = new int[3][3];
        
        int i = 0;
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                stateMatrix[row][col] = Integer.parseInt(matrixStringArr[i]);
                i++;
            }
        }

        return stateMatrix;
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
