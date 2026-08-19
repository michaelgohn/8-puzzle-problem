package com.michaelgohn.eight_puzzle.services.impl;

import com.michaelgohn.eight_puzzle.models.ProblemState;
import com.michaelgohn.eight_puzzle.models.ProblemStateDBObj;
import com.michaelgohn.eight_puzzle.models.State;
import com.michaelgohn.eight_puzzle.repository.ProblemStateDBObjRepository;
import com.michaelgohn.eight_puzzle.services.PuzzleApiService;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PuzzleApiServiceImpl implements PuzzleApiService {

    private final ProblemStateDBObjRepository problemStateRepository;

    @Override
    public ProblemState solve(ProblemState problemState) {

        State initState = problemState.getInitState();
        State goalState = problemState.getGoalState();

        ArrayList<State> openList = new ArrayList<>();
        ArrayList<State> closedList = new ArrayList<>();
        ArrayList<State> solutionPath = new ArrayList<>();
        openList.add(initState);

        State currState = initState;
        boolean foundSolution = false;

        do {
            // System.out.println("\nFVAL: " + currState.getFVal() + "\n");
            foundSolution = currState.expand(openList, closedList, goalState);

            if(foundSolution){
                solutionPath.add(currState);
                while(currState.getParentState() != null){
                    currState = currState.getParentState();
                    solutionPath.add(currState);
                }
                break;
            }

            openList.sort(Comparator.comparingInt(State::getFVal));
            currState = openList.get(0);
        } while(!foundSolution && !openList.isEmpty());

        System.out.println("***** SOLUTION FOUND *****");
        System.out.println("\nInitial State:\n");
        initState.print();
        System.out.println("\nGoal State:\n");
        goalState.print();
        System.out.println("\nTotal number of nodes generated: " + (openList.size() + closedList.size()));
        System.out.println("Total number of nodes expanded: " + closedList.size() + "\n");

        System.out.println("***** SOLUTION PATH *****");
        for (int i = 0; i < solutionPath.size(); i++) {
            solutionPath.get(i).print();
            if(i != solutionPath.size() - 1){
                System.out.println("\n");
                System.out.println("\t^");
                System.out.println("\t|");
                System.out.println("\t|");
                System.out.println("\t|");
                System.out.println("\n");
            }
        }

        ProblemState solutionState = new ProblemState(
            problemState.getTitle(),
            problemState.getInitState(), 
            problemState.getGoalState(),
            solutionPath,
            problemState.getHeuristic()
        );

        return solutionState;
    }

    @Override
    public boolean checkIfSolvable(ProblemState problemState){

        System.out.println("Title: " + problemState.getTitle()); // debug
        
        int[] flatInitState = new int[8];
        int flatInitStateIndex = 0;

        State initState = problemState.getInitState();
        State goalState = problemState.getGoalState();

        for(int i = 0; i < initState.getStatePosition().length; i++){
            for(int j = 0; j < initState.getStatePosition()[i].length; j++){
                System.out.println("Value at i (" + i + "), j (" + j + "): " + initState.getStatePosition()[i][j]); //debug
                if(initState.getStatePosition()[i][j] == 0){
                    System.out.println("Continued: " + flatInitStateIndex); // debug
                    continue;
                }
                
                System.out.println("Not Continued: " + flatInitStateIndex); // debug
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

    private int countInversions(int[] arr){

        int count = 0;
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = i + 1; j < arr.length; j++){
                if(arr[i] > arr[j]) count++;
            }
        }

        return count;
    }

    @Override
    public ProblemState createProblemState(String title, int[][] initMatrix, int[][] goalMatrix, String heuristic) {
        State initState = new State(initMatrix);
        State goalState = new State(goalMatrix);
        
        ProblemState ps = new ProblemState(title, initState, goalState, null, heuristic);

        ProblemStateDBObj psDBObj = ps.convertProblemState();

        problemStateRepository.save(psDBObj);
        
        return ps;
    }

    @Override
    public List<ProblemStateDBObj> getProblems() {
        return problemStateRepository.findAll();
    }

    @Override
    public ProblemStateDBObj getById(Long id) {
        return problemStateRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Puzzle not found with id " + id));
    }

    @Override
    public ProblemStateDBObj updateProblemState(Long id, String title, int[][] initMatrix, int[][] goalMatrix, String heuristic) {
        State initState = new State(initMatrix);
        State goalState = new State(goalMatrix);
        ProblemState updatedState = new ProblemState(title, initState, goalState, null, heuristic);

        ProblemStateDBObj psDBObj = updatedState.convertProblemState();

        ProblemStateDBObj originalPSDBObj = problemStateRepository.findById(id)
                .orElseThrow(() -> new Error("Problem with id: " + id + " not found"));

        originalPSDBObj.setTitle(psDBObj.getTitle());
        originalPSDBObj.setInitStatePosition(psDBObj.getInitStatePosition());
        originalPSDBObj.setGoalStatePosition(psDBObj.getGoalStatePosition());
        originalPSDBObj.setHeuristic(psDBObj.getHeuristic());

        problemStateRepository.save(originalPSDBObj);

        return originalPSDBObj;
    }
    
    @Override
    public void deletePuzzle(Long id) {
        problemStateRepository.deleteById(id);
    }
}
