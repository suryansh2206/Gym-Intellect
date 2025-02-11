import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

const WorkoutPlanMember = () => {
    const [workouts, setWorkouts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const userIdRedux = useSelector((state) => state.auth.userId);
    const tokenRedux = useSelector((state) => state.auth.token);
  
    const userId = userIdRedux || localStorage.getItem("userId");
    const token = tokenRedux || localStorage.getItem("token");

    useEffect(() => {
        const fetchWorkouts = async () => {
            try {
                console.log(userId);
                const response = await fetch(`http://localhost:8083/api/workouts/${userId}`,
                    {
                      method: "GET",
                      headers: {
                        "Content-Type": "application/json",
                        Authorization: `Bearer ${token}`,
                      },
                      credentials: "include",
                    });
                    
                    const data = await response.json();

                setWorkouts(data);
                console.log(data);
            } catch (err) {
                setError("Failed to fetch workouts.");
            } finally {
                setLoading(false);
            }
        };
        fetchWorkouts();
    }, [userId, token]);

    if (loading) return <div className="text-center mt-10 text-xl">Loading...</div>;
    if (error) return <div className="text-center mt-10 text-red-500">{error}</div>;

    return (
        <div className="container mx-auto p-6">
            <h1 className="text-3xl font-bold text-center mb-6">Your Workouts</h1>
            <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
                {workouts.map((workout) => (
                    <div key={workout.id} className="bg-white rounded-xl shadow-lg p-5 transform hover:scale-105 transition-transform">
                        <img className="w-full h-40 object-cover rounded-md" src={require("../../assets/workout1.jpg")} alt="Workout" />
                        <h2 className="text-xl font-bold mt-4">{workout.exerciseName}</h2>
                        <p className="text-gray-600">Sets: {workout.sets} | Reps: {workout.reps}</p>
                        <p className="text-gray-600">Duration: {workout.duration}</p>
                        <p className="text-gray-600">Rest Time: {workout.restTime}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default WorkoutPlanMember;
