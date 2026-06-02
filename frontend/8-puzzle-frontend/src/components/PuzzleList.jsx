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
            const response = await fetch("http://localhost:8080/puzzle/retrieve");

            if(!response.ok) {
                throw new Error(`Request failed with status ${(await response).status}`);
            }

            let data = await response.json();

            console.log(`Backend response: ${data}`);

            setProblems(data);
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
                            <div className="problem">
                                <p>{val.heuristic}</p>

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
                            </div>
                        )
                    })}
                </div>
            </div>
        </>
    )
};