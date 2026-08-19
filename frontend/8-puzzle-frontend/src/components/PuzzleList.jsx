import { useEffect, useState } from "react";
import { Puzzle } from "./Puzzle";
import { Link } from "react-router-dom";
import "../static/styles/PuzzleList.css"

export const PuzzleList = () => {

    const [problems, setProblems] = useState([]);
    const [cellVals, setCellVals] = useState([]);
    let range = [...Array(9)];

    useEffect(() => {
        retrievePuzzles();
    }, []);

    async function retrievePuzzles() {
        try {
            const response = await fetch("http://localhost:8080/puzzle");

            if(!response.ok) {
                throw new Error(`Request failed with status ${(await response).status}`);
            }

            let data = await response.json();

            setProblems(data);
        } catch (error) {
            console.error(error);
        }
    }

    async function deletePuzzle(id) {
        try {
            const response = await fetch(`http://localhost:8080/puzzle/${id}`, {
                method: "DELETE"
            });

            if(!response.ok) {
                console.log(`Request failed with status: ${(await response).status}`)
            }

            retrievePuzzles();
        } catch (error) {
            console.error(error);
        }
    }
    
    return(
        <>
            <div className="container">
                <Link to="/">
                    <button className="btn">Go Back To Landing Page</button>
                </Link>
                <div className="problemList">
                    {problems.map(val => {
                        let initState = val.initStatePosition.split(',');
                        let goalState = val.goalStatePosition.split(',');

                        return(
                            <div className="problem" key={val.id}>
                                <p>{val.title}</p>

                                <div className="states">
                                    <div className="state">
                                        <p>Initial State</p>
                                        <Puzzle value={initState} size="small" />
                                    </div>
                                    <div className="state">
                                        <p>Goal State</p>
                                        <Puzzle value={goalState} size="small" />
                                    </div>
                                </div>

                                {
                                    val.heuristic === 'manhattan-distance' ? (
                                        <p>Heuristic: Manhattan Distance</p>
                                    ) : (
                                        <p>Heuristic: Misplaced Tiles</p>
                                    )
                                }

                                <div className="btns">
                                    <Link to={`/start/${val.id}`}>
                                        <button className="btn btn-start">Start</button>
                                    </Link>
                                    <Link to={`/edit/${val.id}`}>
                                        <button className="btn btn-edit">Edit</button>
                                    </Link>
                                    <button className="btn btn-delete" onClick={() => deletePuzzle(val.id)}>Delete</button>
                                </div>
                            </div>
                        )
                    })}
                </div>
            </div>
        </>
    )
};