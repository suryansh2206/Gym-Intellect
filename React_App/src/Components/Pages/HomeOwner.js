import React, { useState } from "react";
import HeaderOwner from "./HeaderOwner";
import SignupFormMember from "../Authentication/SignupFormMember";

const HomeOwner = () => {
  const [showSignupForm, setShowSignupForm] = useState(false);

  const handleAddMemberClick = () => {
    setShowSignupForm(!showSignupForm);
  };
  return (
    <>
      <div className="home-owner-container">
        <HeaderOwner onAddMemberClick={handleAddMemberClick} />

        <div className="content-container">
          {/* Render the SignupForm conditionally */}
          {showSignupForm && (
            <div className="signup-form-container">
              <SignupFormMember />
            </div>
          )}
        </div>
      </div>
    </>
  );
};

export default HomeOwner;
