import { InputPuzzleTile } from "./InputPuzzleTile"
import "../static/styles/Puzzle.css"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { useParams } from "react-router-dom"
import { useEffect } from "react"

export const EditPuzzle = () => {

    const { id } = useParams();
    const [puzzle, setPuzzle] = useState();
    const [initPuzzleValues, setInitPuzzleValues] = useState([]);
    const [goalPuzzleValues, setGoalPuzzleValues] = useState([]);
    const [initMatrix, setInitMatrix] = useState([]);
    const [goalMatrix, setGoalMatrix] = useState([]);
    const [heuristic, setHeuristic] = useState("");
    const [title, setTitle] = useState("");
    const [showGoalState, setShowGoalState] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        async function fetchData() {
            try {
                const response = await fetch(`http://localhost:8080/puzzle/${id}`)
    
                if(!response.ok) {
                    throw new Error(`Request failed with status ${(await response).status}`);
                }
    
                let data = await response.json();
    
                setPuzzle(data);
                console.log('Puzzle ' + puzzle);
                initializeVariables(data);
            } catch (error) {
                console.error(error);
            }
        }

        fetchData();
    }, [id]);

    function initializeVariables(data) {
        setInitPuzzleValues(data.initStatePosition.split(','));
        setGoalPuzzleValues(data.goalStatePosition.split(','));
        setInitMatrix(data.initStatePosition.split(','));
        setGoalMatrix(data.goalStatePosition.split(','));
        setHeuristic(data.heuristic);
        setTitle(data.title);
    }

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
            title: title,
            initMatrix: initMatrix,
            goalMatrix: matrix,
            heuristic: heuristic
        };

        console.log(requestBody);

        try {
            const response = await fetch(`http://localhost:8080/puzzle/${id}`, {
                method: "PUT",
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

    return(
        <>
            {
                !showGoalState && <div className="input-puzzle">
                    <div className="input-container">
                        <label htmlFor="title-input">Create Title</label>
                        <input id="title-input" type="text" defaultValue={title} onChange={e => handleTitleChange(e.target.value)}/>
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
                        <select 
                            name="heuristic-input" 
                            id="heuristic-input" 
                            value={heuristic}
                            onChange={e => handleHeuristicChange(e.target.value)}
                        >
                            <option value="manhattan-distance">Manhattan Distance</option>
                            <option value="misplaced-tiles">Misplaced Tiles</option>
                        </select>
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