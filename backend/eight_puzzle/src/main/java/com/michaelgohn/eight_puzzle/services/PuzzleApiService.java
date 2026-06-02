package com.michaelgohn.eight_puzzle.services;

import java.util.ArrayList;
import java.util.List;

import com.michaelgohn.eight_puzzle.models.ProblemState;
import com.michaelgohn.eight_puzzle.models.ProblemStateDBObj;
import com.michaelgohn.eight_puzzle.models.State;

public interface PuzzleApiService {
    
    public void startSolving(ArrayList<State> openList, State initState, State goalState, String heuristic);
    public ProblemState createProblemState(int[][] initState, int[][] goalState, String heuristic);
    public List<ProblemStateDBObj> retrieveProblems();
}
