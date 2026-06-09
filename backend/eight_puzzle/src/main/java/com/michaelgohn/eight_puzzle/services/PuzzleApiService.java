package com.michaelgohn.eight_puzzle.services;

import java.util.ArrayList;
import java.util.List;

import com.michaelgohn.eight_puzzle.models.ProblemState;
import com.michaelgohn.eight_puzzle.models.ProblemStateDBObj;
import com.michaelgohn.eight_puzzle.models.State;

public interface PuzzleApiService {
    
    public void startSolving(ArrayList<State> openList, State initState, State goalState, String heuristic);
    public ProblemState createProblemState(String title, int[][] initMatrix, int[][] goalMatrix, String heuristic);
    public List<ProblemStateDBObj> retrieveProblems();
    public ProblemStateDBObj retrieveById(Long id);
    public ProblemStateDBObj updateProblemState(Long id, String title, int[][] initMatrix, int[][] goalMatrix, String heuristic);
    public void deletePuzzle(Long id);
}
