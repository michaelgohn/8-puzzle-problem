package com.michaelgohn.eight_puzzle.services;

import java.util.ArrayList;
import java.util.List;

import com.michaelgohn.eight_puzzle.models.ProblemState;
import com.michaelgohn.eight_puzzle.models.ProblemStateDBObj;

public interface PuzzleApiService {
    
    public ProblemState solve(ProblemState problemState);
    public boolean checkIfSolvable(ProblemState problemState);
    public ProblemState createProblemState(String title, int[][] initMatrix, int[][] goalMatrix, String heuristic);
    public List<ProblemStateDBObj> getProblems();
    public ProblemStateDBObj getById(Long id);
    public ProblemStateDBObj updateProblemState(Long id, String title, int[][] initMatrix, int[][] goalMatrix, String heuristic);
    public void deletePuzzle(Long id);
}
