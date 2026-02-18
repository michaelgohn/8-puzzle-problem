import { InputPuzzleTile } from "./InputPuzzleTile"
import "../static/styles/Puzzle.css"

export const InputPuzzle = () => {

    return(
        <>
            <div className="outer-border">
                {[...Array(9)].map((_, i) => {
                    return <InputPuzzleTile key={i} />
                })}
            </div>
        </>
    )
}