import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import "./DietPlanMember.css"

const DietPlanMember = () => {
    const [dietPlans, setDietPlans] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();
    
    const userIdRedux = useSelector((state) => state.auth.userId);
    const tokenRedux = useSelector((state) => state.auth.token);
  
    const userId = userIdRedux || localStorage.getItem("userId");
    const token = tokenRedux || localStorage.getItem("token");

    useEffect(() => {
        const fetchDietPlans = async () => {
            try {
                const response = await fetch(`http://localhost:8083/api/member/${userId}/diet-plan`, {
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: `Bearer ${token}`,
                    },
                    withCredentials: true,
                });
                const data = await response.json();
                setDietPlans(data);
            } catch (err) {
                setError("Failed to fetch diet plans.");
            } finally {
                setLoading(false);
            }
        };
        fetchDietPlans();
    }, [userId, token]);

    if (loading) return <div className="text-center mt-10 text-xl">Loading...</div>;
    if (error) return <div className="text-center mt-10 text-red-500">{error}</div>;

    return (
    <div className="workouts-container">
        {/* Back Button */}
        <button className="back-button" onClick={() => navigate(-1)}>
            ← Back
        </button>

        <h1 className="workout-title">Diet Plan</h1>

        <div className="workout-grid">
            {dietPlans.map((dietPlan) => (
                <div key={dietPlan.dietPlanId} className="workout-card">
                    <div className="image-container">
                        <img 
                            className="workout-image"
                            src={require("../../assets/diet.avif")} 
                            alt="Diet Plan"
                        />
                    </div>
                    <h2 className="workout-name">Good for {dietPlan.workoutPlan.planName}</h2>
                    <p className="workout-info">
                        <span className="highlight">Contents:</span> {dietPlan.description}
                    </p>
                </div>
            ))}
        </div>
    </div>
);

};

export default DietPlanMember;
