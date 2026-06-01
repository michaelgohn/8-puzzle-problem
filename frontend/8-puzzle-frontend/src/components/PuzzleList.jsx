export const PuzzleList = () => {

    let range = [...Array(9)];
    
    return(
        <>
            {range.map((item, index) => (
                <p>Puzzle {index}</p>
            ))}
        </>
    )
};