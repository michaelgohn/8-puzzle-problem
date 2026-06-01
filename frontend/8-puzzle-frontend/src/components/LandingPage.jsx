import { Link } from "react-router-dom"
import "../static/styles/LandingPage.css"

export const LandingPage = () =>{
    return(
        <div className="landing-container">
            <Link to="/create">
                <button className="btn">Create New Problem State</button>
            </Link>
            <Link to="/view">
                <button className="btn">View Existing Problem States</button>
            </Link>
        </div>
    )
};