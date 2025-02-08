import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router";

const GymProfiles = () => {
  const userIdRedux = useSelector((state) => state.auth.userId);
  const tokenRedux = useSelector((state) => state.auth.token);

  const userId = userIdRedux || localStorage.getItem("userId");
  const token = tokenRedux || localStorage.getItem("token");

  const [gymProfiles, setGymProfiles] = useState(null);
  const [errorMessage, setErrorMessage] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchGymProfiles = async () => {
      setLoading(true);
      if (!token) {
        setErrorMessage("No valid token found. Please log in again.");
        setLoading(false);
        return;
      }

      try {
        const response = await fetch(
          `http://localhost:8081/api/gym-profile/gym-profiles/${userId}`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${token}`,
            },
            credentials: "include",
          }
        );

        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json();
        console.log("Fetched Gym Profiles:", data);
        setGymProfiles(Array.isArray(data) ? data : [data]);
      } catch (error) {
        console.error("Error fetching gym profiles:", error);
        setErrorMessage(error.message);
        setGymProfiles([]);
      } finally {
        setLoading(false);
      }
    };

    if (userId && token) {
      fetchGymProfiles();
    } else {
      console.error("Missing userId or token");
      setErrorMessage("Missing userId or token.");
      setLoading(false);
    }
  }, [userId, token]);

  return (
    <div className="gym-profiles-container">
      <h1 className="display-4">My Gym Profiles</h1>
      <Link to="/ownerDashboard" className="btn btn-outline-secondary">
        Back to Dashboard
      </Link>
      {errorMessage && <div style={{ color: "red" }}>{errorMessage}</div>}
      {loading ? (
        <div>Loading...</div>
      ) : (
        <table className="table table-striped table-bordered">
          <thead className="thead-dark">
            <tr>
              <th scope="col">#</th>
              <th scope="col">Gym Name</th>
              <th scope="col">Location</th>
              <th scope="col">Contact</th>
              <th scope="col">Open Hours</th>
              <th scope="col">GST</th>
              <th scope="col">Status</th>
            </tr>
          </thead>
          <tbody>
            {gymProfiles.map((profile, index) => (
              <tr key={profile.gymProfileId}>
                <th scope="row">{index + 1}</th>
                <td>{profile.gymName}</td>
                <td>{profile.location}</td>
                <td>{profile.contact}</td>
                <td>{profile.openHours}</td>
                <td>{profile.gst}</td>
                <td>{profile.status}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default GymProfiles;
