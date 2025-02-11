import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import "./OwnerProfile.css"; 
import { useNavigate } from "react-router-dom";

const MemberProfile = () => {

//   const { ownerIdRedux } = useSelector((state) => state.auth.userId);
  const tokenRedux = useSelector((state) => state.auth.token);
  const ownerId = localStorage.getItem("userId");
  const token = tokenRedux || localStorage.getItem("token");
  const [owner, setOwner] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    
    console.log(localStorage.getItem("userId"));
    const fetchOwnerDetails = async () => {
      try {
        const response = await fetch(`http://localhost:8214/api/members/profile/${ownerId}`, {
          method: "GET",
          headers: {
            "Authorization": `Bearer ${token}`,
            "Content-Type": "application/json"
          }
        });
        if (!response.ok) {
          throw new Error("Failed to fetch owner details");
        }
        const data = await response.json();
        setOwner(data);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchOwnerDetails();
  }, [ownerId, token]);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;
  if (!owner) return <p>No owner details found.</p>;

  return (
    <div className="container">
        <button className="back-button" onClick={() => navigate(-1)}>← Back</button>
  <div className="card">
    <div className="profile-pic">
      <img  src={require("../../assets/image.png")} alt="Profile" />
    </div>
    <div className="card_load_extreme_descripion">
    <p><strong>Name:</strong> {owner.username}</p>
      <p><strong>Date Of Birth:</strong> {owner.dob}</p>
      <p><strong>Gender:</strong> {owner.gender}</p>
      
      <p><strong>Address:</strong> {owner.address}</p>
      <p><strong>Height in cm:</strong> {owner.height}</p>
    </div>
  </div>
</div>

  );
  
};

export default MemberProfile;
