package com.michaelgohn.eight_puzzle.services;

import java.util.ArrayList;

import com.michaelgohn.eight_puzzle.models.ProblemState;
import com.michaelgohn.eight_puzzle.models.State;

public interface PuzzleApiService {
    
    public void startSolving(ArrayList<State> openList, State initState, State goalState, String heuristic);
    public ProblemState createProblemState(int[][] initState, int[][] goalState, String heuristic);
}
