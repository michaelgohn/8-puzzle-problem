package com.michaelgohn.eight_puzzle.models;
import java.util.*;

import com.michaelgohn.eight_puzzle.logic.Coordinates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import static java.lang.Math.abs;

@Entity
public class State {

    private State parentState;
    private ArrayList<State> childStates;
    private int[][] statePosition;
    private int gVal;
    private int hVal;
    private int fVal;
    private String chosenHeuristic;

    @Id
    @GeneratedValue
    private Long id;

    public State(){
        this.parentState = null;
        this.childStates = new ArrayList<>();
        this.statePosition = null;
        this.gVal = 0;
        this.hVal = 0;
        this.fVal = 0;
        this.chosenHeuristic = "";
    }

    public State(int[][] statePosition){
        this.parentState = null;
        this.childStates = new ArrayList<>();
        this.statePosition = statePosition;
        this.gVal = 0;
        this.hVal = 0;
        this.fVal = 0;
        this.chosenHeuristic = "";
    }

    public State(State parentState, int[][] statePosition){
        this.parentState = parentState;
        this.childStates = new ArrayList<>();
        this.statePosition = statePosition;
        this.gVal = 0;
        this.hVal = 0;
        this.fVal = 0;
        this.chosenHeuristic = "";
    }

    public State(State parentState, ArrayList<State> childStates, int[][] statePosition, int gVal, int hVal, int fVal, String chosenHeuristic){
        this.parentState = parentState;
        this.childStates = childStates;
        this.statePosition = statePosition;
        this.gVal = gVal;
        this.hVal = hVal;
        this.fVal = fVal;
        this.chosenHeuristic = chosenHeuristic;
    }

    public boolean expand(ArrayList<State> openList, ArrayList<State> closedList, State goalState){

        boolean solutionFound = checkSolution(goalState);
        if(solutionFound) return solutionFound;

        Coordinates blankCoordinates = findBlank();
        int[][] potentialStatePosition = new int[statePosition.length][];

        for(int i = 0; i < 4; i++){

            for(int j = 0; j < statePosition.length; j++){
                potentialStatePosition[j] = statePosition[j].clone();
            }

            String[] moveOptions = {"up", "down", "left", "right"};
            int blankVal = 0;

            try{
                switch (moveOptions[i]) {
                    case "up":
                        potentialStatePosition[blankCoordinates.getRow()][blankCoordinates.getCol()] = statePosition[blankCoordinates.getRow() + 1][blankCoordinates.getCol()];
                        potentialStatePosition[blankCoordinates.getRow() + 1][blankCoordinates.getCol()] = blankVal;
                        break;

                    case "down":
                        potentialStatePosition[blankCoordinates.getRow()][blankCoordinates.getCol()] = statePosition[blankCoordinates.getRow() - 1][blankCoordinates.getCol()];
                        potentialStatePosition[blankCoordinates.getRow() - 1][blankCoordinates.getCol()] = blankVal;
                        break;

                    case "left":
                        potentialStatePosition[blankCoordinates.getRow()][blankCoordinates.getCol()] = statePosition[blankCoordinates.getRow()][blankCoordinates.getCol() + 1];
                        potentialStatePosition[blankCoordinates.getRow()][blankCoordinates.getCol() + 1] = blankVal;
                        break;

                    case "right":
                        potentialStatePosition[blankCoordinates.getRow()][blankCoordinates.getCol()] = statePosition[blankCoordinates.getRow()][blankCoordinates.getCol() - 1];
                        potentialStatePosition[blankCoordinates.getRow()][blankCoordinates.getCol() - 1] = blankVal;
                        break;
                
                    default:
                        break;
                }

                int[][] newStatePosition = new int[potentialStatePosition.length][];
                for(int j = 0; j < potentialStatePosition.length; j++){
                    newStatePosition[j] = potentialStatePosition[j].clone();
                }
                State newState = new State(this, newStatePosition);
                newState.fValCalc(chosenHeuristic, goalState);
                this.childStates.add(newState);
            } catch(IndexOutOfBoundsException e){
                // NO ACTION NEEDED
            } catch(Exception e){
                System.out.println(e);
            }
        }

        openList.addAll(this.childStates);
        closedList.add(this);
        openList.remove(0);
        return solutionFound;
    }

