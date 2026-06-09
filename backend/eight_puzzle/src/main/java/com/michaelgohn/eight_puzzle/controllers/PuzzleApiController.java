package com.michaelgohn.eight_puzzle.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michaelgohn.eight_puzzle.dtos.PuzzleRequestDto;
import com.michaelgohn.eight_puzzle.models.ProblemState;
import com.michaelgohn.eight_puzzle.models.ProblemStateDBObj;
import com.michaelgohn.eight_puzzle.services.PuzzleApiService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        String title = puzzleRequest.getTitle();

        ProblemState problemState = puzzleApiService.createProblemState(title, initMatrix, goalMatrix, heuristic);

        ObjectMapper mapper = new ObjectMapper();

        try {
            return ResponseEntity.ok(mapper.writeValueAsString(problemState));
        } catch (JsonProcessingException error) {
            System.out.println(error.toString());
            return ResponseEntity.badRequest().body(error.toString());
        }
    }
    
    @GetMapping("/retrieve")
    public ResponseEntity<List<ProblemStateDBObj>> retrieveProblemList() {
        List<ProblemStateDBObj> puzzleList = puzzleApiService.retrieveProblems();

        return ResponseEntity.ok(puzzleList);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<ProblemStateDBObj> retrieveProblemById(@PathVariable Long id) {
        ProblemStateDBObj problem = puzzleApiService.retrieveById(id);
        return ResponseEntity.ok(problem);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ProblemStateDBObj> updatePuzzle(@PathVariable Long id, @RequestBody PuzzleRequestDto requestBody) {
        String title = requestBody.getTitle();
        int[][] initMatrix = requestBody.getInitMatrix();
        int[][] goalMatrix = requestBody.getGoalMatrix();
        String heuristic = requestBody.getHeuristic();

        ProblemStateDBObj updatedPuzzle = puzzleApiService.updateProblemState(id, title, initMatrix, goalMatrix, heuristic);

        return ResponseEntity.ok(updatedPuzzle);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePuzzle(@PathVariable Long id) {
        System.out.println("id value: " + id);
        puzzleApiService.deletePuzzle(id);

        return ResponseEntity.noContent().build();
    }
}
