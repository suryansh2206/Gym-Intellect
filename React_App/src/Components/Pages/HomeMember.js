import React from "react";
import HeaderMember from "./HeaderMember";
import { Link } from "react-router-dom";
import "./HomeMember.css";

const HomeMember = () => {
  return (
    <>
      <HeaderMember className="home-member-background" />

      {/* Cards Container - NEW ADDITION */}
      <div className="cards-container home-member-background">
      <Link to="/workoutplanmember" className="no-underline">
      <div className="card cursor-pointer hover:shadow-lg transition-shadow duration-300">
        <img
          className="card-img-top"
          src={require("../../assets/1.png")}
          alt="Workout Plan"
        />
      </div>
      </Link>
      <Link to="/dietplanmember" className="no-underline">
      <div className="card">
          <img
            className="card-img-top"
            src={require("../../assets/Diet Plan.png")}
            alt="Diet Plan"
          />
        </div>
      </Link>
        
      </div>
    </>
  );
};

export default HomeMember;
