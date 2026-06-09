package com.michaelgohn.eight_puzzle.services.impl;

import com.michaelgohn.eight_puzzle.models.ProblemState;
import com.michaelgohn.eight_puzzle.models.ProblemStateDBObj;
import com.michaelgohn.eight_puzzle.models.State;
import com.michaelgohn.eight_puzzle.repository.ProblemStateDBObjRepository;
import com.michaelgohn.eight_puzzle.services.PuzzleApiService;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PuzzleApiServiceImpl implements PuzzleApiService {

    private final ProblemStateDBObjRepository problemStateRepository;

    @Override
    public void startSolving(ArrayList<State> openList, State initState, State goalState, String heuristic) {
        // Frontend
            // Create init state
            // Create goal state
            // Choose heuristic

        
    }

    @Override
    public ProblemState createProblemState(String title, int[][] initMatrix, int[][] goalMatrix, String heuristic) {
        State initState = new State(initMatrix);
        State goalState = new State(goalMatrix);
        
        ProblemState ps = new ProblemState(title, initState, goalState, heuristic);

        ProblemStateDBObj psDBObj = ps.convertProblemState();

        problemStateRepository.save(psDBObj);
        
        return ps;
    }

    @Override
    public List<ProblemStateDBObj> retrieveProblems() {
        return problemStateRepository.findAll();
    }

    @Override
    public ProblemStateDBObj retrieveById(Long id) {
        return problemStateRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Puzzle not found with id " + id));
    }

    @Override
    public ProblemStateDBObj updateProblemState(Long id, String title, int[][] initMatrix, int[][] goalMatrix, String heuristic) {
        State initState = new State(initMatrix);
        State goalState = new State(goalMatrix);
        ProblemState updatedState = new ProblemState(title, initState, goalState, heuristic);

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
