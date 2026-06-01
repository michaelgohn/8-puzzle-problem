import { useState } from "react"
import "../static/styles/InputPuzzleTile.css"

export const InputPuzzleTile = ({ value, onChange }) => {
    
    return(
        <div className="cell">
            <input
                type="text"
                maxLength="1"
                value={value}
                onChange={(e) => onChange(e.target.value)}
                className="tile-input"
            />
        </div>
    )
};
