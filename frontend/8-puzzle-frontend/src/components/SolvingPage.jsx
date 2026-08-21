import { useEffect } from "react"
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";

export const SolvingPage = () => {

    const { id } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        handleSolveRequest();
    }, []);

    async function handleSolveRequest() {
        try {
            const response = await fetch(`http://localhost:8080/puzzle/start/${id}`);

            if(response.status === 422){
                const errorMessage = await response.text();
                console.log(errorMessage);
                navigate('/not-solved');
                return;
            }

            if(!response.ok){
                throw new Error(`HTTP Status: ${response.status}`);
            }
            
            const data = await response.json();
            console.log(JSON.stringify(data, null, 2));
            navigate('/solved', {
                state: {
                    data,
                }
            });
        } catch (error) {
            console.error(error);
        }
    }
    
    return(
        <h1 className="solving-header">Solving in progress...</h1>
    )
}