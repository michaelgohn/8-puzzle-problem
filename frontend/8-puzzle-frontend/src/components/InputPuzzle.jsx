import { InputPuzzleTile } from "./InputPuzzleTile"
import "../static/styles/Puzzle.css"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { Link } from "react-router-dom"

export const InputPuzzle = () => {

    const [initPuzzleValues, setInitPuzzleValues] = useState(Array(9).fill(''));
    const [goalPuzzleValues, setGoalPuzzleValues] = useState(Array(9).fill(''));
    const [initMatrix, setInitMatrix] = useState([]);
    const [goalMatrix, setGoalMatrix] = useState([]);
    const [heuristic, setHeuristic] = useState("manhattan-distance");
    const [title, setTitle] = useState("");
    const [showGoalState, setShowGoalState] = useState(false);
    const navigate = useNavigate();

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

    function handleTitleChange(value) {
        setTitle(value);
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
        if(validation(initPuzzleValues)) {
            const matrix = buildInitMatrix();
    
            setInitMatrix(matrix);
            console.log(matrix);
            console.log(heuristic);
            setShowGoalState(true);
        }
    }

    async function handleGoalSubmit() {
        console.log(goalPuzzleValues);
        if(validation(goalPuzzleValues)){
            const matrix = buildGoalMatrix();
            setGoalMatrix(matrix);
            console.log(matrix);
    
            const requestBody = {
                title: title,
                initMatrix: initMatrix,
                goalMatrix: matrix,
                heuristic: heuristic
            };
    
            console.log(requestBody);
    
            try {
                const response = await fetch("http://localhost:8080/puzzle", {
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
    
            navigate("/");
        }
    }

    const validation = (arr) => {
        if(title.length === 0){
            alert('Title is required');
        } else if(arr.includes('')) {
            alert('All tiles must have a value');
        } else if(hasDuplicates(arr)) {
            alert('All tile values must be unique');
        } else if(valueOutOfRange(arr)) {
            alert('All tile values must be between 0 and 8 (inclusive)');
        } else {
            return true;
        }
        return false;
    }

    const hasDuplicates = (arr) => new Set(arr).size !== arr.length;

    const valueOutOfRange = (arr) => {
        return arr.some(val => val < 0 || val > 8);
    }

    const backToInit = () => setShowGoalState(false);

    return(
        <>
            {
                !showGoalState && <div className="input-puzzle">
                    <div className="input-container">
                        <label htmlFor="title-input">Create Title</label>
                        <input 
                            id="title-input"
                            type="text"
                            value={title}
                            onChange={e => handleTitleChange(e.target.value)}/>
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

                    <div className="input-container">
                        <label htmlFor="heuristic-input">Choose Hueristic</label>
                        <select name="heuristic-input" id="heuristic-input" onChange={e => handleHeuristicChange(e.target.value)}>
                            <option value="manhattan-distance">Manhattan Distance</option>
                            <option value="misplaced-tiles">Misplaced Tiles</option>
                        </select>
                    </div>

                    <div>
                        <Link to='/'>
                            <button className="btn back-btn">Back</button>
                        </Link>
                        <button onClick={handleInitSubmit} className="btn puzzle-submit-btn">Submit Puzzle</button>
                    </div>
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
                    
                    <div>
                        <button className="btn back-to-init-btn" onClick={backToInit}>Back</button>
                        <button onClick={handleGoalSubmit} className="btn puzzle-submit-btn">Submit Puzzle</button>
                    </div>
                </div>
            }
        </>
    )
}