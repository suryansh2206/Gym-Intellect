import React from "react";
import HeaderMember from "./HeaderMember";
import "./HomeMember.css";

const HomeMember = () => {
  return (
    <>
      <HeaderMember />

      {/* Cards Container - NEW ADDITION */}
      <div className="cards-container">
        <div className="card">
          <img className="card-img-top" src="..." alt="Add Diet Plan" />
          <div className="card-body"></div>
        </div>

        <div className="card">
          <img className="card-img-top" src="..." alt="Add workout Plan" />
          <div className="card-body"></div>
        </div>
      </div>
    </>
  );
};

export default HomeMember;
