import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import "./WorkoutPlanMember.css";

const WorkoutPlanMember = () => {
  const [workouts, setWorkouts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
  const userIdRedux = useSelector((state) => state.auth.userId);
  const tokenRedux = useSelector((state) => state.auth.token);

  const userId = userIdRedux || localStorage.getItem("userId");
  const token = tokenRedux || localStorage.getItem("token");

  useEffect(() => {
    const fetchWorkouts = async () => {
      try {
        console.log(userId);
        const response = await fetch(
          `http://localhost:8214/api/workouts/${userId}`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
            credentials: "include",
          }
        );

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

  if (loading)
    return <div className="text-center mt-10 text-xl">Loading...</div>;
  if (error)
    return <div className="text-center mt-10 text-red-500">{error}</div>;

  return (
    <div className="workouts-container">
      {/* Back Button */}
      <button className="back-button" onClick={() => navigate(-1)}>
        ← Back
      </button>

      <h1 className="workout-title">Your Workouts</h1>

      <div className="workout-grid">
        {workouts.map((workout) => (
          <div key={workout.id} className="workout-card">
            <img
              className="workout-image"
              src={require("../../assets/workout1.jpg")}
              alt="Workout"
            />
            <h2 className="workout-name">{workout.exerciseName}</h2>
            <p className="workout-info">
              <span className="highlight">Sets:</span> {workout.sets} |
              <span className="highlight"> Reps:</span> {workout.reps}
            </p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default WorkoutPlanMember;