    public int gValCalc(){
        int gVal = this.parentState.getGVal() + 1;
        this.setGVal(gVal);
        return gVal;
    }

    public int manDistCalc(State goalState){
        int manDist = 0;

        for(int row1 = 0; row1 < this.statePosition.length; row1++){
            for(int col1 = 0; col1 < this.statePosition[row1].length; col1++){
                for(int row2 = 0; row2 < goalState.statePosition.length; row2++){
                    for(int col2 = 0; col2 < 3; col2++){
                        if(this.statePosition[row1][col1] == goalState.statePosition[row2][col2] && this.statePosition[row1][col1] != 0){
                            manDist += (abs(row1 - row2) + abs(col1 - col2));
                            col2 = goalState.statePosition[row2].length;
                            row2 = goalState.statePosition.length;
                        }
                    }
                }
            }
        }

        this.setHVal(manDist);
        return manDist;
    }

    public int misplacedTilesCalc(State goalState){
        int numMisplacedTiles = 0;

        for(int row = 0; row < statePosition.length; row++){
            for(int col = 0; col < statePosition[row].length; col++){
                if(statePosition[row][col] != goalState.statePosition[row][col] && statePosition[row][col] != 0)
                    numMisplacedTiles++;
            }
        }

        this.setHVal(numMisplacedTiles);
        return numMisplacedTiles;
    }

    public int fValCalc(String chosenHeuristic, State goalState){
        int fVal = 0;
        if(chosenHeuristic.equalsIgnoreCase("manhattanDistance")){
            fVal = gValCalc() + manDistCalc(goalState);
            this.setFVal(fVal);
            return fVal;
        }
        else {
            fVal = gValCalc() + misplacedTilesCalc(goalState);
            this.setFVal(fVal);
            return fVal;
        }
    }

    private Coordinates findBlank(){
        Coordinates blankCoordinates = new Coordinates();

        for(int row = 0; row < statePosition.length; row++){
            for(int col = 0; col < statePosition[row].length; col++){
                if(statePosition[row][col] == 0){
                    blankCoordinates.setRow(row);
                    blankCoordinates.setCol(col);
                    return blankCoordinates;
                }
            }
        }

        return null;
    }

    private boolean checkSolution(State goalState){
        boolean isMatch = true;

        for(int row = 0; row < statePosition.length; row++){
            for(int col = 0; col < statePosition[row].length; col++){
                if(statePosition[row][col] != goalState.statePosition[row][col])
                    isMatch = false;
            }
        }

        return isMatch;
    }

    public State getParentState(){
        return this.parentState;
    }

    public ArrayList<State> getChildStates(){
        return this.childStates;
    }

    public int[][] getStatePosition(){
        return this.statePosition;
    }

    public int getGVal(){
        return this.gVal;
    }

    public int getHVal(){
        return this.hVal;
    }

    public int getFVal(){
        return this.fVal;
    }

    public void setParentState(State parentState){
        this.parentState = parentState;
    }

    public void setChildStates(ArrayList<State> childStates){
        this.childStates = childStates;
    }

    public void setStatePosition(int[][] statePosition){
        this.statePosition = statePosition;
    }

    public void setGVal(int gVal){
        this.gVal = gVal;
    }

    public void setHVal(int hVal){
        this.hVal = hVal;
    }

    public void setFVal(int fVal){
        this.fVal = fVal;
    }

    public void print(){
        for(int i = 0; i < statePosition.length; i++){
            System.out.print("[");
            for(int j = 0; j < statePosition[i].length; j++){
                if(j != 2) System.out.print(" " + statePosition[i][j] + " ");
                else System.out.print(" " + statePosition[i][j] + " ]");
            }
            System.out.println();
        }
    }
}
