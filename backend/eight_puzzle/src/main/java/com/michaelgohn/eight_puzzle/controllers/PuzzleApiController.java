package com.michaelgohn.eight_puzzle.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michaelgohn.eight_puzzle.services.PuzzleApiService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@AllArgsConstructor
@RequestMapping("/puzzle")
public class PuzzleApiController {
    
    private PuzzleApiService puzzleApiService;

    @GetMapping("/start")
    public void startSolving() {
        return ;
    }

    @GetMapping("/create")
    public void createPuzzleState() {
        return ;
    }
    
    
}
