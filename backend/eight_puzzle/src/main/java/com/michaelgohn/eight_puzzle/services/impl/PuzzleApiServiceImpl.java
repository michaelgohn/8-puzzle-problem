package com.michaelgohn.eight_puzzle.services.impl;

import com.michaelgohn.eight_puzzle.models.ProblemState;
import com.michaelgohn.eight_puzzle.models.State;
import com.michaelgohn.eight_puzzle.services.PuzzleApiService;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class PuzzleApiServiceImpl implements PuzzleApiService {

    @Override
    public void startSolving(ArrayList<State> openList, State initState, State goalState, String heuristic) {
        // Frontend
            // Create init state
            // Create goal state
            // Choose heuristic

        
    }

    @Override
    public ProblemState createProblemState(int[][] initMatrix, int[][] goalMatrix, String heuristic) {
        State initState = new State(initMatrix);
        State goalState = new State(goalMatrix);

        return new ProblemState(initState, goalState, heuristic);
    }
    
}
