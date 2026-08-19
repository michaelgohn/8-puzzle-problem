import { useLocation } from "react-router-dom"
import { Puzzle } from "./Puzzle";
import { Link } from "react-router-dom";
import "../static/styles/SolvedPage.css"

export const SolvedPage = () => {

    const location = useLocation();

    return (
        <>
            <div className="container">
                <div className="solution-path">
                    {location.state.data.solutionPath.map((val, index) => {
                        let temp = [];
                        for(let i = 0; i < val.length; i++){
                            for(let j = 0; j < val[i].length; j++){
                                temp.push(val[i][j]);
                            }
                        }

                        return (
                            <div className="problem-state" key={index}>
                                <p className="state-position">
                                    {index === 0
                                        ? "Initial State -->"
                                        : index === location.state.data.solutionPath.length - 1
                                            ? "Goal State"
                                            : "\u00A0"}
                                </p>
                                < Puzzle value={temp} size="small" />
                            </div>
                        )
                    })}
                </div>
                <div className="go-back-btn">
                    <Link to='/'>
                        <button className="go-home btn">Back</button>
                    </Link>
                </div>
            </div>
        </>
    )
}