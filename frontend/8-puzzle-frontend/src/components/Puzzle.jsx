import { PuzzleTile } from "./PuzzleTile"
import { useState } from "react"
import "../static/styles/Puzzle.css"

export const Puzzle = ({ value, size="normal" }) => {

    const [cellValues, setCellValues] = useState(value);

    return(
        <>
            <div className={`outer-border puzzle-${size}`}>
                {cellValues.map((elem, i) => {
                    return <PuzzleTile key={i} initCellVal={elem} />
                })}
            </div>
        </>
    )
}