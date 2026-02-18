import { useState } from "react"
import "../static/styles/PuzzleTile.css"

export const PuzzleTile = ({ initCellVal }) => {
    const [cellValue, setCellValue] = useState(initCellVal);

    return(
        <>
            <div className="cell">
                <p>{cellValue}</p>
            </div>
        </>
    )
}