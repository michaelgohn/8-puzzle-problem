import { PuzzleTile } from "./PuzzleTile"
import "../static/styles/Puzzle.css"

export const BlankPuzzle = () => {

    return(
        <>
            <div className="outer-border">
                {[...Array(9)].map((_, i) => {
                    return <PuzzleTile key={i} initCellVal={""} />
                })}
            </div>
        </>
    )
}