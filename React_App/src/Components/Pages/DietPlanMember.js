import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import axios from "axios";

const DietPlanMember = () => {
    const [dietPlans, setDietPlans] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    
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
                setDietPlans(response.data);
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
        <div className="container mx-auto p-6">
            <h1 className="text-3xl font-bold text-center mb-6">Your Diet Plan</h1>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
                {dietPlans.map((diet) => (
                    <div key={diet.diet_id} className="bg-white rounded-xl shadow-lg p-5 transform hover:scale-105 transition-transform">
                        <img className="w-full h-40 object-cover rounded-md" src={require("../../assets/diet.jpg")} alt="Diet Plan" />
                        <h2 className="text-xl font-bold mt-4">{diet.meal_type}</h2>
                        <p className="text-gray-600">{diet.description}</p>
                        <p className="text-gray-600 font-bold">Calories: {diet.calories}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default DietPlanMember;
