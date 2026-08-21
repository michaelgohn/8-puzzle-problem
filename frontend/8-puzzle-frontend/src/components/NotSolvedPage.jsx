// import { useLocation } from "react-router-dom"
import { Link } from "react-router-dom"

// const location = useLocation();

export const NotSolvedPage = () => {

    return(
        <>
            <div className="container">
                <h1>Solution not possible for given initial and goal states</h1>
                <Link to='/'>
                    <button className="btn back-to-index">Back</button>
                </Link>
            </div>
        </>
    )
}