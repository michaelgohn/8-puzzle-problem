package com.michaelgohn.eight_puzzle.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michaelgohn.eight_puzzle.dtos.PuzzleRequestDto;
import com.michaelgohn.eight_puzzle.models.ProblemState;
import com.michaelgohn.eight_puzzle.services.PuzzleApiService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@AllArgsConstructor
@RequestMapping("/puzzle")
@CrossOrigin(origins = "http://localhost:5173")
public class PuzzleApiController {
    
    private PuzzleApiService puzzleApiService;

    @PostMapping("/start")
    public void startSolving() {
        return ;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProblemState(@RequestBody PuzzleRequestDto puzzleRequest) {
        int[][] initMatrix = puzzleRequest.getInitMatrix();
        int[][] goalMatrix = puzzleRequest.getGoalMatrix();
        String heuristic = puzzleRequest.getHeuristic();

        ProblemState problemState = puzzleApiService.createProblemState(initMatrix, goalMatrix, heuristic);

        ObjectMapper mapper = new ObjectMapper();

        try {
            return ResponseEntity.ok(mapper.writeValueAsString(problemState));
        } catch (JsonProcessingException error) {
            System.out.println(error.toString());
            return ResponseEntity.badRequest().body(error.toString());
        }
    }
    
    
}
