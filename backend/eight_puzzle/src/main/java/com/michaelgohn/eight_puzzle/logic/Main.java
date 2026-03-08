package com.michaelgohn.eight_puzzle.logic;
import java.util.*;

import com.michaelgohn.eight_puzzle.models.State;

public class Main {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        String chosenHeuristic = Utils.acceptHeuristicChoice(scan); // depreciated
        State initState = Utils.createInitialState(scan, "Initial State");
        State goalState = Utils.createGoalState(scan, "Goal State");

        scan.close();

        if(Utils.checkIfSolvable(initState, goalState)){
            ArrayList<State> openList = new ArrayList<>();
            ArrayList<State> closedList = new ArrayList<>();
            ArrayList<State> solutionPath = new ArrayList<>();
            openList.add(initState);
    
            State currState = initState;
            boolean foundSolution = false;
    
            do {
                // System.out.println("\nFVAL: " + currState.getFVal() + "\n");
                foundSolution = currState.expand(openList, closedList, goalState);
    
                if(foundSolution){
                    solutionPath.add(currState);
                    while(currState.getParentState() != null){
                        currState = currState.getParentState();
                        solutionPath.add(currState);
                    }
                    break;
                }
    
                openList.sort(Comparator.comparingInt(State::getFVal));
                currState = openList.get(0);
            } while(!foundSolution && !openList.isEmpty());
    
            System.out.println("***** SOLUTION FOUND *****");
            System.out.println("\nInitial State:\n");
            initState.print();
            System.out.println("\nGoal State:\n");
            goalState.print();
            System.out.println("\nTotal number of nodes generated: " + (openList.size() + closedList.size()));
            System.out.println("Total number of nodes expanded: " + closedList.size() + "\n");
    
            System.out.println("***** SOLUTION PATH *****");
            for (int i = 0; i < solutionPath.size(); i++) {
                solutionPath.get(i).print();
                if(i != solutionPath.size() - 1){
                    System.out.println("\n");
                    System.out.println("\t^");
                    System.out.println("\t|");
                    System.out.println("\t|");
                    System.out.println("\t|");
                    System.out.println("\n");
                }
            }
        } else {
            System.out.println("Solution not possible for given initial and goal states.");
            System.out.println("\nInitial State");
            initState.print();
            System.out.println("\nGoal State");
            goalState.print();
        }
    }
}