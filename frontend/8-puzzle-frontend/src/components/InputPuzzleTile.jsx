import { useState } from "react"
import "../static/styles/InputPuzzleTile.css"

export const InputPuzzleTile = () => {
    const [cellValue, setCellValue] = useState("");

    function handleChange(newValue){
        setCellValue(newValue)
    }
    
    return(
        <div className="cell">
            <input
                type="text"
                maxLength="1"
                value={cellValue}
                onChange={(e) => handleChange(e.target.value)}
                className="tile-input"
            />
        </div>
    )
};
