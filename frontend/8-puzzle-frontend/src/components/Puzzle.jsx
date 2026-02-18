import { PuzzleTile } from "./PuzzleTile"
import { useState } from "react"
import "../static/styles/Puzzle.css"

export const Puzzle = () => {

    let cellVals = [1, 2, 3, 4, 5, 6, 7, 8, ""];
    const [cellValue, setCellValue] = useState([]);

    return(
        <>
            <div className="outer-border">
                {cellVals.map((elem, i) => {
                    return <PuzzleTile key={i} initCellVal={elem} />
                })}
            </div>
        </>
    )
}