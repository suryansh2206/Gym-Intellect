import React from "react";
import HeaderMember from "./HeaderMember";
import "./HomeMember.css";

const HomeMember = () => {
  return (
    <>
      <HeaderMember className="home-member-background" />

      {/* Cards Container - NEW ADDITION */}
      <div className="cards-container home-member-background">
        <div className="card">
          <img
            className="card-img-top"
            src={require("../../assets/1.png")}
            alt="Add Diet Plan"
          />
        </div>

        <div className="card">
          <img className="card-img-top" src="..." alt="Add workout Plan" />
        </div>
      </div>
    </>
  );
};

export default HomeMember;
