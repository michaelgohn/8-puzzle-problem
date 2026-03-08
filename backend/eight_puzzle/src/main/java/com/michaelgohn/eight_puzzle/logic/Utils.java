package com.michaelgohn.eight_puzzle.logic;
import java.util.*;

import com.michaelgohn.eight_puzzle.models.State;

public class Utils {

    // METHOD USED FOR TESTING PURPOSES
    // NOT USED IN FINAL PROJECT
    public static State createRandomState(){

        List<Integer> possibleTileVals = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
        Random rand = new Random();
        int[][] tempMatrix = new int[3][3];
        int index = 0;
        int picked = 0;

        for(int i = 0; i < tempMatrix.length; i++){
            for(int j = 0; j < tempMatrix[i].length; j++){
                index = rand.nextInt(possibleTileVals.size());
                picked = possibleTileVals.remove(index);
                tempMatrix[i][j] = picked;
            }
        }

        State newState = new State(tempMatrix);

        return newState;
    }

    public static State createUserInputState(Scanner scan, String stateType){
        List<Integer> possibleTileVals = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
        int[][] tempMatrix = new int[3][3];

        for(int i = 0; i < tempMatrix.length; i++){
            for(int j = 0; j < tempMatrix[i].length; j++){
                int value;
                while (true) {
                    System.out.println("\n" + stateType);
                    System.out.println("What value do you want at row " + i + ", column " + j + "?");
                    System.out.println("(Available values: " + possibleTileVals.toString() + ")");
                    if (scan.hasNextInt()) {
                        value = scan.nextInt();
                        if (possibleTileVals.contains(value)) {
                            break;
                        } else {
                            System.out.println("That value is not available. Choose from " + possibleTileVals);
                        }
                    } else {
                        System.out.println("Invalid input. Please enter an integer.");
                        scan.next();
                    }
                }

                tempMatrix[i][j] = value;
                int indexOfPicked = possibleTileVals.indexOf(value);
                possibleTileVals.remove(indexOfPicked);
            }
        }

        State newState = new State(tempMatrix);
        newState.print();

        return newState;
    }

    public static String acceptHeuristicChoice(Scanner scan){
        int choice;
        while(true) {
            System.out.println("\nWhich heuristic would you like to use?");
            System.out.println("1. Manhattan Distance");
            System.out.println("2. Misplaced Tiles");
            System.out.println("Enter 1 or 2: ");
            if(scan.hasNextInt()) {
                choice = scan.nextInt();
                if(choice == 1 || choice == 2){
                    break;
                } else {
                    System.out.println("Please choose 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scan.next();
            }
        }

        if(choice == 1) return "manhattanDistance";
        else return "misplacedTiles";
    }

    public static State createInitialState(Scanner scan, String stateType){
        return createUserInputState(scan, stateType);
    }

    public static State createGoalState(Scanner scan, String stateType){
        State newState = createUserInputState(scan, stateType);
        System.out.println("***** SOLVING IN PROGRESS ******");
        return newState;
    }

}
