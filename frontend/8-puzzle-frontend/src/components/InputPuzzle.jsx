import { InputPuzzleTile } from "./InputPuzzleTile"
import "../static/styles/Puzzle.css"
import { useState } from "react"

export const InputPuzzle = () => {

    const [initPuzzleValues, setInitPuzzleValues] = useState(Array(9).fill(''));
    const [goalPuzzleValues, setGoalPuzzleValues] = useState(Array(9).fill(''));
    const [initMatrix, setInitMatrix] = useState([]);
    const [goalMatrix, setGoalMatrix] = useState([]);
    const [heuristic, setHeuristic] = useState("");
    const [showGoalState, setShowGoalState] = useState(false);

    function handleInitTileChange(index, value) {
        let updatedValues = [...initPuzzleValues];
        updatedValues[index] = value;
        setInitPuzzleValues(updatedValues);
    }

    function handleGoalTileChange(index, value) {
        let updatedValues = [...goalPuzzleValues];
        updatedValues[index] = value;
        setGoalPuzzleValues(updatedValues);
    }

    function handleHeuristicChange(value) {
        setHeuristic(value);
    }

    function buildInitMatrix() {
        return [
            initPuzzleValues.slice(0, 3),
            initPuzzleValues.slice(3, 6),
            initPuzzleValues.slice(6, 9)
        ];
    }

    function buildGoalMatrix() {
        return [
            goalPuzzleValues.slice(0, 3),
            goalPuzzleValues.slice(3, 6),
            goalPuzzleValues.slice(6, 9)
        ]
    }

    function handleInitSubmit() {
        const matrix = buildInitMatrix();

        setInitMatrix(matrix);
        console.log(matrix);
        console.log(heuristic);
        setShowGoalState(true);
    }

    async function handleGoalSubmit() {
        const matrix = buildGoalMatrix();
        setGoalMatrix(matrix);
        console.log(matrix);

        const requestBody = {
            initMatrix: initMatrix,
            goalMatrix: matrix,
            heuristic: heuristic
        };

        console.log(requestBody);

        try {
            const response = await fetch("http://localhost:8080/puzzle/create", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(requestBody)
            });

            if(!response.ok){
                throw new Error(`Request failed with status ${(await response).status}`);
            }

            const data = await response.json();
            console.log(`Backend response: ${data}`);
        } catch (error) {
            console.error(`Error sending puzzle: ${error}`);
        };
    }

    return(
        <>
            {
                !showGoalState && <div className="input-puzzle">
                    <div className="heuristic-container">
                        <label htmlFor="heuristic-input">Choose Hueristic</label>
                        <input id="heuristic-input" type="text" onChange={e => handleHeuristicChange(e.target.value)}/>
                    </div>

                    <div className="outer-border">
                        {
                            initPuzzleValues.map((value, i) => (
                                <InputPuzzleTile 
                                    key={i}
                                    value={value}
                                    onChange={(newValue) => handleInitTileChange(i, newValue)}
                                />
                            ))
                        }
                    </div>

                    <button onClick={handleInitSubmit} className="btn puzzle-submit-btn">Submit Puzzle</button>
                </div>
            }

            {
                showGoalState && <div className="goal-puzzle">
                    <div className="outer-border">
                        {
                            goalPuzzleValues.map((value, i) => (
                                <InputPuzzleTile 
                                    key={i}
                                    value={value}
                                    onChange={(newValue) => handleGoalTileChange(i, newValue)}
                                />
                            ))
                        }
                    </div>

                    <button onClick={handleGoalSubmit} className="btn puzzle-submit-btn">Submit Puzzle</button>
                </div>
            }
        </>
    )
}