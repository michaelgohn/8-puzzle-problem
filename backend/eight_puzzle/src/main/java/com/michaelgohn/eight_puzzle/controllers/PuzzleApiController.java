package com.michaelgohn.eight_puzzle.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michaelgohn.eight_puzzle.dtos.PuzzleRequestDto;
import com.michaelgohn.eight_puzzle.models.ProblemState;
import com.michaelgohn.eight_puzzle.services.PuzzleApiService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@AllArgsConstructor
@RequestMapping("/puzzle")
public class PuzzleApiController {
    
    private PuzzleApiService puzzleApiService;

    @PostMapping("/start")
    public void startSolving() {
        return ;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProblemState(@RequestBody PuzzleRequestDto puzzleRequest) {
        int[][] initMatrix = puzzleRequest.getInitState();
        int[][] goalMatrix = puzzleRequest.getGoalState();
        String heuristic = puzzleRequest.getHeuristic();

        ProblemState problemState = puzzleApiService.createProblemState(initMatrix, goalMatrix, heuristic);

        return ResponseEntity.ok("replace this");
    }
    
    
}
