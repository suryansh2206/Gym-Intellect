import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import "./WorkoutRoutine.css";

const WorkoutRoutine = () => {
  const [formData, setFormData] = useState({
    exerciseName: "",
    sets: "",
    reps: "",
  });

  const [successMessage, setSuccessMessage] = useState(""); // State for success message
  const [workouts, setWorkouts] = useState([]); // State to store workouts
  const [workoutPlans, setWorkoutPlans] = useState([]); // State to store workout plans
  const [selectedWorkoutPlan, setSelectedWorkoutPlan] = useState(""); // State for selected workout plan
  const [selectedWorkouts, setSelectedWorkouts] = useState([]); // State for selected workouts

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    // Handle form submission logic here
    console.log("Form submitted:", formData);

    try {
      console.log("Data being sent to POST request:", formData); // Log the data being sent
      const response = await fetch("http://localhost:8213/api/workouts", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error("Failed to add workout");
      }

      const newWorkout = await response.json();
      setWorkouts([...workouts, newWorkout]); // Add the new workout to the existing list

      setSuccessMessage("Workout routine added successfully!");

      // Optionally, clear the form data after successful submission
      setFormData({
        exerciseName: "",
        sets: "",
        reps: "",
      });
    } catch (error) {
      console.error("Error adding workout:", error);
    }
  };

  useEffect(() => {
    const fetchWorkouts = async () => {
      try {
        const response = await fetch("http://localhost:8213/api/workouts");
        if (response.ok) {
          const data = await response.json();
          console.log("Fetched Workouts:", data); // Log the fetched workouts
          setWorkouts(data);
        } else {
          throw new Error("Failed to fetch workouts");
        }
      } catch (error) {
        console.error("Error fetching workouts:", error);
      }
    };

    fetchWorkouts();
  }, []);

  useEffect(() => {
    const fetchWorkoutPlans = async () => {
      try {
        const response = await fetch("http://localhost:8212/api/member/workout-plans", {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
        });
        if (response.ok) {
          const data = await response.json();
          setWorkoutPlans(data);
          console.log("Workout Plans:", data);
        } else {
          throw new Error("Failed to fetch workout plans");
        }
      } catch (error) {
        console.error("Error fetching workout plans:", error);
      }
    };

    fetchWorkoutPlans();
  }, []);

  const handleWorkoutPlanChange = (e) => {
    setSelectedWorkoutPlan(e.target.value);
  };

  const handleWorkoutSelection = (e, workoutId) => {
    if (e.target.checked) {
      setSelectedWorkouts([...selectedWorkouts, workoutId]);
    } else {
      setSelectedWorkouts(selectedWorkouts.filter((id) => id !== workoutId));
    }
  };

  const handleAssignWorkouts = () => {
    // Handle the logic to assign selected workouts to the selected workout plan
    console.log("Assigning workouts:", selectedWorkouts, "to plan:", selectedWorkoutPlan);
  };

  return (
    <div className="workout-routine-container">
      <div className="workout-routine-wrapper">
        <h2>Add Workout Routine</h2>
        <form onSubmit={handleSubmit}>
          <div className="workout-routine-form-group">
            <label htmlFor="exerciseName">Exercise Name:</label>
            <input
              type="text"
              id="exerciseName"
              name="exerciseName"
              value={formData.exerciseName}
              onChange={handleChange}
              placeholder="Enter Exercise Name"
              required
            />
          </div>
          <div className="workout-routine-form-group">
            <label htmlFor="sets">Sets:</label>
            <input
              type="number"
              id="sets"
              name="sets"
              value={formData.sets}
              onChange={handleChange}
              placeholder="Enter Sets"
              required
            />
          </div>
          <div className="workout-routine-form-group">
            <label htmlFor="reps">Reps:</label>
            <input
              type="number"
              id="reps"
              name="reps"
              value={formData.reps}
              onChange={handleChange}
              placeholder="Enter Reps"
              required
            />
          </div>
          <button type="submit" className="workout-routine-btn">
            Submit
          </button>
        </form>
        {successMessage && (
          <p className="workout-routine-success-message">{successMessage}</p>
        )}
        <Link to="/ownerDashboard" className="dashboard-link">
          Back to Dashboard
        </Link>
      </div>

      <div className="assign-workouts-container">
        <h2>Assign Workouts to Workout Plan</h2>
        <div className="workout-plan-select">
          <label htmlFor="workoutPlan">Select Workout Plan:</label>
          <select
            id="workoutPlan"
            name="workoutPlan"
            value={selectedWorkoutPlan}
            onChange={handleWorkoutPlanChange}
            required
          >
            <option value="" disabled>
              Select Workout Plan
            </option>
            {workoutPlans.map((plan, index) => (
              <option key={plan.planId || index} value={plan.planId}>
                {plan.planName}
              </option>
            ))}
          </select>
        </div>
        <table className="workouts-table">
          <thead>
            <tr>
              <th>Select</th>
              <th>Exercise Name</th>
              <th>Sets</th>
              <th>Reps</th>
            </tr>
          </thead>
          <tbody>
            {workouts.map((workout) => (
              <tr key={workout.id}>
                <td>
                  <input
                    type="checkbox"
                    onChange={(e) => handleWorkoutSelection(e, workout.id)}
                  />
                </td>
                <td>{workout.exerciseName}</td>
                <td>{workout.sets}</td>
                <td>{workout.reps}</td>
              </tr>
            ))}
          </tbody>
        </table>
        <button
          className="assign-workouts-btn"
          onClick={handleAssignWorkouts}
        >
          Assign
        </button>
      </div>
    </div>
  );
};

export default WorkoutRoutine;
